package com.truefan.taskmgmt.repository;

import com.truefan.taskmgmt.domain.User;
import com.truefan.taskmgmt.exception.UserAlreadyCreatedException;
import com.truefan.taskmgmt.exception.UserNotCreatedException;

import java.util.Set;

public interface IUserRepo {
    User register(String name, String email) throws UserAlreadyCreatedException;

    User getUserById(Integer id) throws UserNotCreatedException;

    User getUserByEmail(String email) throws UserNotCreatedException;

    Set<Integer> getRegisteredUsersId();
}
