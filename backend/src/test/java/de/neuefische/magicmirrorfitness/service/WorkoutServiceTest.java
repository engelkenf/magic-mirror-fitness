package de.neuefische.magicmirrorfitness.service;

import de.neuefische.magicmirrorfitness.dto.WorkoutDto;
import de.neuefische.magicmirrorfitness.model.Workout;
import de.neuefische.magicmirrorfitness.repo.WorkoutRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

class WorkoutServiceTest {

    WorkoutRepo workoutRepo = mock(WorkoutRepo.class);
    WorkoutService workoutService = new WorkoutService(workoutRepo);

    @Test
    void testAddWorkout() {

        //GIVEN
        WorkoutDto workoutDto = new WorkoutDto();
        workoutDto.setVideoId("WZBISjfCdCs");
        workoutDto.setCategory("warm_up");

        Workout workoutToSave = new Workout();
        workoutToSave.setId("WZBISjfCdCs");
        workoutToSave.setCategory("warm_up");
        workoutToSave.setTitle("Franzose erfindet genialen ausziehbaren einbruchsicheren Wohnwagen. Beauer: XXXL Design Innenraum.");
        workoutToSave.setDescription("0:00 Vorstellung Wohnwagen und Erfinder\n" +
                "6:08 Innenraum\n" +
                "13:49 Wohnwagen zusammengefahren\n" +
                "18:28 Kontakt\n" +
                "18:52 Preisliste\n" +
                "\n" +
                "\n" +
                "Wohnwagen Neuheiten 2022");
        workoutToSave.setThumbnailUrl("https://i.ytimg.com/vi/WZBISjfCdCs/sddefault.jpg");
        workoutToSave.setDuration("PT19M23S");

        Workout createdWorkout = new Workout();
        createdWorkout.setId("WZBISjfCdCs");
        createdWorkout.setCategory("warm_up");

        when(workoutRepo.save(any())).thenReturn(createdWorkout);

        //WHEN
        Workout actual = workoutService.addWorkout(workoutDto);

        //THEN
        verify(workoutRepo).save(workoutToSave);
        assertThat(actual, is(createdWorkout));
    }

    @Test
    void testGetWorkout(){

        //GIVEN
        String workoutId = "WZBISjfCdCs";

        Workout returnWorkout = new Workout();
        returnWorkout.setId(workoutId);
        returnWorkout.setCategory("warm_up");
        returnWorkout.setTitle("Franzose erfindet genialen ausziehbaren einbruchsicheren Wohnwagen. Beauer: XXXL Design Innenraum.");
        returnWorkout.setDescription("0:00 Vorstellung Wohnwagen und Erfinder\n" +
                "6:08 Innenraum\n" +
                "13:49 Wohnwagen zusammengefahren\n" +
                "18:28 Kontakt\n" +
                "18:52 Preisliste\n" +
                "\n" +
                "\n" +
                "Wohnwagen Neuheiten 2022");
        returnWorkout.setThumbnailUrl("https://i.ytimg.com/vi/WZBISjfCdCs/sddefault.jpg");
        returnWorkout.setDuration("PT19M23S");

        when(workoutRepo.findById(any())).thenReturn(java.util.Optional.of(returnWorkout));

        //WHEN
        Workout actual = workoutService.getWorkout(workoutId);

        //
        verify(workoutRepo).findById(workoutId);
        assertThat(actual, is(returnWorkout));
    }

    @Test
    void testGetWorkout_elementNotFound() {
        //GIVEN
        String workoutId = "invalidID";

        when(workoutRepo.existsById(workoutId)).thenThrow(NoSuchElementException.class);

        //WHEN
        Assertions.assertThrows(NoSuchElementException.class, () -> {
                workoutService.getWorkout(workoutId);
        });
    }

    @Test
    void testDeleteWorkout_elementNotFound() {
        //GIVEN
        String workoutId = "invalidID";

        when(workoutRepo.existsById(workoutId)).thenThrow(NoSuchElementException.class);

        //WHEN
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            workoutService.deleteWorkout(workoutId);
        });
    }
}