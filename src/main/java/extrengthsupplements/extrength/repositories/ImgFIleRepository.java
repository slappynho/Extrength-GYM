package extrengthsupplements.extrength.repositories;


import extrengthsupplements.extrength.models.ImgFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImgFIleRepository extends JpaRepository<ImgFile, Long> {

}
