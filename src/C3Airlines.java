
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import library.User;

//==============================================================
//============Andrei Cunha Böeck e Henrique Denardin============
//=================andrei.boeck@edu.pucrs.br====================
//===============henrique.denardin@edu.pucrs.br=================
//==Trabalho destinado a cadeira de fundamentos da programação==
//==============================================================

public class C3Airlines {
    public static boolean[][] read(int aircraft) throws FileNotFoundException {
        User airline = new User();
        boolean[][] aeronave = airline.read(aircraft);

        return aeronave;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        User airline = new User();
        Scanner in = new Scanner(System.in);

        String command;
        int choice = -1;
        int destiny = 0;
        int aircraft = 0;

        do{
            System.out.println("[1] Venda de passagens\n[2] Disponibilidade da aeronave\n[3] Reset\n[0] Sair");
            choice = in.nextInt();
            airline.clear();
            switch (choice) {
                case 1 -> {
                    System.out.println("Escolha seu destino:");
                    destiny = airline.destiny();
                    System.out.println("Escolha seu assento");
                    command = in.next();
                    switch (destiny) {
                        case 1, 2 -> aircraft = 1;
                        case 3 -> aircraft = 2;
                        case 4, 5 -> aircraft = 3;
                    }
                    airline.sell(aircraft, command);
                }
                case 2 -> {
                    choice = in.nextInt();
                    boolean[][] teste= airline.read(choice);
                    for (int i = 0; i < teste.length; i++) {
                        for (int j = 0; j < teste[i].length; j++) {
                            System.out.print(teste[i][j] + " ");
                        }
                        System.out.println();
                    }
                }
                case 3 -> {
                    System.out.println("[1] ERJ-145\n[2] Citation Ascend\n[3] Boeing 767-300ER");
                    aircraft = in.nextInt();
                    airline.reset(aircraft);
                }
            }
        }
        while(choice!=0);
    }
}
