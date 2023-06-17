import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import library.user;

public class C3Airlines {
    public static void main(String[] args) throws FileNotFoundException {
        user teste = new user();
        Scanner in = new Scanner(System.in);
        String pride = "";
        do {
            pride = in.next();
            teste.hold(pride);
        } while (!pride.startsWith("sair"));
        teste.write(teste.h);
    }
}
