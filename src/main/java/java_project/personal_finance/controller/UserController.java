package java_project.personal_finance.controller;

import java_project.personal_finance.model.UserModel;
import java_project.personal_finance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable UUID id){
        userService.deleteUser(id);
        return ResponseEntity.ok().body("User deleted");
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserModel>> listAll(){
        return ResponseEntity.ok(userService.listAll());
    }

}
