package service;

import entities.User;
import menu.Menu;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {
    public void register(Scanner scanner, ArrayList<User> users) {
        System.out.println("=========Đăng Ký=========");
        String username;
        String password = "";
        String email = "";

        while (true) {
            System.out.print("Nhập username: ");
            username = scanner.nextLine();

            boolean usernameExists = false;

            for (User user : users) {
                if (user.getUsername().equals(username)) {
                    usernameExists = true;
                    System.out.println("Username đã tồn tại. Vui lòng chọn username khác.");
                    break;
                }
            }

            if (!usernameExists) {
                Matcher matcher;
                while (true) {
                    System.out.print("Nhập password: ");
                    password = scanner.nextLine();

                    String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[.,-_;]).{7,15}$";
                    Pattern pattern = Pattern.compile(passwordRegex);
                    matcher = pattern.matcher(password);

                    if (!matcher.matches()) {
                        System.out.println("Password không hợp lệ. Yêu cầu password chứa ít nhất 1 ký tự in hoa và 1 ký tự đặc biệt (. , - _ ;) và từ 7 đến 15 ký tự.");
                        continue;
                    }

                    break;
                }

                while (true) {
                    System.out.print("Nhập email: ");
                    email = scanner.nextLine();

                    String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
                    Pattern pattern = Pattern.compile(emailRegex);
                    matcher = pattern.matcher(email);

                    if (!matcher.matches()) {
                        System.out.println("Email không hợp lệ. mời bạn nhập theo định dạng abc@gmail.com");
                        continue;
                    }

                    break;
                }
            }

            users.add(new User(username, password, email));
            System.out.println("Đăng ký thành công!");
            break;
        }
    }

    public void login(Scanner scanner, ArrayList<User> users, Menu menu, UserService userService, int select) {
            System.out.println("Đăng Nhập");
            while (true) {
                System.out.print("Nhập username: ");
                String username = scanner.nextLine();
                System.out.print("Nhập password: ");
                String password = scanner.nextLine();

            boolean found = false;
            for (User user : users) {
                if (user.getUsername().equals(username)) {
                    found = true;
                    if (user.getPassword().equals(password)) {
                        System.out.println("Chào mừng " + username + " mời bạn thực hiện các công việc sau:");
                        menu.menuLoginDisplay(scanner, users, userService,menu,select);
                    } else {
                        System.out.println("Mật khẩu không chính xác vui lòng lựa chọn");
                        forgetPassword(scanner, users, select,menu,userService);
                    }
                    break;
                }
            }
            if(!found){
                System.out.println("Tài khoản không tồn tại mời bạn nhập lại");
                return;
            }
        }
    }


    public void forgetPassword(Scanner scanner, ArrayList<User> users, int select, Menu menu, UserService userService) {
        System.out.println("1. Đăng nhập lại");
        System.out.println("2. Quên mật khẩu");
        select = Integer.parseInt(scanner.nextLine());
        switch (select) {
            case 1:
                login(scanner,users,menu,userService,select);
                break;
            case 2:
                resetPassword(scanner, users,userService,menu,select);
        }
    }

    public void resetPassword(Scanner scanner, ArrayList<User> users, UserService userService, Menu menu, int select){
        while (true) {
        System.out.println("Nhập email để thực hiện thay đổi mật khẩu");
        String email = scanner.nextLine();

        Menu menuReturn = new Menu();
        for (User user : users){
            if (user.getEmail().equals(email)){
                while (true) {
                    System.out.println("Nhập mật khẩu mới (theo dạng có in hoa, chữ thường, số, kí tự đặc biệt (từ 7-15 kí tự))");
                    String newPassword = scanner.nextLine();

                if(!User.validatePassword(newPassword)){
                    System.out.println("Mật khẩu không hợp lệ mời bạn nhập lại đúng định dạng yêu cầu");
                    continue;
                }
                user.setPassword(newPassword);
                users.add(user);
                System.out.println("Mật khẩu đã được thay đổi");
                System.out.println("Chào mừng " + user.getUsername() + " mời bạn thực hiện các công việc sau");
                menuReturn.menuLoginDisplay(scanner,users,userService,menu,select);
                break;
            }
            }else {
                System.out.println("Email sai vui lòng kiểm tra lại");
                break;
                }
        }
    }
    }
    public void changeUsername(Scanner scanner, ArrayList<User> users) {
        while (true) {
        System.out.println("Mời bạn nhập username mới");
        String usernameChange = scanner.nextLine();
        for (User user : users) {
            if (user.getUsername().equals(usernameChange)){
                System.out.println("Username đã tồn tại");
                break;
            } else {
                user.setUsername(usernameChange);
                System.out.println("Username đã được thay đổi");
                return;
            }
            }
        }
        }
    public void changeEmail(Scanner scanner, ArrayList<User> users) {
        while (true){
            System.out.println("Mời bạn nhập email mới (theo định dạng abc@gmail.com)");
            String emailChange = scanner.nextLine();
            if(!User.validateEmail(emailChange)){
                System.out.println("Email không hợp lệ vui lòng nhập lại");
                continue;
            }
            for (User user : users) {
                if (user.getEmail().equals(emailChange)){
                    System.out.println("Email đã tồn tại vui lòng nhập email khác");
                    break;
                } else {
                    user.setEmail(emailChange);
                    System.out.println("Email đã được thay đổi");
                    return;
                }
            }
        }
    }
}


