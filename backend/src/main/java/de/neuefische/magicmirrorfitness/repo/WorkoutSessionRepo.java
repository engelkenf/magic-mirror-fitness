package de.neuefische.magicmirrorfitness.repo;

import de.neuefische.magicmirrorfitness.model.WorkoutSession;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutSessionRepo extends PagingAndSortingRepository<WorkoutSession, String> {

    List<WorkoutSession> findAll();
}