package com.javahead.ipl.ipldashboard.batchprocessing;

import com.javahead.ipl.ipldashboard.data.MatchDataInput;
import com.javahead.ipl.ipldashboard.model.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;

public class MatchDataItemProcessor implements ItemProcessor<MatchDataInput, Match  > {
    private static final Logger log = LoggerFactory.getLogger(MatchDataItemProcessor.class);

    @Override
    public Match process(final MatchDataInput matchDataInput) throws Exception {
        Match match = new Match();

        match.setId(Long.parseLong(matchDataInput.getId()));
        match.setCity(matchDataInput.getCity());
        match.setPlayerOfMatch(matchDataInput.getPlayer_of_match());
        match.setResult(matchDataInput.getResult());
        match.setSeason(matchDataInput.getSeason());
        match.setWinner(matchDataInput.getWinner());
        match.setTossDecision(matchDataInput.getToss_decision());
        match.setTossWinner(matchDataInput.getToss_winner());
        match.setUmpire1(matchDataInput.getUmpire1());
        match.setUmpire2(matchDataInput.getUmpire2());
        match.setUmpire2(matchDataInput.getUmpire2());
        match.setWinByRuns(Integer.parseInt(matchDataInput.getWin_by_runs()));
        match.setWinByWickets(Integer.parseInt(matchDataInput.getWin_by_wickets()));
        match.setDate(LocalDate.parse(matchDataInput.getDate()));
        match.setVenue(matchDataInput.getVenue());

        String firstInningTeam,secondInningTeam;
        if("bat".equals(match.getTossDecision())){
            firstInningTeam = matchDataInput.getToss_winner();
            secondInningTeam = matchDataInput.getToss_winner().equals(matchDataInput.getTeam1())?
                  matchDataInput.getTeam2():matchDataInput.getTeam1();
        }else{
            secondInningTeam = matchDataInput.getToss_winner();
            firstInningTeam = matchDataInput.getToss_winner().equals(matchDataInput.getTeam1())?
                    matchDataInput.getTeam2():matchDataInput.getTeam1();
        }
        match.setTeam1(firstInningTeam);
        match.setTeam2(secondInningTeam);

        log.info("Converting (" + matchDataInput + ") into (" + match + ")");

        return match;
    }

}
