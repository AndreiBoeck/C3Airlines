package library;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class user {
    private int i = 0;
    public ArrayList<String> h = new ArrayList<>();
    private String path = "";
    private int vertical = 0;
    private int horizontal = 0;
    private int spacing = 0;
//implementar
    //quem
    //destino
    //aeronave
    //preço
    //assento
    //tipo do assento
    public void write(ArrayList<String> names) throws FileNotFoundException {
        PrintStream file = new PrintStream(new FileOutputStream("src/clientsdata", true));

        for (int j = 0; j < i; j++) {
            file.println(names.get(j));
        }
    }
    public ArrayList<String> hold(String entry){
        if(!entry.startsWith("sair")){
            h.add(entry);
            i++;
        }
        return h;
    }
    public void read(int escolha) throws FileNotFoundException {
        switch (escolha){
            case 1:
                path = "src/Cessna Skylane";
                horizontal = 2;
                vertical = 1;
                spacing = 0;
                break;
            case 2:
                path = "src/Citation Ascend";
                vertical = 6;
                horizontal = 2;
                spacing = 2;
                break;
            case 3:
                path = "Boeing 767-300ER";
                vertical = 45;
                horizontal = 7;
                spacing =
                break;
        }
        FileReader file = new FileReader(path);
        Scanner in = new Scanner(file);
        boolean[][] aircraft = new boolean[horizontal][vertical];
        for (int j = 0; j < horizontal; j++) {
            for (int k = 0; k < vertical; k++) {
                aircraft[j][k] = in.nextBoolean();
            }
        }
        for (int j = 0; j < horizontal; j++) {
            for (int k = 0; k < vertical; k++) {
                System.out.printf("%b ", aircraft[j][k]);
                if(vertical%spacing)
            }
        }
    }

}
