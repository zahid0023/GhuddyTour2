package com.ghuddy.backendapp.tours.daoImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ghuddy.backendapp.tours.dao.TourDAO;
import com.ghuddy.backendapp.tours.enums.ErrorCode;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.model.data.activity.SubscribedTourActivityData;
import com.ghuddy.backendapp.tours.model.data.tour.*;
import com.ghuddy.backendapp.tours.utils.EntityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
@Slf4j
public class TourDAOImpl implements TourDAO {

    private final JdbcTemplate jdbcTemplate;
    private final ObjectMapper objectMapper;

    public TourDAOImpl(JdbcTemplate jdbcTemplate, ObjectMapper objectMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<AddedTourData> getAllAddedTours(Integer pageSize, Integer pageNumber) throws EmptyListException {
        String query = """
                select at.id               AS added_tour_id,
                       at.tour_name        AS added_tour_name,
                       pnb.place_name      AS destination_location,
                       at.number_of_days   AS number_of_days,
                       at.number_of_nights AS number_of_nights,
                       at.short_address    AS short_address,
                       at.tour_tag         AS added_tour_tag
                from added_tours at
                         inner join place_near_by pnb on pnb.id = at.destination_location_id;
                                """;
        try {
            return EntityUtil.getAllEntitiesPaginated(query, pageSize, pageNumber, AddedTourData.class, jdbcTemplate);
        } catch (EmptyResultDataAccessException ex) {
            throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        }
    }

    /**
     * @param pageSize   the page size
     * @param pageNumber the number of page
     * @return List of TourData
     */
    public List<TourData> getAllCreatedTours(Integer pageSize, Integer pageNumber) {
        String query = getAllCreatedToursQuery();

        if (pageNumber == 0 && pageSize == 0) {
            // No pagination required
            return jdbcTemplate.query(query, tourDataRowMapper());
        } else {
            // Paginated query
            int offset = (pageNumber - 1) * pageSize;
            return jdbcTemplate.query(query + " LIMIT ? OFFSET ?", tourDataRowMapper(), pageSize, offset);
        }
    }

    private RowMapper<TourData> tourDataRowMapper() {
        // Implement your RowMapper logic here to map the ResultSet to CreatedTourData
        return (rs, rowNum) -> {
            TourData tourData = new TourData();
            // Map the ResultSet columns to createdTourData fields here
            tourData.setTourId(rs.getLong("tour_id"));
            tourData.setTourName(rs.getString("tour_name"));
            tourData.setTourThumbImageUrl(rs.getString("tour_thumb_image_url"));
            tourData.setTourTitle(rs.getString("tour_title"));
            tourData.setTourDescription(rs.getString("tour_description"));
            // Set other fields as needed
            String tourActivities = rs.getString("tour_itinerary");
            if (tourActivities != null && !tourActivities.isEmpty())
                try {
                    List<TourItineraryData> tourItinerary = objectMapper.readValue(tourActivities, new TypeReference<List<TourItineraryData>>() {
                    });
                    tourData.setItinerary(tourItinerary);
                } catch (JsonProcessingException ex) {
                    throw new RuntimeException(ex);
                }
            else tourData.setItinerary(new LinkedList<>());

            String tourSpecialities = rs.getString("tour_specialities");
            if (tourSpecialities != null && !tourSpecialities.isEmpty()) {
                try {
                    List<TourSpecialityData> tourSpecialityDataList = objectMapper.readValue(tourSpecialities, new TypeReference<List<TourSpecialityData>>() {
                    });
                    tourData.setTourSpecialityDataList(tourSpecialityDataList);
                } catch (JsonProcessingException ex) {
                    throw new RuntimeException(ex);
                }
            } else tourData.setTourSpecialityDataList(new LinkedList<>());

            return tourData;
        };
    }

    private String getAllCreatedToursQuery() {
        return """
                with tour_specialities_agg as (select ts.tour_id,
                                                      json_agg(json_build_object(
                                                              'tour_speciality_id', ts.id,
                                                              'tour_speciality_title', ts.title,
                                                              'tour_speciality_description', ts.description,
                                                              'tour_speciality_icon_url', ts.icon_url,
                                                              'tour_id', ts.tour_id
                                                          )) as tour_specialities_agg_column
                                               from tour_specialities ts
                                               group by ts.tour_id),
                     activity_image_agg as (select ai.activity_id,
                                                   json_agg(json_build_object(
                                                           'activity_image_id', ai.id,
                                                           'activity_image_file_name', ai.file_name,
                                                           'activity_image_url', ai.image_url,
                                                           'activity_image_caption', ai.caption
                                                       )) as activity_image_agg_column
                                            from activity_images ai
                                            group by ai.activity_id),
                     tour_activities_agg as (select ti.tour_id,
                                                    json_agg(json_build_object(
                                                            'tour_activity_id', ti.id,
                                                            'tour_activity_name', a.activity_name,
                                                            'tour_activity_images', COALESCE(ai.activity_image_agg_column, '[]'::json)
                                                        )) as tour_activities_agg_column
                                             from tour_itineraries ti
                                                      inner join activities a ON a.id = ti.activity_id
                                                      left join activity_image_agg ai ON ai.activity_id = a.id
                                             group by ti.tour_id)
                 
                select t.id                             as tour_id,
                       at.tour_name                     as tour_name,
                       t.title                          as tour_title,
                       t.description                    as tour_description,
                       t.thumb_image_url                as tour_thumb_image_url,
                       taa.tour_activities_agg_column   as tour_itinerary,
                       tsa.tour_specialities_agg_column as tour_specialities
                from tours t
                         inner join added_tours at on t.added_tour_id = at.id
                         left join tour_activities_agg taa ON taa.tour_id = t.id
                         left join tour_specialities_agg tsa ON tsa.tour_id = t.id;
                                """;
    }

    /**
     * @param pageSize
     * @param pageNumber
     * @param merchantId
     * @return
     * @throws EmptyListException
     */
    @Override
    public List<SubscribedTourData> getAllSubscribedToursForMerchant(Integer pageSize, Integer pageNumber, Long merchantId) {
        String query = getAllSubscribedToursForMerchantQuery();

        if (pageNumber == 0 && pageSize == 0) {
            // No pagination required
            return jdbcTemplate.query(query, subscribedTourDataRowMapper(), merchantId);
        } else {
            // Paginated query
            int offset = (pageNumber - 1) * pageSize;
            return jdbcTemplate.query(query + " LIMIT ? OFFSET ?", subscribedTourDataRowMapper(), merchantId, pageSize, offset);
        }
    }

    public String getAllSubscribedToursForMerchantQuery() {
        return """
                with subscribed_tour_itinerary_agg as (select subscribed_tour_itinerary.subscribed_tour_id as sub_scribed_tour_id,
                                                              json_agg(json_build_object(
                                                                      'activity_id', s_t_activity_id,
                                                                      'activity_name', s_t_activity_name,
                                                                      'activity_day_number', s_t_activity_day_number,
                                                                      'activity_start_time', s_t_activity_start_time,
                                                                      'activity_end_time',
                                                                      s_t_activity_end_time))              as subscribed_tour_itinerary_agg
                                                       from (select st.id                   as subscribed_tour_id,
                                                                    sti.activity_id         as s_t_activity_id,
                                                                    a.activity_name         as s_t_activity_name,
                                                                    sti.activity_day_number as s_t_activity_day_number,
                                                                    sti.activity_start_time as s_t_activity_start_time,
                                                                    sti.activity_end_time   as s_t_activity_end_time
                                
                                                             from subscribed_tour_itinerary sti
                                                                      inner join activity a on a.id = sti.activity_id
                                                                      inner join subscribed_tours st on sti.subscribed_tour_id = st.id
                                                             where st.merchant_id = ?) AS subscribed_tour_itinerary
                                                       group by subscribed_tour_itinerary.subscribed_tour_id)
                                
                select st.id                              as subscribed_tour_id,
                       at2.tour_name                      as subscribed_tour_name,
                       st.tour_reporting_time            as subscribed_tour_reporting_time,
                       st.tour_reporting_place            as subscribed_tour_reporting_place,
                       stia.subscribed_tour_itinerary_agg as subscribed_tour_itinerary
                                
                from subscribed_tours st
                         inner join tour t on st.tour_id = t.id
                         inner join added_tours at2 on t.added_tour_id = at2.id
                         left join subscribed_tour_itinerary_agg stia on st.id = stia.sub_scribed_tour_id
                """;
    }

    private RowMapper<SubscribedTourData> subscribedTourDataRowMapper() {
        return ((rs, rowNum) -> {
            SubscribedTourData subscribedTourData = new SubscribedTourData();

            subscribedTourData.setSubscribedTourId(rs.getLong("subscribed_tour_id"));
            subscribedTourData.setSubscribedTourName(rs.getString("subscribed_tour_name"));
            subscribedTourData.setTourReportingTime(rs.getTime("subscribed_tour_reporting_time").toLocalTime());
            subscribedTourData.setTourReportingPlace(rs.getString("subscribed_tour_reporting_place"));

            String subscribedTourItinerary = rs.getString("subscribed_tour_itinerary");
            if (subscribedTourItinerary != null && !subscribedTourItinerary.isEmpty())
                try {
                    List<SubscribedTourActivityData> tourItinerary = objectMapper.readValue(subscribedTourItinerary, new TypeReference<List<SubscribedTourActivityData>>() {
                    });
                    subscribedTourData.setSubscribedTourActivityList(tourItinerary);
                } catch (JsonProcessingException ex) {
                    throw new RuntimeException(ex);
                }
            else subscribedTourData.setSubscribedTourActivityList(new LinkedList<>());
            return subscribedTourData;
        });
    }
}
