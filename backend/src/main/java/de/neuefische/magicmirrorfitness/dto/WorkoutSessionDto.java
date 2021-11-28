package de.neuefische.magicmirrorfitness.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutSessionDto {

    private String id;
    private String title;
    private String description;
    private String[] workoutIds;
    private String executionTime;
    private String overallLength;
}
