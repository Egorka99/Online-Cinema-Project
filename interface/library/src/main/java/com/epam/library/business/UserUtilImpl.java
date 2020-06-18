package com.epam.library.business;

import com.epam.library.business.exceptions.UserOperationException;
import com.epam.library.business.interfaces.IUserService;
import com.epam.library.dao.UserAccessImpl;
import com.epam.library.dao.interfaces.IAdvancedUserAccessService;
import com.epam.library.model.User;

public class UserUtilImpl implements IUserService {

    IAdvancedUserAccessService userAccessService = new UserAccessImpl();

    @Override
    public User login(String nickname, String password) throws UserOperationException {
        User user = userAccessService.getUserByNickname(nickname);
        if (user == null) {
            throw new UserOperationException("Такого пользователя не существует");
        }
        if (userAccessService.getUserByNickname(nickname).getPassword().equals(password)) {
            if (user.isBlocked()) {
                throw new UserOperationException("Данный аккаунт заблокирован");
            }
            return user;
        }
        else {
            throw new UserOperationException("Не удалось получить пользователя");
        }
    }

    @Override
    public void registerNewUser(String nickname, String password) throws UserOperationException {
        User user = new User(userAccessService.getMaxId() + 1, nickname, password, false, false);
        if (userAccessService.getUserByNickname(user.getNickname()) == null) {
             userAccessService.addNewEntity(user);
        }
        else {
            throw new UserOperationException("Не удалось зарегистрировать пользователя");
        }
    }
}
