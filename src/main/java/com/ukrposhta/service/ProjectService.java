package com.ukrposhta.service;

import com.ukrposhta.model.Project;
import com.ukrposhta.model.Status;
import java.util.List;

public interface ProjectService {
    Project save(Project project);

    List<Project> findAll();

    List<Project> findAllByIdIn(List<Long> projectIds);

    Project findById(Long id);

    void updateStatus(Long id, Status status);

    void deleteById(Long id);
}
