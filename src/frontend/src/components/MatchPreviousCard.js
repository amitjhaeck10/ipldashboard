import { React } from 'react';
import { Link } from 'react-router-dom';

export const MatchPreviousCard = ({match,teamName}) => {
  if(!match || !teamName) return null;
  const otherTeam = match.team1 === teamName ? match.team2:match.team1;
  const win = match.winByRuns === 0 ? match.winByWickets +' wicket' : match.winByRuns +' runs';
  const otherTeamLink = `/teams/${otherTeam}`;
  return (
    <div className="MatchPreviousCard">
        <h4>vs <Link to={otherTeamLink}>{otherTeam}</Link>
        </h4>
        <p>{match.winner} won by {win}</p>
    </div>
  );
}