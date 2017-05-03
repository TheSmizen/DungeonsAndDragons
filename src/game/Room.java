package game;

/*
 * Class that stores the positions of all the entities
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author Arantza, Sean Mizen 16072916
 */

//2 holes, 2 stones, 3 monsters, 3 humans, 1 flying and 1 non-flying dragon
//In the initial state, the depth of the holes is a
//random number between 0 and 20, monsters random strength between 0-5
//humans: Harold, David and Clare. All the animate entities are
//100% healthy.

public class Room {
    //1D List with all the entities
    //Could have gone two dimensional, however 1D is fully functional and will
    //work with writing to/from a file much easier.
    private ArrayList<Entity> entities = new ArrayList<Entity>();
    private int gridWidth;
    /**
     *  Constructor method for the Room class.
     * @param x  column
     * @param y row
     * @param gridWidth width of the playing grid
     * @param holes number of holes in grid
     * @param stones number of stones in grid
     * @param monsters number of monsters in grid
     * @param fDragons number of flying dragons in grid
     * @param nDragons number of normal dragons in grid
     * @param humans array of strings - name of each human

     */
    public Room(int gridWidth, int holes, int stones, int monsters,int fDragons,
                int nDragons, String[] humans){
        this.gridWidth = gridWidth;

        if ((holes + stones + monsters + humans.length
                + fDragons + nDragons) > (gridWidth * gridWidth)){
            throw new UnsupportedOperationException("There are more entities than there are cells!");
        }
        
        resetRoom(holes, stones, monsters,
                fDragons, nDragons, humans);
        //CreateGrid
    }
    /**
     * Overloaded constructor - using default values
     */
    public Room(){
        
        this.gridWidth = 10;
        this.resetRoom();
        
    }

    /**
    * Set up a new room with entities in random places
    * first the room, must be clear of entities
    * @param holes number of holes in grid
    * @param stones number of stones in grid
    * @param monsters number of monsters in grid
    * @param fDragons number of flying dragons in grid
    * @param nDragons number of normal dragons in grid
    * @param humans array of strings - name of each human
    */
    public void resetRoom(int holes, int stones, int monsters,
                int fDragons, int nDragons, String[]humans) {
        
        clearRoom();
        int[] coords = new int[2];
        boolean isFlying;
        
        for(int i = 0; i < holes; i++){
            coords = newFreeCoordinates();
            Hole myHole = new Hole(coords[0],coords[1]);
            entities.add(myHole);
        }
        
        for(int i = 0; i < stones; i++){
            coords = newFreeCoordinates();
            Stone myStone = new Stone(coords[0],coords[1]);
            entities.add(myStone);
            
        }
        
        for(int i = 0; i < monsters; i++){
            coords = newFreeCoordinates();
            Monster myMonster = new Monster(coords[0],coords[1]);
            entities.add(myMonster);
        }
        
        isFlying = true;        
        for(int i = 0; i < fDragons; i++){
            coords = newFreeCoordinates();
            Dragon myDragon = new Dragon(coords[0],coords[1],isFlying);
            entities.add(myDragon);
        }
        
        isFlying = false;
        for(int i = 0; i < nDragons; i++){
            coords = newFreeCoordinates();
            Dragon myDragon = new Dragon(coords[0],coords[1],isFlying);
            entities.add(myDragon);
        }
        
        for(String humanName:humans){
            coords = newFreeCoordinates();
            Human myHuman = new Human(coords[0],coords[1],humanName);
            entities.add(myHuman);
        }
        
    }
    /**
     * Overloaded modifier - using default values
     */
    public void resetRoom(){
        
        String[] namesString = new String[3];
        namesString[0] = "Harold";
        namesString[1] = "David";
        namesString[2] = "Clare";

        this.resetRoom(2, 2, 3, 1, 1, namesString);

    }
    
