package com.epam.library.dao.interfaces;

import com.epam.library.model.User;

public interface IAdvancedUserAccessService extends IBaseAccessService<User> {
    User getUserByNickname(String nickname);
}

