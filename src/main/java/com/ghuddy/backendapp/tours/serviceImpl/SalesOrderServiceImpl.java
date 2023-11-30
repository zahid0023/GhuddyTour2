package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.exception.AbstractException;
import com.ghuddy.backendapp.tours.dto.request.booking.SalesOrderRequest;
import com.ghuddy.backendapp.tours.model.entities.booking.SalesOrderEntity;
import com.ghuddy.backendapp.tours.model.entities.booking.TourPackageOptionBookingEntity;
import com.ghuddy.backendapp.tours.model.entities.combination.AvailableTourPackageOptionEntity;
import com.ghuddy.backendapp.tours.repository.TourPackageOptionRepository;
import com.ghuddy.backendapp.tours.repository.SalesOrderRepository;
import com.ghuddy.backendapp.tours.service.SalesOrderService;
import com.ghuddy.backendapp.tours.service.TourPackageOptionService;
import com.ghuddy.backendapp.tours.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SalesOrderServiceImpl implements SalesOrderService {
    private final SalesOrderRepository salesOrderRepository;
    private final JdbcTemplate jdbcTemplate;
    private final TourPackageOptionService tourPackageOptionService;


    public SalesOrderServiceImpl(SalesOrderRepository salesOrderRepository,
                                 JdbcTemplate jdbcTemplate,
                                 TourPackageOptionService tourPackageOptionService) {
        this.salesOrderRepository = salesOrderRepository;
        this.jdbcTemplate = jdbcTemplate;
        this.tourPackageOptionService = tourPackageOptionService;
    }


    @Override
    @Transactional
    public void updateSalesOrder(SalesOrderRequest request) throws AbstractException, IOException, InterruptedException {
        BigDecimal totalAmount = BigDecimal.valueOf(0.0);
        BigDecimal totalPayable = BigDecimal.valueOf(0.0);
        List<List<Long>> parametersList = new LinkedList<>();
        HashMap<String, Integer> hashMap = new HashMap<>();

        request.getTourShoppingCart().stream()
                .forEach(tourPackageOptionAddToCart -> {
                    parametersList.add(List.of(tourPackageOptionAddToCart.getAvailableTourPackageId(),
                            tourPackageOptionAddToCart.getAccommodationOptionId(),
                            tourPackageOptionAddToCart.getFoodOptionId(),
                            tourPackageOptionAddToCart.getTransferOptionId(),
                            tourPackageOptionAddToCart.getTransportationPackageId(),
                            tourPackageOptionAddToCart.getGuideOptionId(),
                            tourPackageOptionAddToCart.getSpotEntryOptionId()));

                    hashMap.put(StringUtil.generateKey(
                            tourPackageOptionAddToCart.getAvailableTourPackageId(),
                            tourPackageOptionAddToCart.getAccommodationOptionId(),
                            tourPackageOptionAddToCart.getFoodOptionId(),
                            tourPackageOptionAddToCart.getTransferOptionId(),
                            tourPackageOptionAddToCart.getTransportationPackageId(),
                            tourPackageOptionAddToCart.getGuideOptionId(),
                            tourPackageOptionAddToCart.getSpotEntryOptionId()), tourPackageOptionAddToCart.getQuantity());
                });
        List<Long> optionsIds = findMatchingRowsIds(parametersList);
        List<AvailableTourPackageOptionEntity> availableTourPackageOptionEntityList = tourPackageOptionService.getAllOptionsByIds(optionsIds);
        SalesOrderEntity salesOrderEntity = new SalesOrderEntity();
        List<TourPackageOptionBookingEntity> tourPackageOptionBookingEntityList = availableTourPackageOptionEntityList.stream()
                .map(availableTourPackageOptionEntity -> {
                    log.info(getKey(availableTourPackageOptionEntity));
                    return new TourPackageOptionBookingEntity(availableTourPackageOptionEntity, salesOrderEntity, hashMap.get(getKey(availableTourPackageOptionEntity)));
                })
                .toList();
        salesOrderEntity.setTourPackageOptionBookingEntities(tourPackageOptionBookingEntityList);
        salesOrderRepository.save(salesOrderEntity);
    }

    private String getKey(AvailableTourPackageOptionEntity availableTourPackageOptionEntity) {
        Long availableTourPackageId = availableTourPackageOptionEntity.getAvailableTourPackageEntity().getId();
        Long accommodationOptionId = availableTourPackageOptionEntity.getAvailableAccommodationOptionEntity() == null ? 0 : availableTourPackageOptionEntity.getAvailableAccommodationOptionEntity().getId();
        Long foodOptionId = availableTourPackageOptionEntity.getAvailableFoodOptionEntity() == null ? 0 : availableTourPackageOptionEntity.getAvailableFoodOptionEntity().getId();
        Long transferOptionId = availableTourPackageOptionEntity.getAvailableTransferOptionEntity() == null ? 0 : availableTourPackageOptionEntity.getAvailableTransferOptionEntity().getId();
        Long transportationOptionId = availableTourPackageOptionEntity.getAvailableTransportationPackageEntity() == null ? 0 : availableTourPackageOptionEntity.getAvailableTransportationPackageEntity().getId();
        Long guideOptionId = availableTourPackageOptionEntity.getAvailableGuideOptionEntity() == null ? 0 : availableTourPackageOptionEntity.getAvailableGuideOptionEntity().getId();
        Long spotEntryOptionId = availableTourPackageOptionEntity.getAvailableSpotEntryOptionEntity() == null ? 0 : availableTourPackageOptionEntity.getAvailableSpotEntryOptionEntity().getId();
        return StringUtil.generateKey(availableTourPackageId, accommodationOptionId, foodOptionId, transferOptionId, transportationOptionId, guideOptionId, spotEntryOptionId);
    }

    private List<Long> findMatchingRowsIds(List<List<Long>> parametersList) {
        String sql = "SELECT id" +
                " FROM available_components_all_options_combinations WHERE " +
                "(available_tour_package_id, " +
                "COALESCE(available_accommodation_option_id, 0), " +
                "COALESCE(available_food_option_id, 0), " +
                "COALESCE(available_transfer_option_id, 0), " +
                "COALESCE(available_transportation_package_id, 0), " +
                "COALESCE(available_guide_option_id, 0), " +
                "COALESCE(available_spot_entry_option_id, 0)) " +
                "IN (";

        String placeholders = String.join(",", Collections.nCopies(parametersList.size(), "(?,?,?,?,?,?,?)"));
        sql += placeholders + ")";
        log.info(sql.toLowerCase());

        log.info(sql);

        List<Object> flatParameters = parametersList.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        return jdbcTemplate.queryForList(
                sql,
                flatParameters.toArray(),
                Long.class);
    }


    public SalesOrderEntity save(SalesOrderEntity salesOrder) {
        salesOrder = salesOrderRepository.save(salesOrder);
        return salesOrder;
    }


    public SalesOrderEntity fetchSalesOrderById(Long orderId, String requestId) throws AbstractException {

        SalesOrderEntity salesOrderEntity = salesOrderRepository.findByIdAndDeleted(orderId, false);

        return salesOrderEntity;
    }

    public SalesOrderEntity fetchSalesOrderByOrderCode(String orderCode, String requestId) throws AbstractException {


        SalesOrderEntity salesOrderEntity = salesOrderRepository.findByOrderCodeAndDeleted(orderCode, false);
        if (salesOrderEntity == null)
            throw new AbstractException("error");

        return salesOrderEntity;
    }
}