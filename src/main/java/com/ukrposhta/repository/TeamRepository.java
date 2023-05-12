package com.ukrposhta.repository;

import com.ukrposhta.model.Team;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findAllByIdIn(List<Long> teamIds);
}
