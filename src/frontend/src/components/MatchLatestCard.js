import { React } from 'react';

export const MatchLatestCard = ({match}) => {
  if(!match) return null;
  return (
    <div className="MatchLatestCard">
       <h3>Match Latest Card</h3>
         <h4>{match.team1} vs {match.team2}</h4>
    </div>
  );
}