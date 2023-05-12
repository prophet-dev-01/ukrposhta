package com.ukrposhta.service.impl;

import com.ukrposhta.model.Project;
import com.ukrposhta.model.Status;
import com.ukrposhta.repository.ProjectRepository;
import com.ukrposhta.service.ProjectService;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    @Override
    public Project save(Project project) {
        if (project.getStatus() == Status.NOT_COMPLETED_SUCCESSFUL
                || project.getStatus() == Status.COMPLETED_SUCCESSFUL) {
            project.setFinishDate(LocalDate.now());
        }
        return projectRepository.save(project);
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public List<Project> findAllByIdIn(List<Long> projectIds) {
        return projectRepository.findAllByIdIn(projectIds);
    }

    @Override
    public Project findById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(
                        "Couldn't find project by id: " + id));
    }

    @Override
    public void updateStatus(Long id, Status status) {
        if (status == Status.COMPLETED_SUCCESSFUL || status == Status.NOT_COMPLETED_SUCCESSFUL) {
            Project project = projectRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException(
                            "Couldn't find project by id: " + id));
            project.setFinishDate(LocalDate.now());
            projectRepository.save(project);
            return;
        }
        projectRepository.updateStatus(id, status);
    }

    @Override
    public void deleteById(Long id) {
        projectRepository.deleteById(id);
    }
}
