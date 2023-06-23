
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

        do{
            System.out.println("[1] Venda de passagens\n[2] Informações de clientes\n[3] Reset\n[0] Sair\n");
            choice = in.nextInt();
            airline.clear();
            switch (choice){
                case 1:
                    System.out.println("Escolha seu destino:");
                    destiny= airline.destiny();
                    System.out.println("Escolha seu assento");
                    command = in.next();
                    switch (destiny){
                        case 1, 2 -> choice = 1;
                        case 3 -> choice = 2;
                        case 4, 5 -> choice = 3;
                    }
                    airline.sell(choice, command);
                    break;
                case 2:
                case 3:
                    System.out.println("[1] ERJ-145\n[2] Citation Ascend\n[3] Boeing 767-300ER");
                    airline.reset(in.nextInt());
            }
        }
        while(choice!=0);
    }
}
