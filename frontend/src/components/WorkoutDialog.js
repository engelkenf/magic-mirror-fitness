import React from "react"
import { Grid, AppBar, Tabs, Tab } from "@mui/material"
import Dialog from '@mui/material/Dialog';
import Toolbar from '@mui/material/Toolbar';
import IconButton from '@mui/material/IconButton';
import AddCircle from "@mui/icons-material/AddCircle"
import Typography from '@mui/material/Typography';
import CloseIcon from '@mui/icons-material/Close';
import Slide from '@mui/material/Slide';
import WorkoutCard from "./WorkoutCard";

import {getAllWorkouts} from "../service/workout-service"
import { useEffect, useState } from "react"


const Transition = React.forwardRef(function Transition(props, ref) {
    return <Slide direction="up" ref={ref} {...props} />;
});

export default function WorkoutDialog(){

    const [open, setOpen] = React.useState(false);
    const [allWorkouts, setAllWorkouts] =useState([])
    const categories = [
        'warm_up', 'arms', 'legs', 'chest', 'back'
    ]

    useEffect(() => {
        getAllWorkouts()
            .then((result) => setAllWorkouts(result))
            .catch((error) => console.error(error))
    }, [])
    
    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    const styles = {
        AddCircle: {
            marginRight: 10
        }
    }
        return (
        <>
            <IconButton
                edge="end"
                aria-label="delete"
                onClick={handleClickOpen}
            >
                <AddCircle style={styles.AddCircle} fontSize="large" />
                <p>Add a workout</p>
            </IconButton>
            <Dialog
                fullScreen
                open={open}
                onClose={handleClose}
                TransitionComponent={Transition}
            >
                <AppBar sx={{ position: 'relative' }}>
                    <Toolbar>
                        <IconButton
                            edge="start"
                            color="inherit"
                            onClick={handleClose}
                            aria-label="close"
                        >
                            <CloseIcon />
                        </IconButton>
                        <Typography sx={{ ml: 2, flex: 1 }} variant="h6" component="div">
                            Chose your next workout
                        </Typography>
                    </Toolbar>
                </AppBar>

                <Grid container>

                    {
                        
                        allWorkouts.length !== 0 &&
                        allWorkouts.map(
                            workout =>
                                <WorkoutCard key={workout.id}
                                                 workout={workout}
                                                 handleClose={handleClose}
                                />
                        )
                    }
                </Grid>

                <AppBar position='static'>
                    <Tabs
                        value={false}
                        //onChange={handleOnIndexSelect}
                        indicatorColor ='secondary'
                        textColor ='secondary'
                        centered
                    >
                        {categories.map(group =>
                            <Tab key={group} label={group || 'All'} />
                        )}
                    </Tabs>
                </AppBar>

            </Dialog>

        </>
        )

}
