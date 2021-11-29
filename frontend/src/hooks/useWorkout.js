import {useState, useEffect} from 'react'
import { getWorkoutById } from "../service/workout-service"
import { getWorkoutSessionById } from "../service/workout-session-service"

export default function useWorkout(sessionId, workoutId) {


    const [workout, setWorkout] = useState([])
    const [workoutSession, setWorkoutSession] = useState([])


    useEffect(() => {
        getWorkoutSessionById(sessionId)
            .then((result) => setWorkoutSession(result))
            .catch((error) => console.error(error))
    }, [sessionId])

    useEffect(() => {
        getWorkoutById(workoutId)
            .then((result) => setWorkout(result))
            .catch((error) => console.error(error))
    },[workoutId])
    
    return {
        workoutSession, workout
    }
}