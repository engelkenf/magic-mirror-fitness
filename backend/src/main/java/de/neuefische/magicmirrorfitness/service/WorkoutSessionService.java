package de.neuefische.magicmirrorfitness.service;

import de.neuefische.magicmirrorfitness.dto.WorkoutSessionDto;
import de.neuefische.magicmirrorfitness.model.WorkoutSession;
import de.neuefische.magicmirrorfitness.repo.WorkoutSessionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WorkoutSessionService {

    private final WorkoutSessionRepo workoutSessionRepo;

    @Autowired
    public WorkoutSessionService(WorkoutSessionRepo workoutSessionRepo) {
        this.workoutSessionRepo = workoutSessionRepo;
    }

    public List<WorkoutSession> getAllWorkoutSessions() {
        return workoutSessionRepo.findAll();
    }


    public WorkoutSession addWorkoutSession() {

        WorkoutSession newWorkoutSession = new WorkoutSession();

        newWorkoutSession.setId(createUUID());

        return workoutSessionRepo.save(newWorkoutSession);
    }

    private String createUUID() {
        return UUID.randomUUID().toString();
    }

    public WorkoutSession getWorkoutSession(String id){
        Optional<WorkoutSession> optionalWorkoutSession = workoutSessionRepo.findById(id);

        if (optionalWorkoutSession.isEmpty()) {
            throw new NoSuchElementException("Workout Session with id:" + id + " not found!");
        }
        return optionalWorkoutSession.get();
    }



    public void deleteWorkoutSession(String id) {
        Optional<WorkoutSession> optionalWorkoutSession = workoutSessionRepo.findById(id);
        if (optionalWorkoutSession.isEmpty()) {
            throw new NoSuchElementException("Workout Session with id:" + id + " not found!");
        }
        workoutSessionRepo.deleteById(id);
    }

    public WorkoutSession updateWorkoutSession(WorkoutSessionDto workoutSessionDto){

        if(workoutSessionRepo.existsById(workoutSessionDto.getId())){
            WorkoutSession workoutSession = new WorkoutSession();
            workoutSession.setId(workoutSessionDto.getId());
            workoutSession.setTitle(workoutSessionDto.getTitle());
            workoutSession.setDescription(workoutSessionDto.getDescription());
            workoutSession.setWorkoutIds(workoutSessionDto.getWorkoutIds());
            workoutSession.setOverallLength(workoutSessionDto.getOverallLength());
            workoutSession.setExecutionTime(workoutSessionDto.getExecutionTime());


            return workoutSessionRepo.save(workoutSession);
        } else{
            throw new NoSuchElementException("Could not update WorkoutSession element! Element with id does not exist: " + workoutSessionDto.getId());
        }

    }

}
