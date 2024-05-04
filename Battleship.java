import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

class Game{
    private int[][] grid = new int[7][7];
    private int no_of_ships = 3;
    ArrayList<String> allShipLocationCells = new ArrayList<String>();
    Map<Integer, ArrayList<String>> shipLocationMap = new HashMap<>();
   
    private ArrayList<String> generateShipLocation(){
        // Set Grid Size 7*7
        int ROW_LENGTH = 7;  // a-g 
        int COLUMN_LENGTH = 7;  // 1-7
        int MIN = 1;

        Random random = new Random();
        
        int row = random.nextInt(MIN,ROW_LENGTH + 1);
        int column = random.nextInt(MIN,COLUMN_LENGTH + 1);

        System.out.println((char)('a'+(row-1)));
        System.out.println(column);
    
        String[] alignmentType = {"horizontal","vertical"};
        String alignment = alignmentType[random.nextInt(alignmentType.length)];

        System.out.println(alignment);

        int sizeLimit = (alignment == "horizontal")  ? COLUMN_LENGTH-column : ROW_LENGTH-row ;
        int size = sizeLimit > 0 ? random.nextInt(MIN, sizeLimit+1) : 0;

        System.out.println(sizeLimit);
        System.out.println(size);

        char row_letter = (char)('a'+(row-1));

        ArrayList<String> locationCells = new ArrayList<String>(); 
        locationCells.add(Character.toString(row_letter) + Integer.toString(column));
        if(size <=0){
            System.out.print(locationCells);
            return locationCells;
        }
        if(alignment.equals("horizontal")){
            for(int i=1;i<=size;i++){
                String cell =  Character.toString(row_letter) + Integer.toString(column+i);
                locationCells.add(cell);
            }
        }else{
            for(int i=1;i<=size;i++){
                String cell =  Character.toString(row_letter+i) + Integer.toString(column);
                locationCells.add(cell);
            }
        }
        System.out.println(locationCells);
        return locationCells;
    }

    private void generateShips(){
        for(int i=1;i<=no_of_ships;i++){
            System.out.println("------------------> ship"+i);
           ArrayList<String> locationCells =  generateShipLocation();
           boolean flag = false;
           for(String locationCell: locationCells){
                if(allShipLocationCells.contains(locationCell)){
                    i-=1;
                    System.out.println("Location cell overlap. Running again");
                    flag=true;
                    break;
                }
           }
           if(!flag){    
            allShipLocationCells.addAll(locationCells);
            shipLocationMap.put(i,locationCells);
           }
        }
    }

    private void printShips(){
        for (Map.Entry<Integer, ArrayList<String>> entry : shipLocationMap.entrySet()) {
            // Get the key and value from the entry
            Integer key = entry.getKey();
            ArrayList<String> value = entry.getValue();
            // Print the key
            System.out.println("Key: " + key);
            // Print the value (ArrayList<String>)
            System.out.println("Value: " + value);
        }
    }

    private void play(){
        int totalGuess = 0;
        int hitCount = 0;
        Scanner scanner = new Scanner(System.in);
        while(!shipLocationMap.isEmpty()){
            System.out.print("Enter a location:");
            String userInput = scanner.nextLine();
            totalGuess+=1;
            if(allShipLocationCells.contains(userInput)){
                hitCount++;
                System.out.println("Hit!");
                allShipLocationCells.remove(userInput);
                for (Map.Entry<Integer, ArrayList<String>> entry : shipLocationMap.entrySet()) {
                    Integer key = entry.getKey();
                    ArrayList<String> value = entry.getValue();
                    if(value.contains(userInput)){
                        value.remove(userInput);
                        if(value.isEmpty()){
                            System.out.println("Battleship "+key+" is Blitzkreiged!");
                            shipLocationMap.remove(key);
                        }
                        break;
                    }
                }
            }else{
                System.out.println("Miss");
            }
        }
        System.out.println("All battleships are destroyed!");
        System.out.println("Total Guess: "+ totalGuess);
        System.out.println("Hit Count: "+ hitCount);
        if(totalGuess <= 20){
            System.out.println("Wow you are an elite commander!");
        } else if(totalGuess <= 30){
            System.out.println("Aye Aye Captain!");
        } else{
            System.out.println("That sucks. Better Luck next time!");
        }
    }
  
    public void start(){
        generateShips();
        printShips();
        play();
    }
}

public class Battleship {
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
