import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerStatus_ImplTest {

    PlayerStatus_Impl playerStatus_impl = new PlayerStatus_Impl();
    String name1="name1";
    String name2="name2";

    @Test
    void createNewValidPlayer() {
        playerStatus_impl.createNewPlayerName(name1);
        assertTrue(containsName(name1));
    }

    //only works if Status.txt already has "name1:0"
    @Test
    void incrementWinnerIndex() {
        deleter();
        playerStatus_impl.createNewPlayerName(name1);
        playerStatus_impl.incrementWinnerIndex(name1);
        assertTrue(containsName(name1+":1"));
    }

    @Test
    void ranking() {
        List<String> expected = new ArrayList<>();
        expected.add(name2+":2");
        expected.add(name1+":1");
        deleter();
        playerStatus_impl.createNewPlayerName(name1);
        playerStatus_impl.incrementWinnerIndex(name1);
        playerStatus_impl.createNewPlayerName(name2);
        playerStatus_impl.incrementWinnerIndex(name2);
        playerStatus_impl.incrementWinnerIndex(name2);
        assertEquals(expected,playerStatus_impl.ranking());
    }

    private boolean containsName(String name) {
        String text;
        try {
            text = Files.readString(Path.of("src/Status.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return text.contains(name);
    }
    private void deleter(){
        FileWriter fileWriter = null;
        try {
            // Open the file in write mode without append (default is false for append)
            fileWriter = new FileWriter(String.valueOf("src/Status.txt"), false);
            if (fileWriter != null) {
                // Close the fileWriter which will truncate the file
                fileWriter.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}