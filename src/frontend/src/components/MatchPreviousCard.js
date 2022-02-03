import { React } from 'react';
import { Link } from 'react-router-dom';

import './MatchPreviousCard.scss'

export const MatchPreviousCard = ({match,teamName}) => {
  if(!match || !teamName) return null;
  const otherTeam = match.team1 === teamName ? match.team2:match.team1;
  const win = match.winByRuns === 0 ? match.winByWickets +' wicket' : match.winByRuns +' runs';
  const otherTeamLink = `/teams/${otherTeam}`;
  const isMatchWinner = teamName === match.winner;
  return (
      <div className={isMatchWinner ? 'MatchPreviousCard win-card': 'MatchPreviousCard loss-card'}>
        <span className='vs'>vs</span>
        <h1>vs <Link to={otherTeamLink}>{otherTeam}</Link>
        </h1>
        <p className='match-result'>{match.winner} won by {win}</p>
    </div>
  );
}