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
public abstract class InAnimate extends Entity{
    
    public InAnimate(int x, int y, char symbol, String type){
        super(x,y);
        super.setSymbol(symbol);
        super.setType(type);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void move(Room r){
        //do nothing
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String displayAttributes(){
        String returnString = super.displayAttributes();
        returnString = returnString + "\nClass: InAnimate";
        return returnString;
    }
        
}
