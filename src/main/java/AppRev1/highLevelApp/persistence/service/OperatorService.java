package AppRev1.highLevelApp.persistence.service;

import AppRev1.highLevelApp.persistence.entity.MOperator;
import AppRev1.highLevelApp.persistence.repository.OperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OperatorService {

    @Autowired
    OperatorRepository operatorRepository;

    @Transactional
    public MOperator getOperator(Long id){
        return operatorRepository.findOne(id);
    }

    @Transactional
    public List<MOperator> getAll(){
        return operatorRepository.findAll();
    }

    @Transactional
    public void addOperator(MOperator mOperator){
        operatorRepository.saveAndFlush(mOperator);
    }

    @Transactional
    public void updateOperator(MOperator mOperator){
        operatorRepository.saveAndFlush(mOperator);
    }

    @Transactional
    public void deleteOperator(MOperator mOperator){
        operatorRepository.delete(mOperator);
    }

    @Transactional
    public void deleteOperator(Long id){
        operatorRepository.delete(id);
    }
    @Transactional
    public Boolean mOperatorExists(Long id){
        return operatorRepository.exists(id);
    }
}
