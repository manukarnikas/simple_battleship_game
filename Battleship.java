import java.util.ArrayList;
import java.util.Random;

class Game{
    private int[][] grid = new int[7][7];

    private ArrayList<String> getRandomLocation(){
        // Set Grid Size 7*7
        int ROW_LENGTH = 7;  // a-g 
        int COLUMN_LENGTH = 7;  // 1-7
        int MIN = 1;

        Random random = new Random();
        
        int row = random.nextInt(MIN,ROW_LENGTH + 1);
        int column = random.nextInt(MIN,COLUMN_LENGTH + 1);

        System.out.println(row);
        System.out.println(column);
    
        String[] alignmentType = {"horizontal","vertical"};
        String alignment = alignmentType[random.nextInt(alignmentType.length)];

        System.out.println(alignment);

        int sizeLimit = (alignment == "horizontal")  ? COLUMN_LENGTH-column : ROW_LENGTH-row ;
        int size = sizeLimit > 0 ? random.nextInt(MIN, sizeLimit+1) : 0;

         System.out.println(sizeLimit);
          System.out.println(size);
        char row_letter = (char)('a'+(row-1));

        System.out.println(row_letter);

        ArrayList<String> locationCells = new ArrayList<String>(); 
        locationCells.add(Character.toString(row_letter) + Integer.toString(column));
        if(size <=0){
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
        return locationCells;
    }

  
    public void start(){
        System.out.print(getRandomLocation());
    }
}

public class Battleship {
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
