import * as React from 'react';
import ReactPlayer from 'react-player/youtube'
import Typography from '@mui/material/Typography';

export default function PreviewWorkout({workout}) {

    //console.log(allWorkouts)
    const videoURL = "https://www.youtube.com/watch?v=" + workout.id
    //console.log(videoURL)

    const styles = {
        b:{
            paddingTop: 10,
            marginRight: 10
        }
    }

    return (
        <>
            <Typography gutterBottom variant="body2" component="div">
                <b>{workout.title}</b>
            </Typography>
            
            <Typography style={styles.b} variant="caption">
                <b>Category: </b>{workout.category}
            </Typography>
            <Typography style={styles.b} variant="caption">
                <b>Duration: </b>{workout.duration}
            </Typography>
            
            <ReactPlayer 
                url={videoURL} 
                controls />
            
            <p>{workout.description}</p>
        </>
    );
}