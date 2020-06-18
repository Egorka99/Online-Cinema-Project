package com.epam.library.view;

import com.epam.library.business.*;
import com.epam.library.business.exceptions.AuthorOperationException;
import com.epam.library.business.exceptions.BookOperationException;
import com.epam.library.business.exceptions.UserOperationException;
import com.epam.library.business.interfaces.*;
import com.epam.library.dao.AuthorAccessImpl;
import com.epam.library.dao.BaseAccessImpl;
import com.epam.library.model.Author;
import com.epam.library.model.Book;
import com.epam.library.model.History;
import com.epam.library.model.User;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class OperationsConsole {
    User user;

    IBookService bookService;
    IAuthorService authorService;
    IBookmarkService bookmarkService;
    ISearchBookService searchBookService;
    IUserService userService;
    IAdminService adminService;
    IHistoryService historyService;

    BaseAccessImpl<Author> authorAccessService;

    Parser parser;


    public OperationsConsole(User user) {
        this.user = user;
        bookService = new BookUtilImpl();
        authorService = new AuthorUtilImpl();
        bookmarkService = new BookmarkUtilImpl();
        searchBookService = new SearchBookUtilImpl();
        userService = new UserUtilImpl();
        adminService = new AdminUtilImpl();
        historyService = new HistoryUtilImpl();
        authorAccessService = new AuthorAccessImpl();
    }

    public void selectOperation() {
        Scanner scanner = new Scanner(System.in);

        int countOfOperations;

        if (user.isAdmin()) {
            showUserOperations();
            showAdminOperation();
            countOfOperations = 17;
        } else {
            showUserOperations();
            countOfOperations = 14;
        }

        while(true) {
            String numberOfOperation = scanner.nextLine();
            if (!tryParseInt(numberOfOperation) && !numberOfOperation.equals("exit")) {
                System.err.println("Введите число");
                continue;
            }
            if (!user.isAdmin() && !numberOfOperation.equals("exit") && Integer.parseInt(numberOfOperation) > countOfOperations) {
                System.err.println("Выберите операцию от 1 до " + countOfOperations);
                continue;
            }
            switch (numberOfOperation) {
                case "1":
                    addBook();
                    System.out.println("Выберите операцию: ");
                    break;
                case "2":
                    deleteBook();
                    System.out.println("Выберите операцию: ");
                    break;
                case "3":
                    addAuthor();
                    System.out.println("Выберите операцию: ");
                    break;
                case "4":
                    deleteAuthor();
                    System.out.println("Выберите операцию: ");
                    break;
                case "5":
                    addBooksFromCSV();
                    System.out.println("Выберите операцию: ");
                    break;
                case "6":
                    addBooksFromJson();
                    System.out.println("Выберите операцию: ");
                    break;
                case "7":
                    addBookmark();
                    System.out.println("Выберите операцию: ");
                    break;
                case "8":
                    deleteBookmark();
                    System.out.println("Выберите операцию: ");
                    break;
                case "9":
                    searchBooksByTitle();
                    System.out.println("Выберите операцию: ");
                    break;
                case "10":
                    searchBooksByAuthorName();
                    System.out.println("Выберите операцию: ");
                    break;
                case "11":
                    searchBooksByISBN();
                    System.out.println("Выберите операцию: ");
                    break;
                case "12":
                    searchBooksByYearsRange();
                    System.out.println("Выберите операцию: ");
                    break;
                case "13":
                    searchBooksBySeveralWays();
                    System.out.println("Выберите операцию: ");
                    break;
                case "14":
                    searchBooksByUserBookmark();
                    System.out.println("Выберите операцию: ");
                    break;
                case "15":
                    registerNewUser();
                    System.out.println("Выберите операцию: ");
                    break;
                case "16":
                    blockUser();
                    System.out.println("Выберите операцию: ");
                    break;
                case "17":
                    watchUserHistory();
                    System.out.println("Выберите операцию: ");
                    break;
                case "exit":
                    break;
            }

            if (numberOfOperation.equals("exit")) {
                break;
            }
        }

    }

    private void showUserOperations() {
        System.out.println("Действия пользователя: ");
        System.out.println("1.Добавить книгу");
        System.out.println("2.Удалить книгу");
        System.out.println("3.Добавить автора");
        System.out.println("4.Удалить автора и все его книги");
        System.out.println("5.Добавить книги из CSV файла");
        System.out.println("6.Добавить книги из JSON файла");
        System.out.println("7.Добавить закладку");
        System.out.println("8.Удалить закладку");
        System.out.println("9.Найти книгу по названию");
        System.out.println("10.Найти книги по имени автора");
        System.out.println("11.Найти книги по ISBN");
        System.out.println("12.Найти книги по диапазону годов выпуска");
        System.out.println("13.Найти книги по году, количеству страниц и наименованию");
        System.out.println("14.Список книг, в которых есть мои закладки");
        System.out.println("Введите exit, чтобы выйти из программы");
    }

    private void showAdminOperation() {
        System.out.println("Действия администратора: ");
        System.out.println("15.Зарегистрировать нового пользователя");
        System.out.println("16.Заблокировать пользователя");
        System.out.println("17.Смотреть историю действий пользователя");
    }

    private void addBook() {
        Scanner scanner = new Scanner(System.in);

        String bookName;
        int releaseYear;
        String isbn;
        String publisher;
        int pageCount;
        int authorId;

        try {
            System.out.println("Название книги: ");
            bookName = scanner.nextLine();
            System.out.println("Год издания: ");
            releaseYear = scanner.nextInt();
            scanner.nextLine();
            System.out.println("ISBN: ");
            isbn = scanner.nextLine();
            System.out.println("Iздатель: ");
            publisher = scanner.nextLine();
            System.out.println("Количество страниц: ");
            pageCount = scanner.nextInt();
            System.out.println("ID автора: ");
            authorId = scanner.nextInt();
        }
        catch (InputMismatchException ex) {
            System.err.println("Неверный ввод данных");
            return;
        }

        try {
            bookService.addNewBook(bookName,releaseYear,isbn,publisher,pageCount,authorAccessService.getEntity(authorId));
            System.out.println("Книга успешно добавлена!");
            historyService.addHistory(user.getId(),"add new book");
        } catch (BookOperationException e) {
            System.err.println("Не удалось добавить книгу");
        }
    }

    private void deleteBook() {
        Scanner scanner = new Scanner(System.in);
        int bookId;
        try {
            System.out.println("ID книги: ");
            bookId = scanner.nextInt();
        }
        catch (InputMismatchException ex) {
            System.err.println("Неверный ввод данных");
            return;
        }
        bookService.deleteBook(bookId);
        System.out.println("Книга успешно удалена!");
        historyService.addHistory(user.getId(),"delete book");
    }

    private void addAuthor() {

        Scanner scanner = new Scanner(System.in);

        String firstName;
        String secondName;
        String lastName;
        Date dateOfBirth;

        try {
            System.out.println("Iмя автора: ");
            firstName = scanner.nextLine();
            System.out.println("Отчество автора: ");
            secondName = scanner.nextLine();
            System.out.println("Фамилия автора: ");
            lastName = scanner.nextLine();
            System.out.println("Дата рождения(yyyy-mm-dd): ");
            dateOfBirth = Date.valueOf(scanner.nextLine());
        }
        catch (IllegalArgumentException ex) {
            System.err.println("Неверные данные");
            return;
        }
        authorService.addNewAuthor(firstName,secondName,lastName,dateOfBirth);
        System.out.println("Автор успешно добавлен");
        historyService.addHistory(user.getId(),"add author");

    }

    private void deleteAuthor() {
        Scanner scanner = new Scanner(System.in);
        int authorId;
        try {
            System.out.println("ID автора: ");
            authorId = scanner.nextInt();
        }
        catch (InputMismatchException ex) {
            System.err.println("Неверный ввод данных");
            return;
        }

        try {
            authorService.deleteAuthorWithHisBooks(authorId);
        } catch (AuthorOperationException e) {
            System.err.println(e.getMessage());
            return;
        }
        System.out.println("Автор и его книги успешно удалены!");
        historyService.addHistory(user.getId(),"delete author");
    }

    private void addBookmark() {
        Scanner scanner = new Scanner(System.in);

        int userId;
        int bookId;
        int pageNumber;

        try {
            userId = user.getId();
            System.out.println("ID книги: ");
            bookId = scanner.nextInt();
            System.out.println("Номер страницы: ");
            pageNumber = scanner.nextInt();
        }
        catch (InputMismatchException ex) {
            System.err.println("Неверный ввод данных");
            return;
        }

        try {
            bookmarkService.addNewBookmark(userId,bookId,pageNumber);
            System.out.println("Закладка успешно добавлена");
        } catch (BookOperationException e) {
            System.err.println("Не удалось добавить закладку");
        }

        historyService.addHistory(user.getId(),"add bookmark");

    }

    private void showBooks(List<Book> listOfBooks) {
        for (Book book : listOfBooks) {
            System.out.println("Книга № " + book.getISBN() + ": " + book.getBookName());
        }

    }

    private void deleteBookmark() {
        Scanner scanner = new Scanner(System.in);
        int bookmarkId;
        try {
            System.out.println("ID закладки: ");
            bookmarkId = scanner.nextInt();
        }
        catch (InputMismatchException ex) {
            System.err.println("Неверный ввод данных");
            return;
        }

        bookmarkService.deleteBookmark(bookmarkId);
        System.out.println("Закладка успешно удалена!");
        historyService.addHistory(user.getId(),"delete bookmark");
    }

    private void searchBooksByTitle() {
        Scanner scanner = new Scanner(System.in);
        String partOfName;
        System.out.println("Введите название книги (или часть названия)");
        partOfName = scanner.nextLine();
        showBooks(searchBookService.searchBooksByPartOfName(partOfName));
        historyService.addHistory(user.getId(),"search books");
    }

    private void searchBooksByAuthorName() {
        Scanner scanner = new Scanner(System.in);
        String partOfAuthorName;
        System.out.println("Введите имя автора книги (или часть имени)");
        partOfAuthorName = scanner.nextLine();
        showBooks(searchBookService.searchBooksByPartOfAuthorName(partOfAuthorName));
        historyService.addHistory(user.getId(),"search books");

    }

    private void searchBooksByISBN() {
        Scanner scanner = new Scanner(System.in);
        String isbn;
        System.out.println("Введите ISBN книги: ");
        isbn = scanner.nextLine();
        try {
            showBooks(searchBookService.searchBooksByIsbn(isbn));
        } catch (BookOperationException e) {
            System.err.println("Не удалось найти книгу по ISBN");
        }
        historyService.addHistory(user.getId(),"search books");

    }

    private void searchBooksByYearsRange() {
        Scanner scanner = new Scanner(System.in);
        int startYear;
        int endYear;
        try {
            System.out.println("Введите начальный год:");
            startYear = scanner.nextInt();
            System.out.println("Введите конечный год:");
            endYear = scanner.nextInt();
        }
        catch (InputMismatchException ex) {
            System.err.println("Неверный ввод данных");
            return;
        }

        try {
            showBooks(searchBookService.searchBooksByReleaseYearRange(startYear,endYear));
        } catch (BookOperationException e) {
            System.err.println("Не удалось найти книгу по диапазону годов");
        }
        historyService.addHistory(user.getId(),"search books");
    }

    private void searchBooksBySeveralWays() {
        Scanner scanner = new Scanner(System.in);
        int year;
        int pageCount;
        String bookTitle;

        try {
            System.out.println("Введите год издания:");
            year = scanner.nextInt();
            System.out.println("Введите количество страниц:");
            pageCount = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Введите название книги(или часть названия):");
            bookTitle = scanner.nextLine();
        }
        catch (InputMismatchException ex) {
            System.err.println("Неверный ввод данных");
            return;
        }
        showBooks(searchBookService.searchBooksBySeveralWays(year,pageCount,bookTitle));
        historyService.addHistory(user.getId(),"search books");
    }

    private void searchBooksByUserBookmark() {
        Scanner scanner = new Scanner(System.in);
        int userID;
        try {
            System.out.println("Введите ID пользователя: ");
            userID = scanner.nextInt();
        }
        catch (InputMismatchException ex) {
            System.err.println("Неверный ввод данных");
            return;
        }

        showBooks(searchBookService.searchBooksByUserBookmark(userID));
        historyService.addHistory(user.getId(),"search books");
    }

    private void registerNewUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Никнейм:");
        String nicknameReg = scanner.nextLine();
        System.out.println("Пароль:");
        String passwordReg = scanner.nextLine();
        try {
            userService.registerNewUser(nicknameReg,passwordReg);
        } catch (UserOperationException e) {
            System.err.println("Не удалось зарегистрировать пользователя");
            return;
        }
        System.out.println("Пользователь успешно зарегистрирован");

    }

    private void blockUser() {
        Scanner scanner = new Scanner(System.in);
        int userID;
        try {
            System.out.println("Введите ID пользователя: ");
            userID = scanner.nextInt();
        }
        catch (InputMismatchException ex) {
            System.err.println("Неверный ввод данных");
            return;
        }
        adminService.blockUser(userID);
        System.out.println("Пользователь успешно заблокирован");
    }

    private void watchUserHistory() {
        Scanner scanner = new Scanner(System.in);
        int userID;
        try {
            System.out.println("Введите ID пользователя: ");
            userID = scanner.nextInt();
        }
        catch (InputMismatchException ex) {
            System.err.println("Неверный ввод данных");
            return;
        }

        try {
            showUserActions(adminService.getUserHistory(userID));
        } catch (UserOperationException e) {
            System.err.println(e.getMessage());
        }
    }

    private void showUserActions(List<History> listOfHistory) {
        for (History history : listOfHistory) {
            System.out.println("Пользователь № " + history.getUserId() + ". Действие: " + history.getActionText());
        }
    }

    private void addBooksFromJson() {
        parser = new JSONParser();
        Scanner scanner = new Scanner(System.in);
        String filePath;
        System.out.println("Укажите путь к файлу");
        filePath = scanner.nextLine();

        try {
            bookService.addBooksFromList(parser.parse(filePath));
            System.out.println("Добавлено!");
        } catch (IOException e) {
            System.err.println("Не найден путь к файлу");
        }
        catch (CsvException e) {
            System.err.println("Не удалось добавить книги из файла");
        }
        catch (ParseException ex) {
            System.err.println("Ошибка чтения даты рождения автора");
        }
        historyService.addHistory(user.getId(),"add books from json");
    }

    private void addBooksFromCSV() {
        parser = new CSVParser();
        Scanner scanner = new Scanner(System.in);
        String filePath;
        System.out.println("Укажите путь к файлу");
        filePath = scanner.nextLine();

        try {
            bookService.addBooksFromList(parser.parse(filePath));
            System.out.println("Добавлено!");
        } catch (IOException e) {
            System.err.println("Не найден путь к файлу");
        }
        catch (CsvException e) {
            System.err.println("Не удалось добавить книги из файла");
        }
        catch (ParseException ex) {
            System.err.println("Ошибка чтения даты рождения автора");
        }
        historyService.addHistory(user.getId(),"add books from csv");
    }

    private static boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


}
