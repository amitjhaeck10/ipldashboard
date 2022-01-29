package com.javahead.ipl.ipldashboard.respository;

import com.javahead.ipl.ipldashboard.model.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team,Long> {

    Team findByTeamName(String teamName);
}
