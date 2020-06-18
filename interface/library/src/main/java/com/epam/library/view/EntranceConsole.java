package com.epam.library.view;

import com.epam.library.business.exceptions.UserOperationException;
import com.epam.library.business.UserUtilImpl;
import com.epam.library.business.interfaces.IUserService;
import com.epam.library.model.User;

import java.util.Scanner;

public class EntranceConsole {


    private static IUserService userService = new UserUtilImpl();

    public static User signIn() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\"Библиотека\"");
        System.out.println("1.Регистрация");
        System.out.println("2.Вход");
        String inputData;

        while (true) {
          inputData = scanner.nextLine();
          switch(inputData) {
              case "1":
                    register();
                    return login();
              case "2":
                    return login();
              default:
                  System.err.println("Введите 1 или 2");
                  break;
          }
        }
    }

    private static void register() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Никнейм:");
        String nicknameReg = scanner.nextLine();
        System.out.println("Пароль:");
        String passwordReg = scanner.nextLine();

        if (nicknameReg.isEmpty() || passwordReg.isEmpty()) {
            System.err.println("Пустой никнейм/пароль");
            return;
        }
        try {
            userService.registerNewUser(nicknameReg,passwordReg);
        } catch (UserOperationException e) {
            System.err.println("Не удалось зарегистрировать пользователя");
            return;
        }
        System.out.println("Пользователь успешно зарегистрирован");
    }

    private static User login() {
        while (true) {

            Scanner scanner = new Scanner(System.in);
            System.out.println("Вход в систему");
            System.out.println("Никнейм:");
            String nicknameLog = scanner.nextLine();
            System.out.println("Пароль:");
            String passwordLog = scanner.nextLine();

            try {
                User user =  userService.login(nicknameLog,passwordLog);
                System.out.println("Приветствую, " + user.getNickname());
                String role = user.isAdmin() ? "Администратор" : "Пользователь";
                System.out.println("Ваша роль: " + role);
                return user;
            } catch (UserOperationException e) {
                System.err.println("Не удалось войти в систему: " + e.getMessage());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

}
