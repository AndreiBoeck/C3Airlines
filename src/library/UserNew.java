package library;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Scanner;

public class UserNew {
    private static final String USER_DIR = System.getProperty("user.dir");
    private static final String os = System.getProperty("os.name").toLowerCase();

    private final PrintStream file = new PrintStream(new FileOutputStream(USER_DIR + "/library/source/clientsdata", true));
    private String path;
    private int horizontal(int choice) throws FileNotFoundException {
        FileReader file = new FileReader(USER_DIR + getPath(choice));
        Scanner fl = new Scanner(file);
        String line = fl.nextLine();
        String[] array = line.split(", ");
        int horizontal = array.length;
        return horizontal;
    }
    private int vertical(int choice) throws FileNotFoundException {
        FileReader file = new FileReader(USER_DIR + getPath(choice));
        Scanner fl = new Scanner(file);
        int vertical = 0;
        while (fl.hasNextLine()){
            vertical++;
        }
        return vertical;
    }

    public UserNew() throws FileNotFoundException {
    }
    private String getPath(int choice){
        switch (choice) {
            case 1 -> {
                path = USER_DIR + "/library/source/Cessna Skylane";

            }
            case 2 -> {
                path = USER_DIR + "/library/source/Citation Ascend";

            }
            case 3 -> {
                path = USER_DIR + "/library/source/Boeing 767-300ER";
            }
        }
        return path;
    }
    public boolean[][] read(int choice) throws FileNotFoundException {
        FileReader fi = new FileReader(getPath(choice));
        Scanner in = new Scanner(fi);
        boolean[][] aircraft = new boolean[horizontal][vertical];
        for (int j = 0; j < horizontal; j++) {
            for (int k = 0; k < vertical; k++) {
                aircraft[j][k] = in.nextBoolean();
            }
        }
        return aircraft;
    }
    public void print(boolean[][] aircraft) {
        for (int j = 0; j < vertical; j++) {
            for (int k = 0; k < horizontal; k++) {
                System.out.print(aircraft[k][j] + " ");
            }
            System.out.println();
            if(j % spacing == 0 && j!=0){
                System.out.println();
            }
        }
    }
    public void sell(boolean[][] seats, String command) throws FileNotFoundException {
        PrintStream boeing = new PrintStream(USER_DIR + "/library/source/Boeing 767-300ER");
        System.out.println(command);
        System.out.println(command);

        char letter = command.charAt(0);
        System.out.println(letter);

        int number = Integer.parseInt(command.substring(1));
        System.out.println(number);

        int line;
        int column = switch (letter) {
            case 'A', 'a' -> 0;
            case 'B', 'b' -> 1;
            case 'C', 'c' -> 2;
            case 'D', 'd' -> 3;
            case 'E', 'e' -> 4;
            case 'F', 'f' -> 5;
            case 'G', 'g' -> 6;
            default -> -1;
        };

        line = number - 1;
        if (seats [column][line])
            System.out.println("Assento OCUPADO!");

        for (int j = 0; j < 45; j++) {
            for (int k = 0; k < 7; k++) {
                boeing.print(seats[k][j] + " ");
            }
            boeing.println();
        }

    }
    public void reset(int choice) throws FileNotFoundException {
        PrintStream boeing = new PrintStream(getPath(choice));
        boolean[][] aircraft = new boolean[vertical][horizontal];
        for (int j = 0; j < horizontal; j++) {
            for (int k = 0; k < vertical; k++) {
                aircraft[k][j] = false;
            }
        }
        for (int j = 0; j < horizontal; j++) {
            for (int k = 0; k < vertical; k++) {
                boeing.print(aircraft[k][j] + " ");
            }
            boeing.println();
        }
    }
    public void createUser() throws FileNotFoundException {
        String[] client = new String[10];
        Scanner in = new Scanner(System.in);
        String input = "";
        for (int j = 0; j < 10; j++) {
            switch (j){
                case 0 -> input = "digite o nome";
                case 1 -> input = "digite o cpf";
                case 2 -> input = "digite a data de nascimento";
                case 3 -> input = "digite o numero de telefone";
                case 4 -> input = "digite o email ";
                case 5 -> input = "digite o tipo de documento";
                case 6 -> input = "digite o numero do documnento";
                case 7 -> input = "digite o sexo";
                case 8 -> input = "digite a nacionalidade";
                case 9 -> input = "deseja bagagem extra? Quantas?";
            }
            System.out.println(input);
            client[j] = in.nextLine();
        }
        for (int j = 0; j < 10; j++) {
            file.print(client[j] + ", ");
        }
        file.println();
    }
    public void readUser(String name) throws FileNotFoundException {
        FileReader file = new FileReader(USER_DIR + "/library/source/clientsdata");
        Scanner in = new Scanner(System.in);
        Scanner fl = new Scanner(file);
        while (fl.hasNextLine()){
            String line = fl.nextLine();
            String[] pl = line.split(", ");
            if(line.startsWith(name)){
                for (int i = 0; i < 10; i++) {
                    System.out.println(pl[i]);
                }
                break;
            }
            else{
                System.out.println("Cliente não encontrado");
                break;
            }
        }
    }
}