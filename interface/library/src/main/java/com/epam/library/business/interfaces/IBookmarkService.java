package com.epam.library.business.interfaces;

import com.epam.library.business.exceptions.BookOperationException;
import com.epam.library.model.Bookmark;

import java.util.List;

public interface IBookmarkService {
   void addNewBookmark(int userId, int bookId, int pageNumber) throws BookOperationException;
   void deleteBookmark(int bookmarkId);
   List<Bookmark> getAllBookmarks();
}
