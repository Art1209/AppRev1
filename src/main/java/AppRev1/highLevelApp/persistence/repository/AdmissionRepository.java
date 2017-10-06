package AppRev1.highLevelApp.persistence.repository;


import AppRev1.highLevelApp.persistence.entity.Admission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdmissionRepository extends JpaRepository<Admission, Long>{
}
