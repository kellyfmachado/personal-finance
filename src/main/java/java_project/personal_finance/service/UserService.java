package java_project.personal_finance.service;

import java_project.personal_finance.model.UserModel;
import java_project.personal_finance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void deleteUser (UUID id){
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
        }
        else {
            throw new RuntimeException("User not found with id " + id);
        }
    }

    public List<UserModel> listAll(){
        return userRepository.findAll();
    }
}
