package de.neuefische.magicmirrorfitness.service;

import de.neuefische.magicmirrorfitness.dto.WorkoutSessionDto;
import de.neuefische.magicmirrorfitness.model.Workout;
import de.neuefische.magicmirrorfitness.model.WorkoutSession;
import de.neuefische.magicmirrorfitness.repo.WorkoutSessionRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class WorkoutSessionServiceTest {

    WorkoutSessionRepo workoutSessionRepo = mock(WorkoutSessionRepo.class);
    WorkoutSessionService workoutSessionService = new WorkoutSessionService(workoutSessionRepo);

    @Test
    void testAddWorkoutSession() {

        //GIVEN
        String workoutSessionId = "f3c447b1-16b8-46c8-b528-0509fe652b5e";
        String[] workoutIds1 = {"ID1", "ID2"};

        WorkoutSession workoutSession1 = new WorkoutSession();
        workoutSession1.setId(workoutSessionId);
        workoutSession1.setTitle("Session Title 1");
        workoutSession1.setDescription("Session Description");
        workoutSession1.setWorkoutIds(workoutIds1);

        WorkoutSession workoutSession2 = new WorkoutSession();
        workoutSession2.setId(workoutSessionId);
        workoutSession2.setTitle("Session Title 2");
        workoutSession2.setDescription("Session Description");
        workoutSession2.setWorkoutIds(workoutIds1);

        when(workoutSessionRepo.save(any())).thenReturn(workoutSession1);

        //WHEN
        WorkoutSession actual = workoutSessionService.addWorkoutSession();

        //THEN
        verify(workoutSessionRepo).save(workoutSession1);
        assertThat(actual, is(workoutSession2));
    }

    @Test
    void testGetWorkoutSession() {

        //GIVEN
        String workoutSessionId = "f3c447b1-16b8-46c8-b528-0509fe652b5e";
        String[] workoutIds1 = {"ID1", "ID2"};

        WorkoutSession workoutSession1 = new WorkoutSession();
        workoutSession1.setId(workoutSessionId);
        workoutSession1.setTitle("Session Title 1");
        workoutSession1.setDescription("Session Description");
        workoutSession1.setWorkoutIds(workoutIds1);

        when(workoutSessionRepo.findById(any())).thenReturn(java.util.Optional.of(workoutSession1));

        //WHEN
        WorkoutSession actual = workoutSessionService.getWorkoutSession(workoutSessionId);

        //THEN
        verify(workoutSessionRepo).findById(workoutSessionId);
        assertThat(actual, is(workoutSession1));
    }

    @Test
    void getWorkoutSession_elementNotFound() {
        //GIVEN
        String workoutSessionId = "invalidID";

        when(workoutSessionRepo.existsById(workoutSessionId)).thenThrow(NoSuchElementException.class);

        //WHEN
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            workoutSessionService.getWorkoutSession(workoutSessionId);
        });
    }

    @Test
    void deleteWorkoutSession_elementNotFound() {
        //GIVEN
        String workoutSessionId = "invalidID";

        when(workoutSessionRepo.existsById(workoutSessionId)).thenThrow(NoSuchElementException.class);

        //WHEN
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            workoutSessionService.deleteWorkoutSession(workoutSessionId);
        });
    }

    @Test
    void testUpdateWorkoutSession() {

        //GIVEN
        String workoutSessionId = "f3c447b1-16b8-46c8-b528-0509fe652b5e";
        String[] updatedWorkoutIds = {"ID1", "ID2", "ID3", "ID4"};
        String[] workoutIds1 = {"ID1", "ID2"};

        WorkoutSessionDto sessionDto = new WorkoutSessionDto();
        sessionDto.setId(workoutSessionId);
        sessionDto.setTitle("Session Title 1");
        sessionDto.setDescription("Session Description");
        sessionDto.setWorkoutIds(workoutIds1);

        WorkoutSession sessionToSave = new WorkoutSession();
        sessionToSave.setId(workoutSessionId);
        sessionToSave.setTitle("Session Title 1");
        sessionToSave.setDescription("Session Description");
        sessionToSave.setWorkoutIds(workoutIds1);

        WorkoutSession updatedSession = new WorkoutSession();
        updatedSession.setId(workoutSessionId);
        updatedSession.setTitle("Session Title 1");
        updatedSession.setDescription("Session Description");
        updatedSession.setWorkoutIds(updatedWorkoutIds);

        when(workoutSessionRepo.existsById(any())).thenReturn(true);
        when(workoutSessionRepo.save(any())).thenReturn(updatedSession);

        //WHEN
        WorkoutSession actual = workoutSessionService.updateWorkoutSession(sessionDto);

        //THEN
        verify(workoutSessionRepo).save(sessionToSave);
        assertThat(actual, is(updatedSession));

    }
    @Test
    void testUpdateWorkoutSession_elementNotFound() {

        //GIVEN
        String[] workoutIds1 = {"ID1", "ID2"};

        WorkoutSessionDto sessionDto = new WorkoutSessionDto();
        sessionDto.setId("invalidID");
        sessionDto.setTitle("Session Title 1");
        sessionDto.setDescription("Session Description");
        sessionDto.setWorkoutIds(workoutIds1);

        when(workoutSessionRepo.existsById("invalidId")).thenThrow(NoSuchElementException.class);

        //WHEN
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            workoutSessionService.updateWorkoutSession(sessionDto);
        });
    }
}