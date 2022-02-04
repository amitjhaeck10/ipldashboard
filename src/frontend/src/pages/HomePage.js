import { React, useEffect, useState } from 'react';
import { TeamTile } from '../components/TeamTile';

import './HomePage.scss';

export const HomePage = () => {
   const[teams,setTeams] = useState([]);

  useEffect(
    () => {
      const fetchTeam = async() =>{
        const response = await fetch(`http://localhost:8080/team`);
        const data = await  response.json();
        setTeams(data);
      };
      fetchTeam();
    },[]
  );
  
  return (
    <div className="HomePage">
        <div className='header-section'>
          <h1 className='app-page'>IPL DASHBOARD</h1> 
        </div>
        <div className='team-grid'>
             {teams.map(team => <TeamTile teamName={team.teamName}/>)}
        </div>
    </div>
  );
}