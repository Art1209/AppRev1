package AppRev1.highLevelApp.persistence.service;

import AppRev1.highLevelApp.persistence.entity.Person;
import AppRev1.highLevelApp.persistence.entity.Role;
import AppRev1.highLevelApp.persistence.entity.Token;
import AppRev1.highLevelApp.persistence.repository.RoleRepository;
import AppRev1.highLevelApp.persistence.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by aalbutov on 26.10.2017.
 */
@Service
public class TokenService {
    @Autowired
    TokenRepository tokenRepository;

    @Transactional
    public List<Token> getTokenByPersonId(Person id){
        return tokenRepository.findByPersonId(id);
    }

    @Transactional
    public List<Token> getAll(){
        return tokenRepository.findAll();
    }

    @Transactional
    public void addToken(Token token){
        tokenRepository.saveAndFlush(token);
    }

    @Transactional
    public void updateToken(Token token){
        tokenRepository.saveAndFlush(token);
    }

    @Transactional
    public void deleteToken(Token token){
        tokenRepository.delete(token);
    }

    @Transactional
    public void deleteToken(Long id){
        tokenRepository.delete(id);
    }
    @Transactional
    public Boolean tokenExists(Long id){
        return tokenRepository.exists(id);
    }
}
