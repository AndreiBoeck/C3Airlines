
import library.User;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class C3Airlines {
    public static void main(String[] args) throws IOException, InterruptedException {
        User airline = new User();

        Scanner in = new Scanner(System.in);
        int choice = -1;
        do{
            System.out.printf("[1] Agendar Aeronave\n[2] Venda de passagens\n[3] Informações de clientes\n[4] Reset\n[0] Sair\n");
            choice = in.nextInt();
            airline.clear();
            switch (choice){
                case 1:
                    System.out.printf("Agendar Aeronave:\n[1] Cessna Skylane\n[2] Citation Ascend\n");
                    choice = in.nextInt();
                    airline.rentAircraft(choice);
                    break;
                case 2:
                    airline.print(airline.read(3));
                    String seats = in.next();
                    airline.sell(airline.read(3), seats);
                    break;
                case 3:
                    airline.print(airline.read(3));
                    break;
                case 4:
                    airline.readUser(in.next());
                    break;
                case 5:
                    System.out.println("Qual Aeronave deseja restaurar?\n[1] Cessna Skylane\n[2] Citation Ascend\n[3] Boeing 767-300ER");
                    choice = in.nextInt();
                    airline.reset(choice);
                    break;
            }
        }
        while(choice!=0);
    }
}
