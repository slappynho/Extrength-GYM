package extrengthsupplements.extrength.repositories;

import extrengthsupplements.extrength.models.ProductStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductStorageRepository extends JpaRepository<ProductStorage,Long> {
    ProductStorage findProductStorageById(long id);
}
