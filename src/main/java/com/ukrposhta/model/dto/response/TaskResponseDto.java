package com.ukrposhta.model.dto.response;

import com.ukrposhta.model.Status;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskResponseDto {
    private Long id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate finishDate;
    private Long assignedEmployeeId;
    private Status status;
}
