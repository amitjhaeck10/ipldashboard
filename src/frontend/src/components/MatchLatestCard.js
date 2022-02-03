import { React } from 'react';
import { Link } from 'react-router-dom';

import './MatchLatestCard.scss'

export const MatchLatestCard = ({teamName, match}) => {
  if(!match || !teamName) return null;
  const otherTeam = match.team1 === teamName ? match.team2:match.team1;
  const win = match.winByRuns === 0 ? match.winByWickets +' wicket' : match.winByRuns +' runs';
  const otherTeamLink = `/teams/${otherTeam}`;
  const isMatchWinner = teamName === match.winner;
  return (

    <div className={isMatchWinner ? 'MatchLatestCard win-card': 'MatchLatestCard loss-card'}>
       <div>
         <span className='vs'>vs</span>
          <h1><Link to={otherTeamLink}>{otherTeam}</Link>
          </h1>
          <h2 className='match-date'>Match Date: {match.date}</h2>
          <h3 className='match-venue'>Venue: {match.venue}</h3>
          <h3 className='match-winner'>{match.winner} won by {win}</h3>
       </div>
        <div className='additional-details'>
          <h3>First Inning</h3>
          <p>{match.team1}</p>
          <h3>Second Inning</h3>
          <p>{match.team2}</p>
          <h3>player Of Match</h3>
          <p>{match.playerOfMatch}</p>
          <h3>Umpires</h3>
          <p>{match.umpire1} {match.umpire2}</p>
        </div>
    </div>
  );
}