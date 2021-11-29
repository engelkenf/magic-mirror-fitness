import React from 'react'
import { AppBar, Typography } from '@mui/material'

export default function Header(){

    return (
        <AppBar position="static">
            <Typography style={styles.Typography} variant="h3" color="inherit">magic mirror fitness</Typography>
        </AppBar>

    )
}

const styles = {
    Typography: {
        margin: 15
    }
}

