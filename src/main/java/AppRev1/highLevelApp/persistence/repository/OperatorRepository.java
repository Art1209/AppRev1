package AppRev1.highLevelApp.persistence.repository;


import AppRev1.highLevelApp.persistence.entity.MOperator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperatorRepository extends JpaRepository<MOperator, Long> {
}
