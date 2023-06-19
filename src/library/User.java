package library;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class User {
    private static final String USER_DIR = System.getProperty("user.dir");
    private static final String os = System.getProperty("os.name").toLowerCase();
    private final PrintStream file = new PrintStream(new FileOutputStream(USER_DIR + "/library/source/clientsdata", true));
    private int i = 0;
    public ArrayList<String> h = new ArrayList<>();
    private String path = "";
    private int vertical = 0;
    private int horizontal = 0;
    private int spacing = 0;

    public User() throws FileNotFoundException {
    }

    //implementar
    //quem
    //destino
    //aeronave
    //preço
    //assento
    //tipo do assento
    public void write(ArrayList<String> names) {
        for (int j = 0; j < i; j++) {
            file.println(names.get(j));
        }
    }
    public void clear() throws IOException, InterruptedException {

        if(os.contains("win")){
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }
        else{
            new ProcessBuilder("clear").inheritIO().start().waitFor();
        }
    }
    public ArrayList<String> hold(String entry){
        if(!entry.startsWith("sair")){
            h.add(entry);
            i++;
        }
        return h;
    }
    public boolean[][] read(int escolha) throws FileNotFoundException {
        switch (escolha) {
            case 1 -> {
                path = USER_DIR + "/library/source/Cessna Skylane";
                horizontal = 2;
                vertical = 1;
                spacing = 1;
            }
            case 2 -> {
                path = USER_DIR + "/library/source/Citation Ascend";
                vertical = 6;
                horizontal = 2;
                spacing = 2;
            }
            case 3 -> {
                path = USER_DIR + "/library/source/Boeing 767-300ER";
                vertical = 45;
                horizontal = 7;
                spacing = 5;
            }
        }
        FileReader fi = new FileReader(path);
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
    @SuppressWarnings("resource")
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
        else {
            seats[column][line] = true;
            int next;
            if (column == 0 || column == 6) {
                next = column + 1;
            } else {
                next = column - 1;
            }

        }
        for (int j = 0; j < 45; j++) {
            for (int k = 0; k < 7; k++) {
                boeing.print(seats[k][j] + " ");
            }
            boeing.println();
        }
    }
    @SuppressWarnings("resource")
    public void reset() throws FileNotFoundException {
        PrintStream boeing = new PrintStream(USER_DIR + "/library/source/Boeing 767-300ER");
        boolean[][] aircraft = new boolean[7][45];
        for (int j = 0; j < 45; j++) {
            for (int k = 0; k < 7; k++) {
                aircraft[k][j] = false;
            }
        }
        for (int j = 0; j < 45; j++) {
            for (int k = 0; k < 7; k++) {
                boeing.print(aircraft[k][j] + " ");
            }
            boeing.println();
        }
    }
}

