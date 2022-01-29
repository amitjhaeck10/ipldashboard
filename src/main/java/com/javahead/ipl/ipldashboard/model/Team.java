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

    public Team(){
    }

}
