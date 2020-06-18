package com.epam.library.business.interfaces;

public interface IHistoryService {
    void addHistory(int userId, String actionText);
    void deleteHistory(int historyId);
}
