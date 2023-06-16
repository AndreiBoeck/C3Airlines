import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class C3Airlines {
    public static void main(String[] args) throws FileNotFoundException {
        PrintStream file = new PrintStream("Boeing 767-300ER");
        Boolean[][] boeing = new Boolean[5][45];
        Boolean preencher = false;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 45; j++) {
                boeing[i][j] = preencher;
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 45; j++) {
                file.print(boeing[i][j]);
            }
        }
    }
}
