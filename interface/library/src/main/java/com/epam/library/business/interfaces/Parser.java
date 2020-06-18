package com.epam.library.business.interfaces;

import com.epam.library.model.Book;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface Parser {
   List<Book> parse(String filePath) throws IOException, CsvException, ParseException;
}
