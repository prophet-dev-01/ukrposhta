package com.ukrposhta.model.dto.request;

import java.util.List;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class TeamRequestDto {
    @NonNull
    private String name;
    private List<Long> employeeIds;
    private List<Long> taskIds;
    private List<Long> projectIds;
}
