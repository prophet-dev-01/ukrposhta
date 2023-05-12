package com.ukrposhta.repository;

import com.ukrposhta.model.Project;
import com.ukrposhta.model.Status;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByIdIn(List<Long> projectIds);

    @Transactional
    @Modifying
    @Query("UPDATE Project SET status = :status where id = :id")
    void updateStatus(Long id, Status status);
}