    /**
     *  Method that returns the pseudo-randomly generated coordinates
     *  of a free cell
     * PRE: the entity list has at least one fewer items than the area of the grid.
     * @return x,y coordinates as two item array of integers.
     */
    public int[] newFreeCoordinates(){
        int[] coordsArr = new int[2];
        Random randObj = new Random();

        do{
            coordsArr[0] = randObj.nextInt(this.gridWidth);
            coordsArr[1] = randObj.nextInt(this.gridWidth);
        } while (!isFree(coordsArr[0],coordsArr[1]));
        
        return coordsArr;
    }
    
    /**
     *  Method that returns the pseudo-randomly generated coordinates
     *  of a free cell adjacent to given coordinates (including diagonals)
     *  if no cell is free, will return source coordinates
     * PRE: the entity list has at least one fewer items than the area of the grid.
     * @param x x coordinate of the entity
     * @param y y coordinate of the entity
     * @return x,y coordinates as two item array of integers.
     */
    public int[] newFreeAdjacentCoordinates(int x, int y){
        int[] coords = new int[2];
        coords[0] = x;
        coords[1] = y;
        int[][] coordsArr = new int[8][2];
        Random randObj = new Random();
        int freeCellCount = 0;
        
        for(int j = (y - 1); j <= (y + 1); j++){
            for(int i = (x - 1); i <= (x + 1); i++){
                if(isFree(i,j)){
                    //isFree will not return true for the current cell
                    //as it is occupied by the current entity!
                    coordsArr[freeCellCount][0] = i;
                    coordsArr[freeCellCount][1] = j;
                    freeCellCount++;
                }
            }
        }
        
        if(freeCellCount > 0){
            coords = coordsArr[randObj.nextInt(freeCellCount)];
        }
        
        return coords;
    }

    /**
     *  Method that places a new entity into the 'entities' array
     *  method deviates from specifications: x and y are not params,
     *  this is because x and y are stored within the "Entity" object
     *  If cell is not free, entity will be placed in a random free cell
     * PRE: the entity list has at least one fewer items than the area of the grid.
     * @param ent Entity to be placed within the entities array
     */
    public void addNewEntity(Entity ent){
        int x,y;
        x = ent.getX();
        y = ent.getY();
        
        if(isFree(x,y)){
            entities.add(ent);
        }
        else{
            int[] newCoords = newFreeCoordinates();
            ent.setX(newCoords[0]);
            ent.setY(newCoords[1]);
        }
        
    }
    
    
    public ArrayList<Entity> getEntities(){
        return entities;
    }
    
    /**
     *  Empty the list of entities
     */
    public void clearRoom() {
        entities.clear();
    } 

    /**
     *  Method that returns false if a cell is occupied by an entity.
     * @param x  column
     * @param y row
     * @return true is cell free
     */
    public boolean isFree(int x, int y) {
        boolean free = true;
        int position = getPosition(x,y);
        
        if (position >= 0){
            free = false;
        }
        if(!((0 <= x && x < this.gridWidth) && (0 <= y && y < this.gridWidth))){
            //This is the validation to check that the movement is within the grid
            free = false;
        }
        
        return free;
    }

    /**
     *  Method that returns the position in the arrayList occupied by an entity 
     *  given its coordinates
     * @param x column
     * @param y row
     * @return integer position of item the ArrayList, or -1 if the cell is free
     */
    private int getPosition (int x, int y) {
        int position = -1, i = 0;
       
        for(Entity item:entities){
            if ((item.getX() == x) && (item.getY() == y)){
                position = i;
                break;
            }
            i++;
        }
       
        return position;
    }

    /**
     *  Display all the properties of an entity that occupies a particular cell
     *  Utilises displayAttributes method of Entity class (and children)
     * 
     * PRE: Cell must not be empty.
     * @param x column
     * @param y row
     * @return String with the properties of the entity.
     */
    public String displayEntity (int x, int y) {
       Entity myEntity = entities.get(getPosition(x,y));
       String returnString = myEntity.displayAttributes();

       return returnString;
    }
    
    
    /**
     *  Method that moves all the entities that are animated on the room
     */
    public void move() {
        
        entities.forEach((ent) -> {
            ent.move(this);
        });
        
}

