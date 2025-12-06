package com.albin.alcommit.dto.commit;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommitResponseDTO {

    //commits
    private String message;
    private String branch;
    private Long createBy;
    private LocalDateTime createAt;
    private Long updateBy;
    private LocalDateTime updateAt;

    public CommitResponseDTO(String message,
                             String branch, Long createBy, LocalDateTime createAt,
                             Long updateBy, LocalDateTime updateAt) {

        this.message = message;
        this.branch = branch;
        this.createBy = createBy;
        this.createAt = createAt;
        this.updateBy = updateBy;
        this.updateAt = updateAt;
    }

}
