package AppRev1.highLevelApp.persistence.repository;

import AppRev1.highLevelApp.persistence.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by aalbutov on 04.10.2017.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query("select b from Person b where b.login = :login")
    Person findByLogin(@Param("login") String login);
}
