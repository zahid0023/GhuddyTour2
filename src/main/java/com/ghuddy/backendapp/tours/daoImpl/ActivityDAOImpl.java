package com.ghuddy.backendapp.tours.daoImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ghuddy.backendapp.tours.dao.ActivityDAO;
import com.ghuddy.backendapp.tours.enums.ErrorCode;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.model.data.activity.ActivityData;
import com.ghuddy.backendapp.tours.model.data.activity.ActivityImageData;
import com.ghuddy.backendapp.tours.model.data.activity.ActivityTypeData;
import com.ghuddy.backendapp.tours.utils.EntityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@Repository
public class ActivityDAOImpl implements ActivityDAO {
    private final JdbcTemplate jdbcTemplate;
    private final ObjectMapper objectMapper;

    public ActivityDAOImpl(JdbcTemplate jdbcTemplate,
                           ObjectMapper objectMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.objectMapper = objectMapper;
    }

    // used both for paginated and non paginated
    public List<ActivityTypeData> getAllActivityTypes(Integer pageSize, Integer pageNumber) throws EmptyListException {
        String sql = """
                      select id as activity_type_id,
                             activity_type_name as activity_type_name,
                             description as activity_type_description
                      from activity_type
                """;
        try {
            List<ActivityTypeData> activityTypes = EntityUtil.getAllEntitiesPaginated(sql, pageSize, pageNumber, ActivityTypeData.class, jdbcTemplate);
            return activityTypes;
        } catch (EmptyResultDataAccessException ex) {
            throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        }
    }

    // used both for paginated and non paginated
    @Override
    public List<ActivityData> getAllActivities(Integer pageSize, Integer pageNumber) {
        String query = getAllActivitiesQuery();
        if (pageNumber == 0 && pageSize == 0) {
            // No pagination required
            return jdbcTemplate.query(query, activityBeanPropertyRowMapper());
        } else {
            // Paginated query
            int offset = (pageNumber - 1) * pageSize;
            return jdbcTemplate.query(query + " LIMIT ? OFFSET ?", activityBeanPropertyRowMapper(), pageSize, offset);
        }
    }

    private RowMapper<ActivityData> activityBeanPropertyRowMapper() {
        // Implement your RowMapper logic here to map the ResultSet to CreatedTourData
        return (rs, rowNum) -> {
            ActivityData activityData = new ActivityData();
            // Map the ResultSet columns to activityData fields here
            activityData.setActivityId(rs.getLong("activity_id"));
            activityData.setActivityName(rs.getString("activity_name"));
            activityData.setActivityShortLocation(rs.getString("activity_short_location"));
            activityData.setActivityTypeName(rs.getString("activity_type_name"));
            // Get the JSON string from the ResultSet
            String activityImages = rs.getString("activity_images");

            if (activityImages != null && !activityImages.isEmpty())
                // Deserialize the JSON string into a List of ActivityImageData objects
                try {
                    List<ActivityImageData> activityImageDataList = objectMapper.readValue(activityImages, new TypeReference<List<ActivityImageData>>() {
                    });
                    activityData.setActivityImages(activityImageDataList);
                } catch (JsonProcessingException ex) {
                    log.error(ex.toString());
                }
            else activityData.setActivityImages(new LinkedList<>());
            return activityData;
        };
    }

    private String getAllActivitiesQuery() {
        return """
                with activity_images_agg as (select activity_images.activity_id                                         as activity_id,
                                                     json_agg(json_build_object('activity_image_id',
                                                                                activity_images.activity_image_id,
                                                                                'activity_image_file_name',
                                                                                activity_images.activity_image_file_name,
                                                                                'activity_image_url',
                                                                                activity_images.activity_image_url,
                                                                                'activity_image_caption',
                                                                                activity_images.activity_image_caption)) as activity_images
                                              from (select ai.id          as activity_image_id,
                                                           ai.file_name   as activity_image_file_name,
                                                           ai.image_url   as activity_image_url,
                                                           ai.caption     as activity_image_caption,
                                                           ai.activity_id as activity_id
                                                    from activity_images ai) as activity_images
                                              group by activity_images.activity_id)

                 select a.id                  as activity_id,
                        a.activity_name       as activity_name,
                        a.short_location      as activity_short_location,
                        at.activity_type_name as activity_type_name,
                        aia.activity_images   as activity_images

                 from activity a
                          inner join activity_type at on a.activity_type_id = at.id
                          left join activity_images_agg aia on a.id = aia.activity_id
                 """;
    }
}
