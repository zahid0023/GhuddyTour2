package com.ghuddy.backendapp.tours.model.data.spot.entry;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.dto.data.OptionData;
import com.ghuddy.backendapp.tours.model.entities.spot.entry.AvailableSpotEntryOptionEntity;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class AvailableSpotEntryOptionData extends OptionData {
    @JsonProperty("tour_package_available_spot_entry_packages")
    private List<AvailableSpotEntryPackageData> availableSpotEntryPackageDataList;

    public AvailableSpotEntryOptionData(AvailableSpotEntryOptionEntity availableSpotEntryOptionEntity){
        this.availableSpotEntryPackageDataList = availableSpotEntryOptionEntity.getAvailableSpotEntryPackageEntityList().stream()
                .map(availableSpotEntryPackageEntity -> new AvailableSpotEntryPackageData(availableSpotEntryPackageEntity))
                .toList();
        this.setTotalOptionPricePerPerson(availableSpotEntryOptionEntity.getTotalOptionPricePerPerson());
        this.setIsActive(availableSpotEntryOptionEntity.getIsActive());
    }
}
