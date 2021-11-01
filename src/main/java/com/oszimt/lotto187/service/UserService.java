package com.oszimt.lotto187.service;

import com.oszimt.lotto187.domain.Role;
import com.oszimt.lotto187.domain.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);//TODO don't allow duplicate usernames
    User getUser(String username);
    List<User> getUsers();
}
