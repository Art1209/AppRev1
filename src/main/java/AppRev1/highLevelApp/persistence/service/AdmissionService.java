package AppRev1.highLevelApp.persistence.service;

import AppRev1.highLevelApp.persistence.entity.Admission;
import AppRev1.highLevelApp.persistence.repository.AdmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class AdmissionService {

    @Autowired
    AdmissionRepository admissionRepository;

    @Transactional
    public Admission getAdmission(Long id){
        return admissionRepository.findOne(id);
    }

    @Transactional
    public List<Admission> getAll(){
        return admissionRepository.findAll();
    }

    @Transactional
    public void addAdmission(Admission admission){
        admissionRepository.saveAndFlush(admission);
    }

    @Transactional
    public void updateAdmission(Admission admission){
        admissionRepository.saveAndFlush(admission);
    }

    @Transactional
    public void deleteAdmission(Admission admission){
        admissionRepository.delete(admission);
    }

    @Transactional
    public void deleteAdmission(Long id){
        admissionRepository.delete(id);
    }

    @Transactional
    public Boolean admissionExists(Long id){
        return admissionRepository.exists(id);
    }
}
