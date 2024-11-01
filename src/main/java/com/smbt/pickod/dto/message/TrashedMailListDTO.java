package com.smbt.pickod.dto.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter @ToString
@NoArgsConstructor
public class TrashedMailListDTO {
    private Long memberNum;
    private Long msgSender;
    private Long msgRecipient;
    private Long msgId;
    private Long msgRead;
    private String memberNickname;
    private LocalDateTime msgSentTime;
    private String msgContent;
    private String msgBox;
}