import { React, useEffect, useState } from 'react';
import { useParams,Link } from 'react-router-dom';
import { MatchLatestCard } from '../components/MatchLatestCard';
import { MatchPreviousCard } from '../components/MatchPreviousCard';
import { PieChart } from 'react-minimal-pie-chart';

import './TeamPage.scss';

export const TeamPage = () => {
   const[team,setTeam] = useState({matches:[]});
   const {teamName} = useParams();

  useEffect(
    () => {
      const fetchMatches = async() =>{
        const response = await fetch(`http://localhost:8080/team/${teamName}`);
        const data = await  response.json();
        setTeam(data);
      };
      fetchMatches();
    },[teamName]
  );

  if(!team || !team.teamName){
     return <h1>Team not found</h1>
  }
  
  const losses = team.totalMatches - team.totalWins;
  return (
    <div className="TeamPage">
        <div className='team-name-section'>
          <h2 className='home-page-section'>
             <Link to={`/home`}>Home</Link>
          </h2>     
          <h1 className='team-name'>{team.teamName}
          </h1> 
        </div>
        <div className='win-loss-section'>
            Wins / Losses
            <p>{team.totalWins}/{losses}</p>
            <PieChart
              data={[
                { title: 'losses', value:team.totalMatches - team.totalWins, color: '#b65445' },
                { title: 'wins', value: team.totalWins , color: '#53a864' }            
              ]}
            />
        </div>

       <div className='team-detail-section'>
          <h3>Match Latest Card</h3>
          <MatchLatestCard  teamName ={team.teamName}  match={team.matches[0]}/>
        </div>
            {team.matches.slice(1).map(match => <MatchPreviousCard teamName ={team.teamName} match={match} />)}
        <div className='more-link'>
           <Link to={`/teams/${teamName}/matches/${team.matches[0].season}`}>{team.matches[0].season} >></Link> 
        </div>
    </div>
  );
}