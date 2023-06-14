import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class teste {
    public static void sell(String command, boolean[][] seats) {
        System.out.println(command);
        String choice = command.substring(5);
        System.out.println(choice);

        char letter = choice.charAt(0);
        System.out.println(letter);

        int number = Integer.parseInt(choice.substring(1));
        System.out.println(number);

        int line;
        int column;

        switch(letter) {
            case 'A' :
            case 'a' :
                column = 0;
                break;
            case 'B' :
            case 'b' :
                column = 1;
                break;
            case 'C' :
            case 'c' :
                column = 2;
                break;
            case 'D' :
            case 'd' :
                column = 3;
                break;
            default:
                column = -1;
        }

        line = number - 1;
        if (seats [line][column])
            System.out.println("Assento OCUPADO!");
        else {
            seats [line][column] = true;
            int next;
            if (column == 0 || column == 2) {
                next = column + 1;
            } else {
                next = column - 1;
            }
            if (!seats[line][next]) {
                System.out.println("Deseja reservar o assento ao lado? (S/n)");
            }
        }
    }
    public static void print(boolean[][] seats) {
        System.out.println("POA -> CGH");
        System.out.println("03/06/2023 6h TAM 3434\n");

        System.out.println("    A  B     C  D");
        // percorre cada linha
        for (int i = 0; i < seats.length; i++) {
            // mostra uma linha matriz
            System.out.printf("%2d ", i + 1);
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j])
                    System.out.print("[O]");
                else
                    System.out.print("[ ]");
                if (j == 1) {
                    System.out.print("   ");
                }
            }
            System.out.printf(" %2d%n", i + 1);
            if (i == 11 || i == 12) {
                System.out.println();
            }
        }
        System.out.println("    A  B     C  D");

    }
    public static void write(boolean[][] seats) throws Exception {
        PrintStream file = new PrintStream("seats.txt");
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                file.print(seats[i][j]+" ");
            }
            file.println();
        }
        file.close();
    }

    public static void read(boolean[][] seats) throws Exception {
        FileInputStream file = new FileInputStream("seats.txt");
        Scanner in = new Scanner(file);
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                seats[i][j] = in.nextBoolean();
            }
        }
        in.close();
        file.close();
    }
}
