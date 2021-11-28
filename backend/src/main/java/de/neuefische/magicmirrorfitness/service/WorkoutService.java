package de.neuefische.magicmirrorfitness.service;

import com.google.api.services.youtube.model.Video;
import de.neuefische.magicmirrorfitness.api.YouTubeConnection;
import de.neuefische.magicmirrorfitness.dto.WorkoutDto;
import de.neuefische.magicmirrorfitness.model.Workout;
import de.neuefische.magicmirrorfitness.repo.WorkoutRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class WorkoutService {

    private final WorkoutRepo workoutRepo;

    @Autowired
    public WorkoutService(WorkoutRepo workoutRepo){
        this.workoutRepo = workoutRepo;
    }

    public List<Workout> getAllWorkouts() {
        return workoutRepo.findAll();
    }

    public Workout addWorkout(WorkoutDto workoutDto){

        String videoId = workoutDto.getId();
        String category = workoutDto.getCategory();

        YouTubeConnection youtubeInfo = new YouTubeConnection();
        Video video = youtubeInfo.getVideoInfoById(videoId);

        Workout newWorkout = new Workout(
                video.getId(),
                video.getSnippet().getTitle(),
                video.getSnippet().getDescription(),
                video.getSnippet().getThumbnails().getStandard().getUrl(),
                video.getContentDetails().getDuration(),
                category
        );

        return workoutRepo.save(newWorkout);
    }

    public Workout getWorkout(String id) {
        Optional<Workout> optionalWorkout = workoutRepo.findById(id);

        if (optionalWorkout.isEmpty()) {
            throw new NoSuchElementException("Workout with id:" + id + " not found!");
        }
        return optionalWorkout.get();
    }

    public void deleteWorkout(String id) {
        Optional<Workout> optionalWorkout = workoutRepo.findById(id);
        if (optionalWorkout.isEmpty()) {
            throw new NoSuchElementException("Workout with id:" + id + " not found!");
        }
        workoutRepo.deleteById(id);
    }
}
