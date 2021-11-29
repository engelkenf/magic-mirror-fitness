import * as React from 'react';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';

export default function WorkoutCard({workout,handleClose}){

const styles = {
    Typography: {
        maxHeight: 100,
        overflow: "hidden",
        marginBottom: 10
    },
    b:{
        paddingTop: 10,
        marginRight: 10
    },
    Card:{
        margin: 10
    }
}

    return (

        <Card key={workout.id} style={styles.Card} sx={{ maxWidth: 345 }}>
            <CardMedia
                component="img"
                height="140"
                image={workout.thumbnailUrl}
                alt={workout.title}
            />
            <CardContent>
                <Typography gutterBottom variant="body2" component="div">
                    <b>{workout.title}</b>
                </Typography>
                <Typography style={styles.Typography} variant="body2" color="text.secondary">
                    {workout.description}
                </Typography>
                <Typography style={styles.b} variant="caption">
                    <b>Duration: </b>{workout.duration}
                </Typography>
                <Typography style={styles.b} variant="caption">
                    <b>Category: </b>{workout.category}
                </Typography>
            </CardContent>
            <CardActions>
                <Button
                    key={workout.id}
                    size="small"
                    onClick={handleClose}
                >
                        Add to session list
                </Button>
            </CardActions>
        </Card>
    );
}


