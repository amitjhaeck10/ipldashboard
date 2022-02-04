package com.javahead.ipl.ipldashboard.controller;

import com.javahead.ipl.ipldashboard.model.Match;
import com.javahead.ipl.ipldashboard.model.Team;
import com.javahead.ipl.ipldashboard.respository.MatchRepository;
import com.javahead.ipl.ipldashboard.respository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MatchRepository matchRepository;


    @GetMapping("/team")
    public Iterable<Team> getAllTeamName(){
        return teamRepository.findAll();
    }

    @GetMapping("/team/{teamName}")
    public Team getTeamInfo(@PathVariable String teamName) {
        Team team = this.teamRepository.findByTeamName(teamName);
        Pageable pageable = PageRequest.of(0, 4);
        List<Match> matches = this.matchRepository.getByTeam1OrTeam2OrderByDateDesc(teamName, teamName, pageable);
        team.setMatches(matches);

        return team;
    }

    @GetMapping("team/{teamName}/matches")
    public List<Match> getMatches(@PathVariable String teamName,@RequestParam int year){
          LocalDate startDate = LocalDate.of(year,1,1);
          LocalDate endDate = LocalDate.of(year+1,1,1);
          List<Match> matches = matchRepository.getMatchesByTeamNameBetweenDate(teamName,
          startDate, endDate);
        return matches;
    }
}
