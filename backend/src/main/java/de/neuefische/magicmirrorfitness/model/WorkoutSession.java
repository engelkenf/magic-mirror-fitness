package de.neuefische.magicmirrorfitness.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("session")
public class WorkoutSession {

    private String id;
    private String title;
    private String description;
    private String[] workoutIds;
    private String executionTime;
    private String overallLength;

}
