package com.ukrposhta.model.dto.response;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamResponseDto {
    private Long id;
    private String name;
    private List<Long> employeeIds;
    private List<Long> taskIds;
    private List<Long> projectIds;
}
