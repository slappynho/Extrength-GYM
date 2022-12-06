package extrengthsupplements.extrength.controllers;

import extrengthsupplements.extrength.DTO.WorkoutDTO;
import extrengthsupplements.extrength.Services.ClientServices;
import extrengthsupplements.extrength.Services.WorkoutServices;
import extrengthsupplements.extrength.models.Client;
import extrengthsupplements.extrength.models.SubscriptionTypes;
import extrengthsupplements.extrength.models.Workout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class WorkoutController {

    @Autowired
    private WorkoutServices workoutServices;
    @Autowired
    private ClientServices clientServices;

    @GetMapping("/workouts")
    public List<WorkoutDTO> getWorkouts(){
        return workoutServices.getWorkouts().stream().map(WorkoutDTO::new).collect(Collectors.toList());
    }

    @PostMapping("/workouts")
    public ResponseEntity<Object> addWorkout(Authentication authentication, @RequestParam Long id){
        Client client = clientServices.findByEmail(authentication.getName());
        if (client == null){
            return new ResponseEntity<>("", HttpStatus.FORBIDDEN);

        }
        if (client.getBillSubscription() == null){
            return new ResponseEntity<>("You do not have a subscription", HttpStatus.FORBIDDEN);

        }
        if (client.getWorkouts().size() >= 1 && client.getBillSubscription().getSubscription().getSubscriptionTypes() == SubscriptionTypes.BASIC ){
            return new ResponseEntity<>("Too many workouts for your subscription", HttpStatus.FORBIDDEN);
        }
        if (client.getWorkouts().size() >= 3 && client.getBillSubscription().getSubscription().getSubscriptionTypes() == SubscriptionTypes.STANDARD ){
            return new ResponseEntity<>("Too many workouts for your subscription", HttpStatus.FORBIDDEN);
        }




        Workout workout = workoutServices.getWorkoutById(id);
        client.addWorkouts(workout);
        workout.addClients(client);
        workoutServices.saveWorkout(workout);
        clientServices.saveClient(client);
        return new ResponseEntity<>("Subscribed", HttpStatus.CREATED);
    }
}