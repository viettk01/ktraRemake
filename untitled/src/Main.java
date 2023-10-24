import entities.User;
import menu.Menu;
import service.UserService;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int select = 0;
        Scanner scanner = new Scanner(System.in);
        ArrayList<User> users = new ArrayList<>();
        UserService userService = new UserService();
        Menu menu = new Menu();
        menu.menuDisplay(scanner,users,userService,menu,select);
    }
}