package com.ukrposhta.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.ukrposhta.model.Employee;
import com.ukrposhta.model.Project;
import com.ukrposhta.model.Team;
import com.ukrposhta.repository.EmployeeRepository;
import com.ukrposhta.repository.ProjectRepository;
import com.ukrposhta.repository.TeamRepository;
import com.ukrposhta.service.TeamService;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class TeamServiceImplTest {
    @Mock
    private TeamService teamService;
    @Mock
    private TeamRepository teamRepository;
    @Mock
    private ProjectRepository projectRepository;
    @Mock
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        teamService = new TeamServiceImpl(teamRepository, employeeRepository, projectRepository);
    }

    @Test
    void addProject_correctCase_ok() {
        Long projectId = 1L;
        Long teamId = 2L;
        Project project = new Project();
        project.setId(projectId);
        Team team = new Team();
        team.setId(teamId);
        Mockito.when(teamRepository.findById(teamId))
                .thenReturn(Optional.of(team));
        Mockito.when(projectRepository.findById(projectId))
                .thenReturn(Optional.of(project));
        Mockito.when(teamRepository.save(any()))
                .thenAnswer(i -> i.getArgument(0, Team.class));
        Team result = teamService.addProject(teamId, projectId);
        Assertions.assertEquals(1, result.getProjects().size());
        Assertions.assertTrue(result.getProjects().contains(project));
    }

    @Test
    void addEmployee_ok() {
        Long teamId = 1L;
        Long employeeId = 2L;
        Team team = new Team();
        team.setId(teamId);
        team.setName("Test Team");
        Employee employee = new Employee();
        employee.setId(employeeId);
        employee.setFirstName("John Doe");
        Mockito.when(teamRepository.findById(teamId)).thenReturn(Optional.of(team));
        Mockito.when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
        Mockito.when(teamRepository.save(any()))
                .thenAnswer(i -> i.getArgument(0, Team.class));
        Team result = teamService.addEmployee(teamId, employeeId);
        assertEquals(teamId, result.getId());
        assertEquals(1, result.getEmployees().size());
        assertEquals(employee, result.getEmployees().get(0));
    }

    @Test
    void addProject_not_ok() {
        Long teamId = 1L;
        Long projectId = 2L;
        Team team = new Team();
        team.setId(teamId);
        when(teamRepository.findById(teamId)).thenReturn(Optional.of(team));
        when(projectRepository.findById(projectId)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> {
            teamService.addProject(teamId, projectId);
        });
    }
}
