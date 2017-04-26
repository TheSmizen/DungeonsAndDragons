

/*
 * Main template class with the menu with all the options
 * 
 */

package game;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
/**
 *
 * @author Arantza
 */
public class GameController {
    
    static private boolean testingMode;

    static void menu(){
        
        System.out.println("Enter an option");
        System.out.println("1: Display level");
	System.out.println("2: Move animated entities");
        System.out.println("3: Display the properties of an entity");
        System.out.println("4: Reset the room");
        System.out.println("0: Exit");
    }
    
    static void version1(){
        
        int boardWidth = 10;
        
        String[] namesString = new String[3];
        namesString[0] = "Harold";
        namesString[1] = "David";
        namesString[2] = "Clare";
        
        //Params:
        //Board width, Stones, Holes, Monsters, FDragons, NDragons, Humans(arr)
        Room myRoom = new Room(boardWidth, 2, 2, 3, 1, 1, namesString);

        System.out.println(myRoom.toString());
        System.out.println();
        
        
        try{
            myRoom.loadEntities("C:\\temp\\OutFile.csv");
            System.out.println("Loading File...\n");
        }
        catch(java.io.FileNotFoundException ex){
                System.out.println(":(\n" + ex.toString());
        }
        
        System.out.println(myRoom.toString());
        System.out.println();

        
        
        //Board initialised, now to run main game code:
        
        Scanner kb = new Scanner(System.in);
        int option, x, y = 0;
        String inputLine;
        
        do {
            
            if (testingMode) {
                for(Entity ent:myRoom.getEntities()){
                    System.out.println(ent.displayAttributes());
                    System.out.println();
                }
            }
            
            menu();
            option = kb.nextInt();
            kb.nextLine();
            switch (option) {
                case 1:
                    //Display Level
                    System.out.println(myRoom.toString()); 
                    break;

                case 2:
                    //Move animated entities
                    myRoom.move();
                    break;

                case 3:
                    //Display the properties of an entity
                    do{
                        System.out.println("Enter the position of the entity " +
                                "that you want to display in the format 'x,y'");
                        inputLine = kb.nextLine();
                        inputLine = inputLine.replace("'", "");
                        x = Integer.parseInt(inputLine.split(",")[0]);
                        y = Integer.parseInt(inputLine.split(",")[1]);
                        if (myRoom.isFree(x, y)){
                            System.out.println("No entity found on this cell.");
                            System.out.println("It might be hiding elsewhere.");
                        }
                        else{
                            System.out.println(myRoom.displayEntity(x,y));
                        }
                        
                    } while ((y < 0) || (y >= boardWidth));
                    
                    break;
                    
                case 4:
                    //Reset the room
                    myRoom.resetRoom(2, 2, 3, 1, 1, namesString);
                    break;

                case 0:
                    //exit
                    System.out.println("Goodbye!");
                    break;

                default:
                    break;
            }
            
            System.out.println();
            
        } while (option != 0); 
        
    }
    
    static void version2(){
        
        System.out.println("Make sure you run the \"Main\" module in the " +
                "Graphics folder, not this file!");
        
    }

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        testingMode = false;
        
        version2();
        
    }
    
 }
