import React from 'react'
import WorkoutDialog from "./WorkoutDialog"
import PreviewWorkout from "./PreviewWorkout"
import useWorkout from "../hooks/useWorkout";



import { 
    Grid, 
    Paper, 
    List, 
    ListItem,
    ListItemText
} from "@mui/material"


export default function WorkoutSessionDetail({sessionId, workoutId, setWorkoutId}){

    const {workoutSession, workout} = useWorkout(sessionId, workoutId);

    const handleClick = workoutId => {
        setWorkoutId(workoutId)
    }

    const styles = {
        Paper: {
            padding: 20,
            margin: 10
        },
        ListItem:{
            cursor: 'pointer'
        }
    }
    return(
        <section className="workout-session-list">

            <Grid container>
                <Grid item sm>
                    <Paper style={styles.Paper}>
                        <List>

                            <ListItem>
                                <ListItemText
                                    //primary={workoutSession.title}
                                />
                            </ListItem>

                            {

                                workoutSession?.workoutIds?.map(
                                    id =>
                                        <ListItem key={id} style={styles.ListItem}>
                                            <ListItemText
                                                primary={id}
                                                onClick={() => handleClick(id)}
                                            />
                                        </ListItem>
                                )
                            }

                            <ListItem>


                                    <WorkoutDialog/>

                            </ListItem>
                            
                        </List>
                    </Paper>
                </Grid>

                <Grid item sm>
                    <Paper style={styles.Paper}>
                        {
                            workout &&

                        <PreviewWorkout workout={workout}/>
                            }
                    </Paper>
                </Grid>

            </Grid>
            
        </section>
    )


}
