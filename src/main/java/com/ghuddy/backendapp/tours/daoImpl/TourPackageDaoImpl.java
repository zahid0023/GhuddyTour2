package com.ghuddy.backendapp.tours.daoImpl;

import com.ghuddy.backendapp.tours.dao.TourPackageDao;
import com.ghuddy.backendapp.tours.enums.ErrorCode;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.model.data.tourpackage.TourPackageTypeData;
import com.ghuddy.backendapp.tours.utils.EntityUtil;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TourPackageDaoImpl implements TourPackageDao {
    private final JdbcTemplate jdbcTemplate;

    public TourPackageDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<TourPackageTypeData> getTourPackageTypes(Integer pageSize, Integer pageNumber) throws EmptyListException {
        String query = """
                select id                as tour_package_type_id,
                       package_type_name as tour_package_type_name,
                       description       as tour_package_type_description,
                       suitable_for      as tour_package_type_suitable_for
                from tour_package_type
                """;
        try {
            List<TourPackageTypeData> tourPackages = EntityUtil.getAllEntitiesPaginated(query, pageSize, pageNumber, TourPackageTypeData.class, jdbcTemplate);
            return tourPackages;
        } catch (EmptyResultDataAccessException ex) {
            throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        }
    }
}
