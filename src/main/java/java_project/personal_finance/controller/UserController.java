package java_project.personal_finance.controller;

import java_project.personal_finance.dto.UserDto;
import java_project.personal_finance.model.UserModel;
import java_project.personal_finance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteUser(){
        userService.deleteUser();
        return ResponseEntity.ok().body("User deleted");
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserModel>> listAll(){
        return ResponseEntity.ok(userService.listAll());
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateUser(@RequestBody UserDto userDto) {
        userService.updateUser(userDto);
        return ResponseEntity.ok().body("User updated");
    }

}
