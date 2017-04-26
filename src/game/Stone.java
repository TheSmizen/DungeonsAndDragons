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
public class Stone extends InAnimate{
    
    public Stone(int x, int y){
        super(x,y,'S',"Stone");
    }
    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String displayAttributes(){
        String returnString = super.displayAttributes();
        returnString = returnString + "\nType: Stone";
        return returnString;
    }
    

}
