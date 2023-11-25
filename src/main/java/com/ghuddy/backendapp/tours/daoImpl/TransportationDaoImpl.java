package com.ghuddy.backendapp.tours.daoImpl;

import com.ghuddy.backendapp.tours.dao.TransportationDao;
import com.ghuddy.backendapp.tours.enums.ErrorCode;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.model.data.transportation.TransportationBrandData;
import com.ghuddy.backendapp.tours.model.data.transportation.TransportationModeData;
import com.ghuddy.backendapp.tours.model.data.transportation.TransportationProviderData;
import com.ghuddy.backendapp.tours.model.data.transportation.TransportationRouteData;
import com.ghuddy.backendapp.tours.utils.EntityUtil;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransportationDaoImpl implements TransportationDao {
    private final JdbcTemplate jdbcTemplate;

    public TransportationDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<TransportationRouteData> getAllTransportationRoutes(Integer pageSize, Integer pageNumber) throws EmptyListException {
        String query = """
                select tr.id               as transportation_route_id,
                       src_loc.place_name  as transportation_route_source_location,
                       dest_loc.place_name as transportation_route_destination_location
                from transportation_route tr
                         inner join place_near_by src_loc on tr.source_location_id = src_loc.id
                         inner join place_near_by dest_loc on tr.destination_location_id = dest_loc.id
                                """;
        try {
            List<TransportationRouteData> transportationRoutes = EntityUtil.getAllEntitiesPaginated(query, pageSize, pageNumber, TransportationRouteData.class, jdbcTemplate);
            return transportationRoutes;
        } catch (EmptyResultDataAccessException ex) {
            throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        }
    }

    @Override
    public List<TransportationModeData> getAllTransportationModes(Integer pageSize, Integer pageNumber) throws EmptyListException {
        String query = """
                select tm.id          as transportation_mode_id,
                       tm.mode_name   as transportation_mode_name,
                       tm.description as transportation_mode_description,
                       tm.icon_url    as transportation_mode_icon_url
                from transportation_mode tm
                """;
        try {
            List<TransportationModeData> transportationModes = EntityUtil.getAllEntitiesPaginated(query, pageSize, pageNumber, TransportationModeData.class, jdbcTemplate);
            return transportationModes;
        } catch (EmptyResultDataAccessException ex) {
            throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        }
    }

    @Override
    public List<TransportationProviderData> getAllTransportationProviders(Integer pageSize, Integer pageNumber) throws EmptyListException {
        String query = """
                select tp.id                           as transportation_provider_id,
                       tp.transportation_provider_name as transportation_provider_name,
                       tp.hotline_number               as transportation_hotline_number,
                       tp.rating                       as transportation_provider_rating,
                       tp.total_reviews                as transportation_provider_total_reviews
                from transportation_provider tp
                """;
        try {
            List<TransportationProviderData> transportationProviders = EntityUtil.getAllEntitiesPaginated(query, pageSize, pageNumber, TransportationProviderData.class, jdbcTemplate);
            return transportationProviders;
        } catch (EmptyResultDataAccessException ex) {
            throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        }
    }

    @Override
    public List<TransportationBrandData> getAllTransportationBrands(Integer pageSize, Integer pageNumber) throws EmptyListException {
        String query = """
                select tb.id         as transportation_brand_id,
                       tb.brand_name as transportation_brand_name
                from transportation_brand tb
                """;
        try {
            List<TransportationBrandData> transportationBrands = EntityUtil.getAllEntitiesPaginated(query, pageSize, pageNumber, TransportationBrandData.class, jdbcTemplate);
            return transportationBrands;
        } catch (EmptyResultDataAccessException ex) {
            throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        }
    }
}
