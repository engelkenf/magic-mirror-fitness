import axios from "axios";

export const getWorkoutById = (workoutId) => {
const url = 'http://localhost:8080/api/workout/' + workoutId

    return axios
        .get(url)
        .then((response) => response.data)
        .catch((error) => console.log(error))

}

export const getAllWorkouts = () => {
    
    return axios
        .get('http://localhost:8080/api/workout')
        .then((response) => response.data)
        .catch((error) => console.log(error))
}