
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
        int choice = -1;
        do{
            System.out.println("Venda de passagens\n[2] Informações de clientes\n[3] Reset\n[0] Sair\n");
            choice = in.nextInt();
            airline.clear();
            switch (choice){
                case 1:
                case 2:
                case 3:
            }
        }
        while(choice!=0);
    }
}
