package de.neuefische.magicmirrorfitness.controller;

import de.neuefische.magicmirrorfitness.model.Workout;
import de.neuefische.magicmirrorfitness.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workout")
public class WorkoutController {

    private final WorkoutService workoutService;

    @Autowired
    public WorkoutController(WorkoutService workoutService){
        this.workoutService = workoutService;
    }

    @CrossOrigin
    @GetMapping
    public List<Workout> getAllWorkouts() {
        return workoutService.getAllWorkouts();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public Workout getWorkoutById(@PathVariable String id) {
        return workoutService.getWorkout(id);
    }

    @CrossOrigin
    @PostMapping
    public Workout addWorkout(@RequestBody String requestBody){
        return workoutService.addWorkout(requestBody);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public String deleteWorkout(@PathVariable String id) {
        workoutService.deleteWorkout(id);
        return "Workout with ID: " + id + "has been deleted";
    }

}
