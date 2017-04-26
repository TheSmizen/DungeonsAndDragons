/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author Smizen
 */
public class Human extends Animate{
    
    private final String name;
    
    public Human(int x, int y, String name){
        super(x,y,'@',"Human");
        this.name = name;
    }    
    public Human(int x, int y, int health, String name){
        super(x,y,'@',"Human");
        this.setHealth(health);
        this.name = name;
    }
    
    //Humans always move one cell to on their right
    //provided that this cell is not occupied and within bounds.
    //Defaults to a random cell adjacent to the current cell.
    //Their health is reduced by one every time that they move.
    /**
     * {@inheritDoc}
     */
    @Override
    public void move(Room r){
        
        if(this.getHealth() <= 0){
            return;
        }
        int x = this.getX();
        int y = this.getY();
        
        
        if(r.isFree((x+1), y)){
        this.setX(x+1);
        }
        else{
            int newCoords[] = r.newFreeAdjacentCoordinates(x,y);

            this.setX(newCoords[0]);
            this.setY(newCoords[1]);
        }

        if ((x != this.getX()) || (y != this.getY())){
            this.setHealth(this.getHealth()-1);
        }
        
    }
    
    public String getName(){
        return this.name;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String displayAttributes(){
        String returnString = super.displayAttributes();
        returnString = returnString + "\nType: Human\nName: " + getName();
        return returnString;
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public String toString(){
        String returnString = super.toString();
        return returnString + "," + this.name;
    }
}
