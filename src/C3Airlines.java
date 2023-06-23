
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
    public static void main(String[] args) throws IOException, InterruptedException {
        User airline = new User();
        Scanner in = new Scanner(System.in);

        String command;
        int choice = -1;
        int destiny = 0;
        int aircraft = 0;

        do{
            System.out.println("[1] Venda de passagens\n[2] Disponibilidade da aeronave\n[3] Reset\n[4] Usuarios cadastrados\n[5] Todos os clientes\n[0] Sair");
            choice = in.nextInt();
            airline.clear();
            switch (choice) {
                case 1 -> {
                    System.out.println("Escolha seu destino:");
                    destiny = airline.destiny();
                    airline.print(destiny);
                    System.out.println("Quantos irão viajar?");
                    int j = in.nextInt();
                    for (int i = 0; i < j; i++) {
                        System.out.println("Escolha seu assento");
                        command = in.next();
                        airline.sell(destiny, command);
                        airline.getPrice(destiny);
                    }
                    System.out.println("R$" + airline.price);
                }
                case 2 -> {
                    System.out.println("[1] ERJ-145\n[2] Citation Ascend\n[3] Boeing 767-300ER");
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
                case 4 ->{
                    System.out.println("Qual o nome do Usuário?");
                    String name = in.next();
                    airline.readUser(name);
                }
                case 5 -> airline.getAll();
            }
        }
        while(choice!=0);
    }
}
