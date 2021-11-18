package de.neuefische.magicmirrorfitness.repo;

import de.neuefische.magicmirrorfitness.model.Workout;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutRepo extends PagingAndSortingRepository<Workout, String> {

    List<Workout> findAll();
}
