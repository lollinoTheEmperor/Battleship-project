import java.io.BufferedReader;
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

    @Override
    public String createNewPlayerName() {
        String name;
        do {
            Scanner sc=new Scanner(System.in);
            System.out.print("Please enter a name: ");
            name=sc.nextLine();
            name=name.replaceAll(" ","");
        }while(!nameCheck(name));
        if (namePresenceCheck(name)){
            System.out.println("Sorry the name is already in use.");
            createNewPlayerName();
        }
        nameWrite(name);
        return name;
    }

    //checks the length of the name
    private boolean nameCheck(String name){
        int minLength=3, maxLength=20;
        if (name.length()<minLength){
            System.out.println("The name is too short, at least "+minLength+" characters!");
            return false;
        }
        if (name.length()>maxLength){
            System.out.println("The name is too long, at most "+maxLength+" characters!");
            return false;
        }
        return true;
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
    private void nameWrite(String name){
        String newPlayer=name+":0";
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
                        newPlayer
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            try {
                Files.writeString(
                        Path.of(path),
                        "\n"+newPlayer,
                        StandardOpenOption.APPEND
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @Override
    public String selectPlayer() {
        String name;
        boolean next=false;
        do {
            System.out.print("Please enter a name: ");
            Scanner sc = new Scanner(System.in);
            name = sc.nextLine();
            name = name.replaceAll(" ", "");
            if (namePresenceCheck(name)){
                next=true;
            }else {
                System.out.println("Sorry the name is not present.");
            }
        }while (!next);
        return name;
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

        try {
            Files.write(
                    Path.of(path),
                    newList
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String[]> ranking(){
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
        return words;
    }
}
