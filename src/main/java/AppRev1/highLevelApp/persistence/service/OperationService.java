package AppRev1.highLevelApp.persistence.service;

import AppRev1.highLevelApp.persistence.entity.MOperation;
import AppRev1.highLevelApp.persistence.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OperationService {

    @Autowired
    OperationRepository operationRepository;

    @Transactional
    public MOperation getOperation(Long id){
        return operationRepository.findOne(id);
    }

    @Transactional
    public List<MOperation> getAll(){
        return operationRepository.findAll();
    }

    @Transactional
    public void addOperation(MOperation operation){
        operationRepository.saveAndFlush(operation);
    }

    @Transactional
    public void updateOperation(MOperation operation){
        operationRepository.saveAndFlush(operation);
    }

    @Transactional
    public void deleteOperation(Long id){
        operationRepository.delete(id);
    }

    @Transactional
    public Boolean operationExists(Long id){
        return operationRepository.exists(id);
    }
}
