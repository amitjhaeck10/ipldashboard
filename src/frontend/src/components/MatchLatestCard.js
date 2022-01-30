import { React } from 'react';
import { Link } from 'react-router-dom';

export const MatchLatestCard = ({teamName, match}) => {
  if(!match || !teamName) return null;
  const otherTeam = match.team1 === teamName ? match.team2:match.team1;
  const win = match.winByRuns === 0 ? match.winByWickets +' wicket' : match.winByRuns +' runs';
  const otherTeamLink = `/teams/${otherTeam}`;
  return (
    <div className="MatchLatestCard">
       <h3>Match Latest Card</h3>
       <h4> vs  <Link to={otherTeamLink}>{otherTeam}</Link>
       </h4>
       <h4>Match Date: {match.date}  Venue: {match.venue}</h4>
       <h4>{match.winner} won by {win}</h4>
    </div>
  );
}