import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;

class Game{
    private int[][] grid = new int[7][7];
    private int no_of_ships = 3;
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
        ArrayList<String> allShipLocationCells = new ArrayList<String>();
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

  
    public void start(){
        generateShips();
        printShips();
    }
}

public class Battleship {
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
