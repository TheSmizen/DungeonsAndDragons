/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.Random;

/**
 *
 * @author Smizen
 */
public class Monster extends Animate{
    
    //0 <= strength <= 5 as per requirements
    private final int strength;
    
    public Monster(int x, int y){
        super(x,y,'*',"Monster");
        this.strength = (new Random()).nextInt(6);
    }
    public Monster(int x, int y, int health, int strength){//Overloaded constructor for game saving/loading
        super(x,y,'*',"Monster");
        this.setHealth(health);
        this.strength = strength;
        
    }
    
    
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
        
        
        int newCoords[] = new int[2];
        int[][] possibleCoords = new int[4][2];
        int possibleCoordsCount = 0;
        
        
        if(r.isFree(x + this.getStrength(),y)){//is "Move right strength places" possible
            newCoords[0] = x + this.getStrength();
            newCoords[1] = y;
            possibleCoords[possibleCoordsCount] = newCoords;
            possibleCoordsCount++;
        }
        if(r.isFree(x - this.getStrength(),y)){//is "Move left strength places" possible
            newCoords[0] = x - this.getStrength();
            newCoords[1] = y;
            possibleCoords[possibleCoordsCount] = newCoords;
            possibleCoordsCount++;
        }
        if(r.isFree(x,y + this.getStrength())){//is "Move up strength places" possible
            newCoords[0] = x;
            newCoords[1] = y + this.getStrength();
            possibleCoords[possibleCoordsCount] = newCoords;
            possibleCoordsCount++;
        }
        if(r.isFree(x, y - this.getStrength())){//is "Move down strength places" possible
            newCoords[0] = x;
            newCoords[1] = y - this.getStrength();
            possibleCoords[possibleCoordsCount] = newCoords;
            possibleCoordsCount++;
        }
        
        
        if (possibleCoordsCount > 0){//Minimum one move was legal and possible
            newCoords = possibleCoords[(new Random()).nextInt(possibleCoordsCount)];
            this.setX(newCoords[0]);
            this.setY(newCoords[1]);
            this.setHealth(this.getHealth()-(this.getStrength()));
        }
        else{//none of the above moves were possible.
            newCoords = r.newFreeAdjacentCoordinates(x,y);
            if(newCoords[0] != x || newCoords[1] != y){//Adjacent tile was free
                this.setX(newCoords[0]);
                this.setY(newCoords[1]);
                this.setHealth(this.getHealth()-(this.getStrength()));
            }
            //Otherwise do not move, do not reduce health
        }
        
    }
    
    public int getStrength(){
        return strength;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String displayAttributes(){
        String returnString = super.displayAttributes();
        returnString = returnString + "\nType: Monster\nStrength: "
                + getStrength();
        return returnString;
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public String toString(){
        String returnString = super.toString();
        return returnString + "," + this.strength;
    }
    
}
