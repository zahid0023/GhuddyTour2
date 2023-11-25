package com.ghuddy.backendapp.tours.dto.request.tour;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.Set;


@Data
public class TourCreateRequest extends BaseRequest {
    @Schema(description = "The unique identifier of the tour.", example = "10", required = true)
    @JsonProperty("added_tour_id")
    private Long addedTourID;

    @Schema(description = "A title that goes with the tour", example = "Relaxing Tour For All age in Bandarban", required = true)
    @JsonProperty("tour_title")
    private String title;
    @Schema(description = "A description of the tour", example = "A short description", required = true)
    @JsonProperty("tour_description")
    private String description;

    @Schema(description = "The url of the thumb image", example = "www.image.com")
    @JsonProperty("thumb_image_url")
    private String thumbImageURL;

    @Schema(description = "The list of activities associated with the tour.", required = true)
    @JsonProperty("tour_activity_ids")
    private Set<Long> tourActivityIds;

    @Schema(description = "The list of specialities associated with the tour.", required = true)
    @JsonProperty("tour_specialities")
    private List<TourSpecialityRequest> tourSpecialities;

    @Override
    public void validate() throws AbstractException {

    }
}




