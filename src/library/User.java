package library;

import java.io.*;
import java.util.Scanner;

//==============================================================
//============Andrei Cunha Böeck e Henrique Denardin============
//=================andrei.boeck@edu.pucrs.br====================
//===============henrique.denardin@edu.pucrs.br=================
//==Trabalho destinado a cadeira de fundamentos da programação==
//==============================================================

public class User {
    private final String USER_DIR = System.getProperty("user.dir");//Encontra onde esta o arquivo
    private final String os = System.getProperty("os.name").toLowerCase();

    private final PrintStream userdata = new PrintStream(new FileOutputStream(USER_DIR + "/library/source/clientsdata", true));//Leitor comum do arquivo ClientsData
    private String path;
    private String aircraftName;
    public double price = 0;
    public User() throws FileNotFoundException {
    }
    public String getPath(int choice){//metodo utilizado para selecionar a aeronave
        switch (choice) {
            case 1 -> {
                path = USER_DIR + "/library/source/ERJ-145";
                aircraftName = "ERJ-145";
            }
            case 2 -> {
                path = USER_DIR + "/library/source/Citation Ascend";
                aircraftName = "Citation Ascend";
            }
            case 3 -> {
                path = USER_DIR + "/library/source/Boeing 767-300ER";
                aircraftName = "Boeing 767-300ER";
            }
        }
        return path;
    }
    private int horizontal(int choice) throws FileNotFoundException {//metodo destinado a descobrir a quantidade de colunas
        FileReader file = new FileReader(getPath(choice));
        Scanner fl = new Scanner(file);

        String line = fl.nextLine();
        String[] array = line.split(" ");

        return array.length;
    }
    private int vertical(int choice) throws FileNotFoundException {//metodo destinado a descobrir quantidade de linhas
        FileReader file = new FileReader(getPath(choice));
        Scanner fl = new Scanner(file);

        int vertical = 0;
        while (fl.hasNextLine()){
            vertical++;
            fl.nextLine();
        }

        return vertical;
    }

