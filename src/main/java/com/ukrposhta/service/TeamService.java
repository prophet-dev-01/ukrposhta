package com.ukrposhta.service;

import com.ukrposhta.model.Team;
import java.util.List;
import org.springframework.data.domain.PageRequest;

public interface TeamService {
    Team save(Team team);

    List<Team> findAll();

    List<Team> findAll(PageRequest pageRequest);

    List<Team> findAllByIdIn(List<Long> teamIds);

    Team findById(Long id);

    Team addEmployee(Long id, Long employeeId);

    Team addProject(Long id, Long projectId);

    void deleteById(Long id);
}
