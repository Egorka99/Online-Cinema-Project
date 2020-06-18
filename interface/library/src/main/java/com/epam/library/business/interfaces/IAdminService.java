package com.epam.library.business.interfaces;

import com.epam.library.business.exceptions.UserOperationException;
import com.epam.library.model.History;

import java.util.List;

public interface IAdminService extends IUserService {
    void blockUser(int userId);
    List<History> getUserHistory(int userId) throws UserOperationException;
}
