package com.ghuddy.backendapp.tours.model.data.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.dto.data.OptionData;
import com.ghuddy.backendapp.tours.model.entities.transfer.AvailableTransferOptionEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class AvailableTransferOptionData extends OptionData {
    @JsonProperty("tour_package_available_transfer_packages")
    private List<AvailableTransferPackageData> availableTransferPackageDataList;

    public AvailableTransferOptionData(AvailableTransferOptionEntity availableTransferOptionEntity){
        this.availableTransferPackageDataList = availableTransferOptionEntity.getAvailableTransferPackageEntityList().stream()
                .map(availableTransferPackageEntity->new AvailableTransferPackageData(availableTransferPackageEntity))
                .toList();
        this.setTotalOptionPricePerPerson(availableTransferOptionEntity.getTotalOptionPricePerPerson());
        this.setIsActive(availableTransferOptionEntity.getIsActive());
    }
}
