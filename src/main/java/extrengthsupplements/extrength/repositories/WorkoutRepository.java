package extrengthsupplements.extrength.repositories;


import extrengthsupplements.extrength.models.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface WorkoutRepository extends JpaRepository<Workout,Long> {
}
