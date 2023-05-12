package com.ukrposhta.model.dto.request;

import com.ukrposhta.model.Status;
import java.time.LocalDate;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class TaskRequestDto {
    @NonNull
    private String name;
    @Size(max = 300)
    private String description;
    private LocalDate startDate;
    private LocalDate finishDate;
    @Positive
    private Long assignedEmployeeId;
    @Enumerated(EnumType.STRING)
    private Status status;
}

