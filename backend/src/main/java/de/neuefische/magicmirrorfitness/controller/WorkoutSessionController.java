package de.neuefische.magicmirrorfitness.controller;

import de.neuefische.magicmirrorfitness.dto.WorkoutSessionDto;
import de.neuefische.magicmirrorfitness.model.WorkoutSession;
import de.neuefische.magicmirrorfitness.service.WorkoutSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workout-session")
public class WorkoutSessionController {

    private final WorkoutSessionService workoutSessionService;

    @Autowired
    public WorkoutSessionController(WorkoutSessionService workoutSessionService){
        this.workoutSessionService = workoutSessionService;
    }

    @CrossOrigin
    @GetMapping
    public List<WorkoutSession> getAllWorkoutSessions(){
        return workoutSessionService.getAllWorkoutSessions();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public WorkoutSession getWorkoutSessionById(@PathVariable String id) {
        return workoutSessionService.getWorkoutSession(id);
    }

    @CrossOrigin
    @PostMapping
    public WorkoutSession addWorkoutSession(){
        return workoutSessionService.addWorkoutSession();
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public String deleteWorkoutSession(@PathVariable String id){
        workoutSessionService.deleteWorkoutSession(id);
        return "Workout session with ID: " + id + "has been deleted";
    }

    @CrossOrigin
    @PutMapping
    public WorkoutSession updateWorkoutSession(@RequestBody WorkoutSessionDto workoutSessionDto){
        return workoutSessionService.updateWorkoutSession(workoutSessionDto);
    }


}
