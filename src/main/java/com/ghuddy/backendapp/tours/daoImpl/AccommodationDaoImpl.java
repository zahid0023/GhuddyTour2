package com.ghuddy.backendapp.tours.daoImpl;

import com.ghuddy.backendapp.tours.dao.AccommodationDao;
import com.ghuddy.backendapp.tours.enums.ErrorCode;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.model.data.accommodation.AccommodationData;
import com.ghuddy.backendapp.tours.model.data.accommodation.AccommodationTypeData;
import com.ghuddy.backendapp.tours.model.data.accommodation.TourRoomCategoryData;
import com.ghuddy.backendapp.tours.model.data.accommodation.TourRoomTypeData;
import com.ghuddy.backendapp.tours.utils.EntityUtil;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccommodationDaoImpl implements AccommodationDao {
    private final JdbcTemplate jdbcTemplate;

    public AccommodationDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<TourRoomCategoryData> getTourRoomCategories(Integer pageSize, Integer pageNumber) throws EmptyListException {
        String query = """
                select id                 as tour_room_category_id,
                       room_category_name as tour_room_category_name,
                       description        as tour_room_category_description
                from tour_room_category
                """;
        try {
            List<TourRoomCategoryData> tourRoomCategories = EntityUtil.getAllEntitiesPaginated(query, pageSize, pageNumber, TourRoomCategoryData.class, jdbcTemplate);
            return tourRoomCategories;
        } catch (EmptyResultDataAccessException ex) {
            throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        }
    }

    @Override
    public List<TourRoomTypeData> getTourRoomTypes(Integer pageSize, Integer pageNumber) throws EmptyListException {
        String query = """
                select id             as tour_room_type_id,
                       room_type_name as tour_room_type_name,
                       description    as tour_room_type_description
                from tour_room_type
                """;
        try {
            List<TourRoomTypeData> tourRoomTypes = EntityUtil.getAllEntitiesPaginated(query, pageSize, pageNumber, TourRoomTypeData.class, jdbcTemplate);
            return tourRoomTypes;
        } catch (EmptyResultDataAccessException ex) {
            throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        }
    }

    @Override
    public List<AccommodationTypeData> getTourAccommodationTypes(Integer pageSize, Integer pageNumber) throws EmptyListException {
        String query = """
                select id                      as tour_accommodation_type_id,
                       accommodation_type_name as tour_accommodation_type_name
                from tour_accommodation_type
                """;
        try {
            List<AccommodationTypeData> tourAccommodationTypes = EntityUtil.getAllEntitiesPaginated(query, pageSize, pageNumber, AccommodationTypeData.class, jdbcTemplate);
            return tourAccommodationTypes;
        } catch (EmptyResultDataAccessException ex) {
            throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        }
    }

    @Override
    public List<AccommodationData> getTourAccommodations(Integer pageSize, Integer pageNumber) throws EmptyListException {
        String query = """
                select ta.id                       as tour_accommodation_id,
                       ta.accommodation_name       as tour_accommodation_name,
                       tat.accommodation_type_name as tour_accommodation_type,
                       ta.accommodation_address    as tour_accommodation_address,
                       ta.average_rating           as tour_accommodation_average_rating,
                       ta.total_reviews            as tour_accommodation_total_reviews
                from tour_accommodation as ta
                         inner join tour_accommodation_type tat on tat.id = ta.accommodation_type_id
                """;
        try {
            List<AccommodationData> tourAccommodations = EntityUtil.getAllEntitiesPaginated(query, pageSize, pageNumber, AccommodationData.class, jdbcTemplate);
            return tourAccommodations;
        } catch (EmptyResultDataAccessException ex) {
            throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        }
    }
}
