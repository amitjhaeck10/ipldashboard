package com.javahead.ipl.ipldashboard.teamdata;

import com.javahead.ipl.ipldashboard.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.Map;

@Component
public class BuildTeamData implements ITeamData{

    private EntityManager em;

    @Autowired
    public BuildTeamData(EntityManager em){
        this.em = em;
    }

    @Override
    @Transactional
    public void buildData(){
        Map<String, Team> teamMap = new HashMap<>();

        em.createQuery("select distinct m.team1,count(*) from Match m group by m.team1",Object[].class).
                getResultList().stream().map(e-> new Team((String)e[0],(Long)e[1])).
                forEach(team -> teamMap.put(team.getTeamName(),team));


        em.createQuery("select distinct m.team2,count(*) from Match m group by m.team2",Object[].class).
                getResultList().stream().forEach(e-> {
                      Team team = teamMap.get((String)e[0]);
                      if(team !=null) team.setTotalMatches(team.getTotalMatches() + (Long) e[1]);
                      });


        em.createQuery("select distinct m.winner,count(*) from Match m group by m.winner",Object[].class).
                getResultList().stream().forEach(e-> {
                    Team team = teamMap.get((String)e[0]);
                    if(team !=null) team.setTotalWins((Long) e[1]);
                });

        teamMap.values().forEach(t-> {
            System.out.println(t.toString());
        });
        teamMap.values().forEach(t->em.persist(t));
    }

}
