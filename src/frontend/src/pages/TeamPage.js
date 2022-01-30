import { React, useEffect, useState } from 'react';
import { MatchLatestCard } from '../components/MatchLatestCard';
import { MatchPreviousCard } from '../components/MatchPreviousCard';

export const TeamPage = () => {
   const[team,setTeam] = useState({matches:[]});

  useEffect(
    () => {
      const fetchMatches = async() =>{
        const response = await fetch('http://localhost:8080/team/Mumbai%20Indians');
        const data = await  response.json();
        setTeam(data);
      };
      fetchMatches();
    },[]
  );

  return (
    <div className="TeamPage">
        <h1>{team.teamName}</h1>
        <MatchLatestCard match={team.matches[0]}/>
        {team.matches.slice(1).map(match => <MatchPreviousCard match={match} />)}
    </div>
  );
}