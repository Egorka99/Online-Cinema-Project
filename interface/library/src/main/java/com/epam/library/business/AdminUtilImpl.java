package com.epam.library.business;

import com.epam.library.business.exceptions.UserOperationException;
import com.epam.library.business.interfaces.IAdminService;
import com.epam.library.dao.AdminAccessImpl;
import com.epam.library.dao.interfaces.IAdminAccessService;
import com.epam.library.model.History;
import com.epam.library.model.User;

import java.util.List;

public class AdminUtilImpl extends UserUtilImpl implements IAdminService {

    IAdminAccessService adminAccessService = new AdminAccessImpl();


    @Override
    public void blockUser(int userId) {
        adminAccessService.defineUserIsBlockedTrue(adminAccessService.getEntity(userId));
    }

    @Override
    public List<History> getUserHistory(int userId) throws UserOperationException {
        User user = adminAccessService.getEntity(userId);
        if (user == null) {
            throw new UserOperationException("Такого пользователя не существует");
        }
        return adminAccessService.getUserHistory(user);
    }

}
