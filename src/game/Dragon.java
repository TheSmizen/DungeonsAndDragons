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
public class Dragon extends Animate{
    
    private final boolean flying;
    
    public Dragon(int x, int y, boolean flying){
        super(x,y,'#',"Dragon");
        this.flying = flying;
    }
    public Dragon(int x, int y, int health, boolean flying){
        super(x,y,'#',"Dragon");
        this.setHealth(health);
        this.flying = flying;
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
        
        if(this.getHealth() > 0){
            int newCoords[];
            
            if(this.isFlying()){
                
                newCoords = r.newFreeCoordinates();
                this.setX(newCoords[0]);
                this.setY(newCoords[1]);
                
            }
            else{
                
                newCoords = r.newFreeAdjacentCoordinates(x,y);
                this.setX(newCoords[0]);
                this.setY(newCoords[1]);
                
                if ((x != this.getX()) || (y != this.getY())){
                    this.setHealth(this.getHealth()-(new Random().nextInt(6)));
                }
                
            }
            
        }
        
    }
    
    public boolean isFlying(){
        return this.flying;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String displayAttributes(){
        String returnString = super.displayAttributes();
        returnString = returnString + "\nType: Dragon\nIs Flying: "
                + isFlying();
        return returnString;
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public String toString(){
        String returnString = super.toString();
        return returnString + "," + this.flying;
    }
    
}

