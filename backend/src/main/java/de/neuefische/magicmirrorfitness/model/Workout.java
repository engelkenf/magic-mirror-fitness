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
@Document("workout")
public class Workout {

    private String id; //same as YouTube id
    private String title;
    private String description;
    private String thumbnailUrl;
    private String duration;

    private String category;

}
