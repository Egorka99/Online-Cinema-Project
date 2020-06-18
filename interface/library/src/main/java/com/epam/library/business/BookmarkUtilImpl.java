package com.epam.library.business;

import com.epam.library.business.exceptions.BookOperationException;
import com.epam.library.business.interfaces.IBookmarkService;
import com.epam.library.dao.BookAccessImpl;
import com.epam.library.dao.BookmarkAccessImpl;
import com.epam.library.dao.interfaces.IAdvancedBookAccessService;
import com.epam.library.dao.interfaces.IBaseAccessService;
import com.epam.library.model.Bookmark;

import java.util.List;

public class BookmarkUtilImpl implements IBookmarkService {
    private IBaseAccessService<Bookmark> bookmarkAccessService = new BookmarkAccessImpl();
    private IAdvancedBookAccessService bookAccessService = new BookAccessImpl();

    @Override
    public void addNewBookmark(int userId, int bookId, int pageNumber) throws BookOperationException {
        if (bookAccessService.getEntity(bookId).getPageCount() < pageNumber) {
            throw new BookOperationException("Страница с закладкой в книге не может быть больше последней страницы");
        }
        if (pageNumber < 1) {
            throw new BookOperationException("Не бывает книги с меньше чем одной страницей");
        }
        Bookmark bookmark = new Bookmark(bookmarkAccessService.getMaxId() + 1,userId, bookId, pageNumber);
        bookmarkAccessService.addNewEntity(bookmark);
    }

    @Override
    public void deleteBookmark(int bookmarkId) {
        bookmarkAccessService.deleteEntity(bookmarkId);
    }

    @Override
    public List<Bookmark> getAllBookmarks() {
       return bookmarkAccessService.getAllEntities();
    }
}
