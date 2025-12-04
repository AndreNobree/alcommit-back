package com.albin.alcommit.dto.commit;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommitResponseAllDTO {

    //commits
    private String messageCommit;
    private String branchCommit;
    private Long createByCommit;
    private LocalDateTime createAtCommit;
    private Long updateByCommit;
    private LocalDateTime updateAtCommit;

    public CommitResponseAllDTO(String messageCommit,
                                String branchCommit, Long createByCommit, LocalDateTime createAtCommit,
                                Long updateByCommit, LocalDateTime updateAtCommit) {

        this.messageCommit = messageCommit;
        this.branchCommit = branchCommit;
        this.createByCommit = createByCommit;
        this.createAtCommit = createAtCommit;
        this.updateByCommit = updateByCommit;
        this.updateAtCommit = updateAtCommit;
    }

}
