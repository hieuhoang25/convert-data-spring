package com.hicode.convertdata.controller;

import com.hicode.convertdata.model.User;
import com.hicode.convertdata.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") String id){
        return ResponseEntity.ok(userService.findById(id));
    }
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody User user){
        return ResponseEntity.ok(userService.create(user));
    }
    @PutMapping()
    public ResponseEntity<?> update(@RequestBody User user){
        return ResponseEntity.ok(userService.update(user));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")  String id){
        userService.delete(id);
        return ResponseEntity.ok().build();
    }
}
