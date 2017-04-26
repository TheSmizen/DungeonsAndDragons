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
public abstract class Animate extends Entity{
    private int health; // 0 <= health <= 100 measure of entity health
    
    public Animate(int x, int y, char symbol, String type){
        super(x,y);
        super.setSymbol(symbol);
        super.setType(type);
        
        this.health = 100;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String displayAttributes(){
        String returnString = super.displayAttributes();
        returnString = returnString + "\nClass: Animate\nHealth: "
                + this.health;
        return returnString;
    }
    
    public int getHealth(){
        return this.health;
    }
    public void setHealth(int newHealth){
        this.health = newHealth;
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public String toString(){
        String returnString = super.toString();
        return returnString + "," + this.health;
    }
    
}

