import library.User;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class UserTest extends User {

    public UserTest() throws FileNotFoundException {
    }
    @Test
    public void readTest() throws FileNotFoundException {
        User tests = new User();
        boolean[][] aircraft = new boolean[7][45];
        for (int i = 0; i < 7; i++) {
            Arrays.fill(aircraft[i], false);
        }
        assertEquals(aircraft[0][0], tests.read(3)[0][0]);

    }
    @Test
    public void getPathTest() throws FileNotFoundException {
        User tests = new User();
        assertEquals("C:\\Users\\Teste\\OneDrive\\Documents\\GOL/src/library/source/Boeing 767-300ER", tests.getPath(3));
    }
}