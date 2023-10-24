package menu;

import entities.User;
import service.UserService;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    int select;

    public void menuDisplay(Scanner scanner, ArrayList<User> users, UserService userService, Menu menu,int select) {
        while (true) {
            System.out.println("===== MENU =====");
            System.out.println("1. Đăng nhập");
            System.out.println("2. Đăng ký");
            System.out.println("0. Exit");
            System.out.print("Chọn: ");
            select = Integer.parseInt(scanner.nextLine());
            if (select == 0) {
                System.out.println("Kết thúc chương trình");
                break;
            }
            menu(scanner, users, userService,menu,select);
        }
    }

    public void menu(Scanner scanner, ArrayList<User> users, UserService userService,Menu menu, int select) {
        switch (select) {
            case 1:
                userService.login(scanner,users, menu,userService,select);
                break;
            case 2:
                userService.register(scanner, users);
                break;
        }
    }

    public void menuLoginDisplay(Scanner scanner, ArrayList<User> users, UserService userService,Menu menu, int select) {

        while (true) {
            System.out.println("===== MENU ĐÃ ĐĂNG NHẬP =====");
            System.out.println("1. Thay đổi username");
            System.out.println("2. Thay đổi email");
            System.out.println("3. Thay đổi password");
            System.out.println("4. Đăng xuất");
            System.out.println("0. Exit");
            System.out.print("Chọn: ");
            select = Integer.parseInt(scanner.nextLine());
            if (select == 0) {
                System.out.println("kết thúc chương trình");
                break;

            }
            menuLogin(scanner,users,userService,menu,select);
        }
    }

    public void menuLogin(Scanner scanner, ArrayList<User> users, UserService userService, Menu menu,int select) {
        {
            switch (select) {
                case 1:
                    userService.changeUsername(scanner, users);
                    break;
                case 2:
                    userService.changeEmail(scanner, users);
                    break;
                case 3:
                    userService.resetPassword(scanner, users,userService,menu,select);
                    break;
                case 4:
                    System.out.println("Đăng xuất thành công");
                    menuDisplay(scanner, users, userService,menu,select);
            }
            while (select == 0) ;
        }
    }
}
