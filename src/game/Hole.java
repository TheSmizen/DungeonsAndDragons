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
public class Hole extends InAnimate{
    private final int depth;
    public Hole(int x, int y){
        super(x,y,'O',"Hole");
        this.depth = (new Random()).nextInt(19) + 1;
    }
    public Hole(int x, int y,int depth){//Overloaded method: for loading a saved game
        super(x,y,'O',"Hole");
        this.depth = depth;
    }
    
    public int getDepth(){
        return this.depth;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String displayAttributes(){
        String returnString = super.displayAttributes();
        returnString = returnString + "\nType: Hole\nDepth: " + getDepth();
        return returnString;
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public String toString(){
        String returnString = super.toString();
        return returnString + "," + this.depth;
    }
}