    /**
     *  Method that returns a string used to display the room as specified
     * @return String representation of the room to read as command line
     */
    public String toString() {
        
       String boardString = "";
       boardString = boardString.concat("  ");
       
       for(int i = 0; i < gridWidth; i++){
           boardString = boardString.concat(i + " ");
       }
       
       for(int y = 0; y < (gridWidth); y++){
            boardString = boardString.concat("\n");
            for(int x = 0; x < gridWidth; x++){
               
                if(x==0){
                    boardString = boardString.concat(y + " ");
                }
               
                if(isFree(x,y)){
                   boardString = boardString.concat(". ");
                }
                else{
                    boardString = boardString.concat(entities.get(
                            getPosition(x,y)).getSymbol() + " ");
                }
               
            }

       }
       return boardString;
    }
    
//Dragon #
//Human @
//Monster *
///Stone S
///Hole O

    /**
     * Method for that clears the room and creates a new list with the
     * entities read in a text or CSV file
     * PRE: filePath exists and is in the correct format.
     * @param filePath Full file path of text or csv file to read.
     * @throws FileNotFoundException 
     */
    public void loadEntities(String filePath) throws FileNotFoundException {
        
        //File format:
        //first row is grid width (in later versions may represent more data)
        //after is the list of entites:
        //x,y,Type[,health||depth][,name||flying||strength]
        
        FileInputStream inFile = new FileInputStream(filePath);
        Scanner inFileScanner = new Scanner(inFile);
        
        this.clearRoom();
        this.gridWidth = Integer.parseInt(inFileScanner.nextLine());
        
        String[] entityStrings;
        
        while(inFileScanner.hasNext()){
            entityStrings = inFileScanner.nextLine().split(",");
            
            switch(entityStrings[2]){//Decision: which type of entity is this?
                case "#":
                    this.addNewEntity(new Dragon(
                            Integer.parseInt(entityStrings[0]),//x
                            Integer.parseInt(entityStrings[1]),//y
                            Integer.parseInt(entityStrings[3]),//health
                            Boolean.parseBoolean(entityStrings[4])//isFlying
                            ));
                    break;
                case "@":
                    this.addNewEntity(new Human(
                            Integer.parseInt(entityStrings[0]),//x
                            Integer.parseInt(entityStrings[1]),//y
                            Integer.parseInt(entityStrings[3]),//health
                            entityStrings[4]));//name
                    break;
                case "*":
                    this.addNewEntity(new Monster(
                            Integer.parseInt(entityStrings[0]),//x
                            Integer.parseInt(entityStrings[1]),//y
                            Integer.parseInt(entityStrings[3]),//health
                            Integer.parseInt(entityStrings[4])));//strength
                    break;
                case "S":
                    this.addNewEntity(new Stone(
                            Integer.parseInt(entityStrings[0]),//x
                            Integer.parseInt(entityStrings[1])));//y
                    break;
                case "O":
                    this.addNewEntity(new Hole(
                            Integer.parseInt(entityStrings[0]),//x
                            Integer.parseInt(entityStrings[1]),//y
                            Integer.parseInt(entityStrings[3])));//depth
                    break;
                    
            }
        }
    }
    
    /**
     * method that saves the entities and their positions into a text or CSV file
     * 
     * @param filePath Full file path of text or csv file to read.
     * @throws FileNotFoundException 
     */
    public void saveEntities(String filePath) throws FileNotFoundException{
        
        //File format:
        //first row is grid width (in later versions may represent more data)
        //after is the list of entites:
        //x,y,Type[,health||depth][,name||flying||strength]
        
        PrintWriter outFile = new PrintWriter(filePath);
        
        outFile.println(this.gridWidth);
        
        entities.forEach((ent) -> {
            outFile.println(ent.toString());
        });
        
        outFile.close();
    }
}

