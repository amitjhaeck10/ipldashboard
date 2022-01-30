package com.javahead.ipl.ipldashboard.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String teamName;
    private Long totalMatches;
    private Long totalWins;

    @Transient
    private List<Match> matches;

    public Team(String teamName,Long totalMatches){
        this.teamName=teamName;
        this.totalMatches=totalMatches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public Team(){
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setTotalMatches(Long totalMatches) {
        this.totalMatches = totalMatches;
    }

    public void setTotalWins(Long totalWins) {
        this.totalWins = totalWins;
    }

    public Long getId() {
        return id;
    }

    public String getTeamName() {
        return teamName;
    }

    public Long getTotalMatches() {
        return totalMatches;
    }

    public Long getTotalWins() {
        return totalWins;
    }

    public List<Match> getMatches() {
        return matches;
    }
}
