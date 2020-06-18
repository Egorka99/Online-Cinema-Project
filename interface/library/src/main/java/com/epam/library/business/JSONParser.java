package com.epam.library.business;

import com.epam.library.business.interfaces.Parser;
import com.epam.library.model.Book;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.IOException; 
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class JSONParser implements Parser {
    Gson gson;

    public JSONParser()
    {
        gson = new GsonBuilder().setDateFormat("dd.mm.yyyy").create();
    }

    @Override
    public List<Book> parse(String filePath) throws IOException {
        return Arrays.asList(gson.fromJson(new FileReader(filePath, StandardCharsets.UTF_8), Book[].class));
    }
}
