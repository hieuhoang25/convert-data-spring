package com.hicode.convertdata.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.hicode.convertdata.model.User;
import com.hicode.convertdata.repository.UserRepository;
import com.hicode.convertdata.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(String id) {
        UUID uuId = UUID.fromString(id);
        return userRepository.findById(uuId).orElseThrow(() ->  new RuntimeException());
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        User u = findById(user.getId().toString());
        ObjectMapper objectMapper = new ObjectMapper();
        log.info("{}", objectMapper.convertValue(u, User.class));
        return null;
    }

    @Override
    public void delete(String id) {
        User user = findById(id);
        userRepository.delete(user);
    }
}
