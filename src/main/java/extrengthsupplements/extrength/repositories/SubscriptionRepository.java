package extrengthsupplements.extrength.repositories;

import extrengthsupplements.extrength.models.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    Subscription findSubscriptionById(long id);

}
