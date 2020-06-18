package com.epam.library.view;

public class Main {
    public static void main(String[] args) {
        OperationsConsole operationsConsole = new OperationsConsole(EntranceConsole.signIn());
        operationsConsole.selectOperation();
    }
}
