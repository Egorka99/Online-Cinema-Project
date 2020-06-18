package com.epam.library.business.interfaces;

import com.epam.library.business.exceptions.UserOperationException;
import com.epam.library.model.User;

public interface IUserService {
    User login(String nickname, String password) throws UserOperationException;
    void registerNewUser(String nickname, String password) throws UserOperationException;
}
