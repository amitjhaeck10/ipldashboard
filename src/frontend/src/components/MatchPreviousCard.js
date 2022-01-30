import { React } from 'react';

export const MatchPreviousCard = ({match}) => {
  return (
    <div className="MatchPreviousCard">
        <p>{match.team1} vs {match.team2}</p>
    </div>
  );
}