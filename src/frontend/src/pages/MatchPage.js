import { React, useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { MatchLatestCard } from '../components/MatchLatestCard';

export const MatchPage = () => {
   const[match,setMatch] = useState([]);
   const {teamName,year} = useParams();
  useEffect(
    () => {
      const fetchMatches = async() =>{
        const response = await fetch(`http://localhost:8080/team/${teamName}/matches?year=${year}`);
        const data = await  response.json();
        setMatch(data);
      };
      fetchMatches();
    },[]
  );

  if(!teamName){
     return <h1>Team not found</h1>
  }

  return (
    <div className="MatchPage">
        <h1>{teamName}</h1>
        {match.map(match => <MatchLatestCard teamName ={teamName} match={match} />)}
    </div>
  );
}