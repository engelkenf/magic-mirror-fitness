import React from 'react'

import {useState} from 'react'
import WorkoutSessionDetail from "./WorkoutSessionDetail"


export default function MainContent(){
    
    
    const [sessionId, setSessionId] = useState("713f7d17-7c09-45f0-a331-0ba191f73f02")
    const [workoutId, setWorkoutId] = useState("")
    
    return (
        <section className="main-content">

            <WorkoutSessionDetail 
                sessionId={sessionId}
                workoutId={workoutId}
                setWorkoutId={setWorkoutId}
            />

        </section>
    )
}



