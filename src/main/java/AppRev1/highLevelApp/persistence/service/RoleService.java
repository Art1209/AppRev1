package AppRev1.highLevelApp.persistence.service;

import AppRev1.highLevelApp.persistence.entity.Role;
import AppRev1.highLevelApp.persistence.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Transactional
    public Role getRole(Long id){
        return roleRepository.findOne(id);
    }

    @Transactional
    public List<Role> getAll(){
        return roleRepository.findAll();
    }

    @Transactional
    public void addRole(Role role){
        roleRepository.saveAndFlush(role);
    }

    @Transactional
    public void updateRole(Role role){
        roleRepository.saveAndFlush(role);
    }

    @Transactional
    public void deleteRole(Role role){
        roleRepository.delete(role);
    }

    @Transactional
    public void deleteRole(Long id){
        roleRepository.delete(id);
    }
    @Transactional
    public Boolean roleExists(Long id){
        return roleRepository.exists(id);
    }
}
