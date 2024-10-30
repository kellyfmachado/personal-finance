package java_project.personal_finance.service;

import java_project.personal_finance.dto.UserDto;
import java_project.personal_finance.model.UserModel;
import java_project.personal_finance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void deleteUser (){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        UserModel userModel = (UserModel) userRepository.findByEmail(userEmail);
        userRepository.deleteById(userModel.getId());
    }

    public UserModel authenticatedUser (){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        return (UserModel) userRepository.findByEmail(userEmail);
    }

    public List<UserModel> listAll(){
        return userRepository.findAll();
    }

    public void updateUser(UserDto userDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        UserModel userModel = (UserModel) userRepository.findByEmail(userEmail);

        if (!userDto.getName().isEmpty()){
            userModel.setName(userDto.getName());
        }

        if (!userDto.getEmail().isEmpty()){
            userModel.setEmail(userDto.getEmail());
        }

        if (!userDto.getPassword().isEmpty()){
            userModel.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword())); // Encrypt the new password
        }

        userModel.setUpdatedAt(new Date());
        userRepository.save(userModel);
    }
}
