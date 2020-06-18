package com.epam.library.business;

import com.epam.library.business.interfaces.Parser;
import com.epam.library.dao.AuthorAccessImpl;
import com.epam.library.dao.interfaces.IBaseAccessService;
import com.epam.library.model.Author;
import com.epam.library.model.Book;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CSVParser implements Parser {


    IBaseAccessService<Author> authorAccessService = new AuthorAccessImpl();

    @Override
    public List<Book> parse(String filePath) throws IOException, CsvException, ParseException {
        return convertStringArrayToBookList(parseIntoStringArray(filePath));
    }

    private List<String[]> parseIntoStringArray(String filePath) throws IOException, CsvException {
        Reader reader = Files.newBufferedReader(Paths.get(filePath));
        CSVReader csvReader = new CSVReader(reader);
        csvReader.skip(1);
        return csvReader.readAll();
    }

    private List<Book> convertStringArrayToBookList(List<String[]> stringArray) throws ParseException {
        List<Book> bookList = new ArrayList<>();
        for (String[] strings : stringArray) {
            Author author = new Author(
                Integer.MAX_VALUE,
                strings[5],
                strings[6],
                strings[7],
                Date.valueOf(new SimpleDateFormat("yyyy-mm-dd")
                            .format(new SimpleDateFormat("dd.mm.yyyy")
                                    .parse(strings[8])))

            );
            Book book = new Book(
                        Integer.MAX_VALUE,
                        strings[0],
                        Integer.parseInt(strings[1]),
                        strings[3],
                        strings[4],
                        Integer.parseInt(strings[2]),
                        author
            );
            bookList.add(book);
        }
        return bookList;
    }


}
