package com.smbt.pickod.dto.admin.place;

import com.smbt.pickod.dto.place.PlaceImgDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter
@ToString @NoArgsConstructor
public class AdmPlaceDetailsDTO {
    private Long placeId;
    private String placeName;
    private String placeAddress;
    private String placeDetails;
    private String placeUrl;
    private String placePhoneNum;
    private String placeCost;
    private String placeOpHour;
    private String placeRelated1;
    private String placeRelated2;
    private String placeRelated3;
    private Long placeLat;
    private Long placeLong;
    private String isReported;
    // img 파일 받아오는 부분까지
    List<PlaceImgDTO> placeImgDTO;

}
