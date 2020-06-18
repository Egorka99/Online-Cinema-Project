package com.epam.library.business;

import com.epam.library.business.interfaces.IHistoryService;
import com.epam.library.dao.HistoryAccessImpl;
import com.epam.library.dao.interfaces.IBaseAccessService;
import com.epam.library.model.History;

public class HistoryUtilImpl implements IHistoryService {

    IBaseAccessService<History> historyAccessService = new HistoryAccessImpl();

    @Override
    public void addHistory(int userId, String actionText) {
        History history = new History(historyAccessService.getMaxId() + 1,userId,actionText);
        historyAccessService.addNewEntity(history);
    }

    @Override
    public void deleteHistory(int historyId) {
        historyAccessService.deleteEntity(historyId);
    }
}
