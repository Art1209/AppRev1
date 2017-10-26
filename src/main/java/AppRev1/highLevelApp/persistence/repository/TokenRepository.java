package AppRev1.highLevelApp.persistence.repository;

import AppRev1.highLevelApp.persistence.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by aalbutov on 26.10.2017.
 */
@Repository
public interface TokenRepository extends JpaRepository<Token,Long> {
    @Query("select b from Token b where b.personId = :personId")
        //value="SELECT a FROM tokens WHERE a.login = ?1 LIMIT 1", nativeQuery = true
    List<Token> findByPersonId(@Param("personId") Long personId);
}
