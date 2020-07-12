package com.truefan.taskmgmt.repository;

import com.truefan.taskmgmt.domain.User;
import com.truefan.taskmgmt.exception.UserAlreadyCreatedException;
import com.truefan.taskmgmt.exception.UserNotCreatedException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Repository
public class UserRepo implements IUserRepo {

    private final Map<Integer, User> USERS_BY_ID = new HashMap<>();
    private final Map<String, Integer> USERS_BY_EMAIL = new HashMap<>();

    @Override
    public User register(String name, String email) throws UserAlreadyCreatedException {
        if (USERS_BY_EMAIL.get(email) != null) {
            throw new UserAlreadyCreatedException("User already exists for email " + email);
        }
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        USERS_BY_ID.put(user.getId(), user);
        USERS_BY_EMAIL.put(email, user.getId());
        return user;
    }

    @Override
    public User getUserById(Integer id) throws UserNotCreatedException {
        User user = USERS_BY_ID.get(id);
        if (user == null) {
            throw new UserNotCreatedException("No user exists for id " + id);
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) throws UserNotCreatedException {
        Integer id = USERS_BY_EMAIL.get(email);
        if (id == null) {
            throw new UserNotCreatedException("No user exists for email " + email);
        }
        return USERS_BY_ID.get(id);
    }

    @Override
    public Set<Integer> getRegisteredUsersId() {
        return USERS_BY_ID.keySet();
    }
}