    public boolean[][] read(int choice) throws FileNotFoundException {//metodo feito para criar a matriz correspondente a cada aeronave
        FileReader file = new FileReader(getPath(choice));

        Scanner in = new Scanner(file);

        boolean[][] aircraft = new boolean[vertical(choice)][horizontal(choice)];
        for (int j = 0; j < aircraft.length; j++) {
            for (int k = 0; k < aircraft[j].length; k++) {
                aircraft[j][k] = in.nextBoolean();
            }
        }

        return aircraft;
    }
    public void print(int choice) throws FileNotFoundException {//metodo destinado a escrever a matriz anterior
        boolean[][] aircraft = read(choice);
        for (int j = 0; j < aircraft.length; j++) {
            for (int k = 0; k < aircraft[j].length; k++) {
                System.out.print(aircraft[j][k] + " ");
            }
            System.out.println();
        }
    }
    public void sell(int choice , String command) throws FileNotFoundException {//metodo feito para vender
        boolean[][] seats = read(choice);
        boolean available = available(choice);
        PrintStream boeing = new PrintStream(getPath(choice));
        Scanner in = new Scanner(System.in);
        String userSeat = command;
        String seatType = "Não exclusivo";

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
        if (seats[line][column]){//aqui o programa descobre se o assento selecionado ja foi ocupado
            System.out.println("Assento OCUPADO!");
            for (int j = 0; j < seats.length; j++) {
                for (int k = 0; k < seats[j].length; k++) {
                    boeing.print(seats[j][k] + " ");
                }
                boeing.println();
            }
            return;
        }
        else {
            if (line <= seats.length/2) {//Se o assento estiver em uma exclusiva ele o usuario é convidado a escolher se deseja alugar metade da aeronave para si
                System.out.println("Deseja exclusividade?");
                command = in.next();

                if (command.startsWith("s") || command.startsWith("S")) {
                    if (available) {
                        for (int i = 0; i < seats.length / 2; i++) {
                            for (int j = 0; j < seats[i].length; j++) {
                                seats[i][j] = true;
                                getPrice(choice);
                            }
                        }
                        seatType = "VIP";
                    } else {
                        System.out.println("Infelizmente a exclusividade não está disponivel nesta aeronave, um de nossos clientes ja comprou um ou mais assentos na area desejada");
                        for (int j = 0; j < seats.length; j++) {
                            for (int k = 0; k < seats[j].length; k++) {
                                boeing.print(seats[j][k] + " ");
                            }
                            boeing.println();
                        }
                    }
                }
            }
            else {
                System.out.println("A exclusividade está disponivel apenas na primeira metade do avião\nQuer trocar o assento?");
                command = in.next();
                if (command.startsWith("s") || command.startsWith("S")){
                    System.out.println("Escolha novamente seu assento");
                    command = in.next();
                    for (int j = 0; j < seats.length; j++) {
                        for (int k = 0; k < seats[j].length; k++) {
                            boeing.print(seats[j][k] + " ");
                        }
                        boeing.println();
                    }
                    sell(choice, command);
                    return;
                }
            }
                seats[line][column] = true;
        }
        System.out.println("Confirma a escolha?");
        System.out.printf("%s\n%s\n%s\n",aircraftName, userSeat, seatType);
        command = in.next();
        if (command.startsWith("N") || command.startsWith("n")){
            System.out.println("O processo deve ser iniciado do zero");
        }
        else {
            createUser(userSeat);
            for (int j = 0; j < seats.length; j++) {
                for (int k = 0; k < seats[j].length; k++) {
                    boeing.print(seats[j][k] + " ");
                }
                boeing.println();
            }
            System.out.println("Assentos salvo, tenha uma boa viagem");
        }

    }
    private boolean available(int choice) throws FileNotFoundException {//metodo destinado a saber se a exclusividade esta disponivel
        boolean available = true;
        for (int i = 0; i < horizontal(choice); i++) {
            for (int j = 0; j < vertical(choice)/2; j++) {
                if (read(choice)[j][i]){
                    available = false;
                }
            }
        }
        return available;
    }
    public void reset(int choice) throws FileNotFoundException {// Feito para recriar a aeronave
        boolean[][] aircraft = read(choice);
        PrintStream boeing = new PrintStream(getPath(choice));
        for (int j = 0; j < aircraft.length; j++) {
            for (int k = 0; k < aircraft[j].length; k++) {
                aircraft[j][k] = false;
            }
        }
        for (int j = 0; j < aircraft.length; j++) {
            for (int k = 0; k < aircraft[j].length; k++) {
                boeing.print(aircraft[j][k] + " ");
            }
            boeing.println();
        }
        System.out.println("A aeronave foi redefinida");
    }
    public int destiny(){//Escolha de destino
        Scanner in = new Scanner(System.in);
        System.out.println("[1] POA -> CGH(Sao Paulo - Congonhas) -- 19:45");//ERJ
        System.out.println("[2] POA -> CWB(Curitiba - Curitiba) -- 13:20");//Ascend
        System.out.println("[3] POA -> LAX(Los Angeles - Los Angeles) -- 8:35");//Boeing
        return in.nextInt();
    }
    public void createUser(String seats) throws FileNotFoundException {//metodo para criar e gravar os usuarios
        String[] client = new String[11];
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
                case 6 -> input = "digite o numero do documento";
                case 7 -> input = "digite o sexo";
                case 8 -> input = "digite a nacionalidade";
                case 9 -> input = "deseja bagagem extra? Quantas?";
            }
            System.out.println(input);
            client[j] = in.nextLine();
        }
        client[10] = seats;
        for (int j = 0; j < 11; j++) {
            userdata.print(client[j] + ", ");
        }
        userdata.println();
    }
    public void readUser(String name) throws FileNotFoundException {// localiza apenas um usuário
        FileReader file = new FileReader(USER_DIR + "/library/source/clientsdata");
        Scanner fl = new Scanner(file);
        while (fl.hasNextLine()){
            String line = fl.nextLine();
            String[] pl = line.split(", ");
            if(pl[0].startsWith(name)){
                for (int i = 0; i < 11; i++) {
                    switch (i){
                        case 0 -> System.out.print("Nome: ");
                        case 1 -> System.out.print("CPF: ");
                        case 2 -> System.out.print("Data de Nascimento: ");
                        case 3 -> System.out.print("Numero de telefone: ");
                        case 4 -> System.out.print("Email: ");
                        case 5 -> System.out.print("Tipo de documento: ");
                        case 6 -> System.out.print("Numero de documento: ");
                        case 7 -> System.out.print("Sexo: ");
                        case 8 -> System.out.print("Nacionalidade: ");
                        case 9 -> System.out.print("Bagagem Extra: ");
                        case 10 -> System.out.print("Assento: ");
                    }
                    System.out.println(pl[i]);
                }
                return;
            }
        }
        System.out.println("Cliente não encontrado");
    }
    public void getAll() throws FileNotFoundException {//le todos os usuarios
        FileReader file = new FileReader(USER_DIR + "/library/source/clientsdata");
        Scanner fl = new Scanner(file);
        while (fl.hasNextLine()) {
            String line = fl.nextLine();
            String[] pl = line.split(", ");
            for (int i = 0; i < 11; i++) {
                switch (i) {
                    case 0 -> System.out.print("Nome: ");
                    case 1 -> System.out.print("CPF: ");
                    case 2 -> System.out.print("Data de Nascimento: ");
                    case 3 -> System.out.print("Numero de telefone: ");
                    case 4 -> System.out.print("Email: ");
                    case 5 -> System.out.print("Tipo de documento: ");
                    case 6 -> System.out.print("Numero de documento: ");
                    case 7 -> System.out.print("Sexo: ");
                    case 8 -> System.out.print("Nacionalidade: ");
                    case 9 -> System.out.print("Bagagem Extra: ");
                    case 10 -> System.out.print("Assento: ");
                }
                System.out.println(pl[i]);
            }
            System.out.println("--------------------------------------------------------------");
        }
    }
    public void getPrice(int choice){//calcula o preço total
        switch (choice){
            case 1 -> price += 1500.00;
            case 2 -> price += 1000.00;
            case 3 -> price += 3200.00;
        }

    }
    public void clear() throws IOException, InterruptedException {//limpa a tela de escolha para evitar a poluição visual

        if(os.contains("win")){
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }
        else{
            new ProcessBuilder("clear").inheritIO().start().waitFor();
        }
    }
}
