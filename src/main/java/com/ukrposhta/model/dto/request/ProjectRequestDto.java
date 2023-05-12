package com.ukrposhta.model.dto.request;

import com.ukrposhta.model.Status;
import java.time.LocalDate;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectRequestDto {
    @Column(unique = true, nullable = false)
    private String name;
    @Size(max = 300)
    private String description;
    private LocalDate startDate;
    private LocalDate finishDate;
    private LocalDate deadline;
    private Set<Long> teamIds;
    @Enumerated(EnumType.STRING)
    private Status status;
}
