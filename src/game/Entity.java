

/*
 * Abstract Class Entity
 * 
 */

package game;
import java.util.Random;

/**
 *
 * @author Arantza
 */
//Dragon #
//Human @
//Monster *
///Stone S
///Hole O
public abstract class Entity {
    private char symbol; // symbol that represents the entity
    private String type; // every entity is of a type
    private int x; // x coordinate in the room
    private int y; // y coordinate in the room
 
 
    public Entity (int x, int y) {
        this.type = "entity";
        this.x = x;
        this.y = y;
    }


    public char getSymbol() {
        return symbol;
    }
    public void setSymbol(char c) {
        symbol = c;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX (int newx) {
        this.x=newx;
    }
    public void setY (int newy) {
        this.y=newy;
    }
    
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Abstract method that moves the entity by the requirement specified rules.
     * @param r the room with the positions of all the entities
     */
    public abstract void move(Room r);
    
    /**
     * Method which displays attributes of an entity for reading on the command line.
     * @return Readable string representation of entity
     */
    public String displayAttributes(){
        String returnString = "Entity at co-ordinates " + this.x + ","
                + this.y + ":";
        return returnString;
    }
    
/**
 * Method which returns information about an entity - in comma delimited format
 * @return string with information about an abstract entity 
 */
   public String toString() {
       String s =  this.x + "," + this.y + "," + this.symbol;
       return s;
   }

}








