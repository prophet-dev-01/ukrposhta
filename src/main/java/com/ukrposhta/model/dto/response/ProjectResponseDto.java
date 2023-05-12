package com.ukrposhta.model.dto.response;

import com.ukrposhta.model.Status;
import java.time.LocalDate;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectResponseDto {
    private Long id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate finishDate;
    private LocalDate deadline;
    private Set<Long> teamIds;
    private Status status;
}
