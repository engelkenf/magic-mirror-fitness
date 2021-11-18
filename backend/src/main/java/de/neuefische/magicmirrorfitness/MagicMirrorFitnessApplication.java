package de.neuefische.magicmirrorfitness;

import de.neuefische.magicmirrorfitness.api.YouTubeConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MagicMirrorFitnessApplication {

    public static void main(String[] args) {
        SpringApplication.run(MagicMirrorFitnessApplication.class, args);


        YouTubeConnection youtubeInfo = new YouTubeConnection();
        youtubeInfo.getVideoInfoById("_sNRH65Wmuw");
    }

}
