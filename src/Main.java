import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

public class Main {

    static File directory = new File("D:\\IntelliJProjects\\JavaPrograms\\Lab_13_FileListMaker\\src\\Lists");

    private static final String REGGIE = "[AaDdIiQqMmOoSsCcVv]";
    private static Path currentFile = null;
    private static ArrayList<String> currentList = new ArrayList<>();
    private static boolean needsSaved = false;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){

        boolean running = true;

        do{
            String choice = SafeInput.getRegExString(scanner,"Input a command: (A,D,I,V,M,O,S,C,Q)",REGGIE).toLowerCase();

            switch(choice){
                case "a":
                    addItem();
                    break;
                case "d":
                    deleteItem();
                    break;
                case "i":
                    insertItem();
                    break;
                case "v":
                    viewList();
                    break;
                case "c":
                    clearList();
                    break;
                case "o":
                    openFile();
                    break;
                case "s":
                    saveFile();
                    break;
                case "m":
                    move();
                    break;
                case "q":
                    running = quit();
                    break;
            }

        }while(running);
    }

    static void addItem(){
        needsSaved=true;

        String itemToAdd = SafeInput.getNonZeroLenString(scanner, "What item would you like to add to the ArrayList?");
        currentList.add(itemToAdd);
        System.out.println(itemToAdd + " added successfully!");
    }

    static void deleteItem(){
        needsSaved=true;

        int index = SafeInput.getRangedInt(scanner, "What index of the ArrayList would you like to remove?", 0, currentList.size());
        currentList.remove(index);
        System.out.println("Your item at " + String.valueOf(index) + " was removed successfully!");
        scanner.nextLine();
    }

    static void insertItem(){
        needsSaved=true;

        String itemToAdd = SafeInput.getNonZeroLenString(scanner, "What item would you like to add to the ArrayList?");
        int index = SafeInput.getRangedInt(scanner, "What index of the ArrayList would you like to put " + itemToAdd + " at?", 0, currentList.size());
        currentList.add(index,itemToAdd);
        System.out.println(itemToAdd + " added successfully!");
        scanner.nextLine();
    }

    static void viewList(){
        System.out.println(currentList);
    }

    static void clearList(){
        currentList.clear();
    }

    static void saveFile(){
        needsSaved=false;

        if(currentFile!=null){
            try{
                Files.write(currentFile,currentList,TRUNCATE_EXISTING);
                System.out.println("Saved to current file!");
            } catch (IOException e){
                e.printStackTrace();
            }
        }else{
            String choice = SafeInput.getNonZeroLenString(scanner, "What is the name of your file?");

            try{
                File file = new File(directory,String.format("%s.txt", choice));

                currentFile = file.toPath();

                Files.write(file.toPath(), currentList,CREATE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static void openFile(){

        if(needsSaved){
            if(SafeInput.getYNConfirm(scanner, "You have unsaved data! Are you sure you want to open another file? (Y,N)")){
                saveFile();
                return;
            }
        }

        needsSaved = false;

        String fToOpen = SafeInput.getNonZeroLenString(scanner,"What file would you like to open?");

        File file = new File(directory, String.format("%s.txt",fToOpen));

        Path path = file.toPath();
        currentFile = path;

        if(Files.exists(path)){

            try{
                List<String> lines = Files.readAllLines(path);
                currentList = new ArrayList<>(lines);
            } catch(IOException e){
                System.out.println("ERROR WHEN READING FILE!");
            }
        }else{
            System.out.println("The file: " + fToOpen + " does not exist!");
        }
    }

    static void move(){
        needsSaved = true;

        int indexFrom = SafeInput.getRangedInt(scanner,"What index would you like to move?",1,currentList.size());
        int indexTo = SafeInput.getRangedInt(scanner,"What index would you like to move to?",1,currentList.size());

        String element = currentList.get(indexFrom);
        currentList.remove(element);

        currentList.set(indexTo,element);
        scanner.nextLine();
    }

    static boolean quit(){

        if(needsSaved){
            if(SafeInput.getYNConfirm(scanner, "You have unsaved data! Are you sure you want to quit? (Y,N)")){
                saveFile();
                return true;
            }else{
                return false;
            }
        }

        return !SafeInput.getYNConfirm(scanner, "Are you sure you want to quit? (Y,N)");
    }
}