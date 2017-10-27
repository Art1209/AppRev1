package AppRev1.highLevelApp.persistence.service;

import AppRev1.highLevelApp.persistence.entity.Person;
import AppRev1.highLevelApp.persistence.entity.Token;
import AppRev1.highLevelApp.persistence.repository.PersonRepository;
import AppRev1.highLevelApp.persistence.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;
    @Autowired
    TokenRepository tokenRepository;

    @Transactional
    public Person getPerson(Long id){
        return personRepository.findOne(id);
    }

    @Transactional
    public Person getPerson(String login){
        Pageable top = new PageRequest(0, 1);
        List<Person> result = personRepository.findByLogin(login, top);
        return result.get(0);
    }

    @Transactional
    public List<Person> getAll(){
        return personRepository.findAll();
    }

    @Transactional
    public void addPerson(Person person){
        personRepository.saveAndFlush(person);
    }

    @Transactional
    public void updatePerson(Person person){
        personRepository.saveAndFlush(person);
    }

    @Transactional
    public void deletePerson(Person person){
        personRepository.delete(person);
    }

    @Transactional
    public void deletePerson(Long id){
        personRepository.delete(id);
    }

    @Transactional
    public Boolean personExists(Long id){
        return personRepository.exists(id);
    }
}
