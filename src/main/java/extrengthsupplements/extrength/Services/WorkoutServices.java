package extrengthsupplements.extrength.Services;

import extrengthsupplements.extrength.models.Workout;

import java.util.List;

public interface WorkoutServices {
    public List<Workout> getWorkouts();
    public Workout getWorkoutById(Long id);
    public void saveWorkout(Workout workout);
}
