package com.epam.library.dao.interfaces;

import com.epam.library.model.History;
import com.epam.library.model.User;

import java.util.List;

public interface IAdminAccessService extends IAdvancedUserAccessService {
    void defineUserIsBlockedTrue(User user);
    List<History> getUserHistory(User user);
}
