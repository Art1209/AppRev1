package AppRev1.highLevelApp.persistence.repository;

import AppRev1.highLevelApp.persistence.entity.MOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<MOperation, Long> {
}
