package com.smbt.pickod.dto.journal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter @ToString
@NoArgsConstructor
public class JnlMemberDTO {
    private Long memberNum;
    private String memberId;
    private String nickName;
    private String memberImgYn;
    private Long memberImgsId;
    private List<JnlMemberImgsDTO> memberImgsList;

}