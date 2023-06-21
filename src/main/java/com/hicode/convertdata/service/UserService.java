package com.hicode.convertdata.service;

import com.hicode.convertdata.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<User> findAll();

    User findById(String id);

    User create(User user);

    User update(User user);

    void delete(String id);
}
