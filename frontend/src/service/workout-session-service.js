import axios from "axios";

export const getWorkoutSessionById = (workoutId) => {
    const url = 'http://localhost:8080/api/workout-session/' + workoutId

    return axios
        .get(url)
        .then((response) => response.data)
        .catch((error) => console.log(error))

}

export const getAllWorkoutSessions = () => {

    return axios
        .get('http://localhost:8080/api/workout-session')
        .then((response) => response.data)
        .catch((error) => console.log(error))
}