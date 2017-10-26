package AppRev1.highLevelApp.persistence.repository;

import AppRev1.highLevelApp.persistence.entity.Person;
import AppRev1.highLevelApp.persistence.entity.Token;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query("select b from Person b where b.login = :login")
    //value="SELECT a FROM persons WHERE a.login = ?1 LIMIT 1", nativeQuery = true
    List<Person> findByLogin(@Param("login") String login, Pageable pageable);
}
