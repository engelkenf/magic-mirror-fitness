package de.neuefische.magicmirrorfitness.service;

import de.neuefische.magicmirrorfitness.model.WorkoutSession;
import de.neuefische.magicmirrorfitness.repo.WorkoutSessionRepo;
import org.json.JSONArray;
import org.json.JSONObject;
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

    public WorkoutSession updateWorkoutSession(String requestBody){

        final JSONObject requestObjAsJson = new JSONObject(requestBody);

        Optional<WorkoutSession> optionalWorkoutSession
                = workoutSessionRepo.findById(
                        requestObjAsJson.get("id").toString()
        );

        optionalWorkoutSession.get()
                .setTitle(requestObjAsJson.get("title").toString());
        optionalWorkoutSession.get()
                .setDescription(requestObjAsJson.get("description").toString());

        JSONArray workoutIdsJson = new JSONArray(requestObjAsJson.get("workoutIds").toString());
        optionalWorkoutSession.get().setWorkoutIds(jsonArrToStringArr(workoutIdsJson));

        optionalWorkoutSession.get()
                .setExecutionTime(requestObjAsJson.get("executionTime").toString());
        optionalWorkoutSession.get()
                .setOverallLength(requestObjAsJson.get("overallLength").toString());

        return workoutSessionRepo.save(optionalWorkoutSession.get());
    }


    private String[] jsonArrToStringArr(JSONArray workoutIdsJson){

        // Creating workout ID List and adding the data to it
        List<String> workoutIdList = new ArrayList<>();
        for (int i = 0; i < workoutIdsJson.length(); i++) {
            workoutIdList.add(workoutIdsJson.getString(i));
        }

        // Creating String array as final output
        int size = workoutIdList.size();

        return workoutIdList.toArray(new String[size]);
    }

}
