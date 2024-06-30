import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class PlayerStatus_Impl implements PlayerStatus {
    Path root=Path.of("src","Status.txt");
    String path=root+"";

    public String createNewPlayerName(String name) {
        String newPlayer=name+":0";
        writer(newPlayer);
        return name;
    }

    //check if the name is already present in the record of names
    private boolean namePresenceCheck(String name){
        try (BufferedReader reader = Files.newBufferedReader(Path.of(path))){
            String line = reader.readLine();
            while (line != null){
                if (line.contains(name)){
                    return true;
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    //writes the name in the record of names
    private void writer(String toInsert){
        boolean isEmpty=false;
        try (BufferedReader reader = Files.newBufferedReader(Path.of(path))){
            String line = reader.readLine();
            if (line==null){
                isEmpty=true;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(isEmpty) {
            try {
                Files.writeString(
                        Path.of(path),
                        toInsert
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            try {
                Files.writeString(
                        Path.of(path),
                        "\n"+toInsert,
                        StandardOpenOption.APPEND
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @Override
    public void selectPlayer(String name) {
        if (!namePresenceCheck(name)){
            createNewPlayerName(name);
        }
    }

    @Override
    public void incrementWinnerIndex(String name) {
        List<String> allPlayers;
        try (Stream<String> stream = Files.lines(Path.of(path))){
            allPlayers = stream.toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String newLine;
        List<String> newList=new ArrayList<>();
        for (String i:allPlayers){
            String[] elements=i.split(":");
            if (elements[0].equals(name)){
                int index=Integer.parseInt(elements[1]);
                index++;
                newLine=elements[0]+":"+index;
                newList.add(newLine);
            }else {
                newList.add(i);
            }
        }
        deleter();
        for (String i:newList){
            writer(i);
        }
    }

    //deletes all the player (they will be rewritten)
    private void deleter(){
        FileWriter fileWriter = null;
        try {
            // Open the file in write mode without append (default is false for append)
            fileWriter = new FileWriter(String.valueOf(root), false);
            if (fileWriter != null) {
                // Close the fileWriter which will truncate the file
                fileWriter.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> ranking(){
        List<String[]> words=new ArrayList<>();

        List<String> allPlayers;
        try (Stream<String> stream = Files.lines(Path.of(path))){
            allPlayers = stream.toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (String i:allPlayers){
            String[] elements=i.split(":");
            words.add(elements);
        }

        words.sort(new WinsComparator());
        List<String> output=new ArrayList<>();
        for (String[] i:words){
            output.add(i[0]+":"+i[1]);
        }

        return output;
    }
}
