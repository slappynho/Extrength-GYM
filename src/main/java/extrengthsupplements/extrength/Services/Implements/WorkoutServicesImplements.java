package extrengthsupplements.extrength.Services.Implements;


import extrengthsupplements.extrength.Services.WorkoutServices;
import extrengthsupplements.extrength.models.Workout;
import extrengthsupplements.extrength.repositories.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutServicesImplements implements WorkoutServices {
    @Autowired
    private WorkoutRepository workoutRepository;

    @Override
    public List<Workout> getWorkouts() {
        return workoutRepository.findAll();
    }
    @Override
    public Workout getWorkoutById(Long id){
        return workoutRepository.findById(id).get();
    }

    @Override
    public void saveWorkout(Workout workout) {
        workoutRepository.save(workout);
    }


}
