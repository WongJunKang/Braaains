package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;

/**
 * A class the extends Ground and allow for fertilise and harvest action.
 */
public class Crop extends Ground{
    private int age = 0;
    private static final int RIPEN_AGE = 20;
    private static final int GROW_RATE = 10;

    /**
     * Constructor.
     *
     */
    public Crop() {
        super('c');
        // Add the capability of harvest and fertilise to crop type object.
        addCapability(FarmingCapability.UNRIPE);
    }

    /**
     * Method that allows crop to "experience" play turn, every time a turn passes the age of the crop will be
     * incremented by 1, and when it reaches age 20 or greater, the crop will gain an additional capability named
     * FarmingCapability.RIPE
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        if (age >= RIPEN_AGE){
            if(this.hasCapability(FarmingCapability.UNRIPE)){
                this.displayChar = 'C';
                // doesn't allow any actor to fertilise, once it has ripen.
                removeCapability(FarmingCapability.UNRIPE);
                addCapability(FarmingCapability.RIPE);
            }
        }
        age++;
    }

    /**
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return actions, actions that can be performed to the crop.
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction){
        Actions actions = new Actions();
        // add HarvestAction into allowable action of ripen crop.
        if(this.age > RIPEN_AGE){
            actions.add(new HarvestAction(location));
        }
        return actions;
    }


    /**
     * This method increment the age of the crop by a "GROW_RATE".
     */
    public void fertilise(){
        age += GROW_RATE;
    }

    /**
     * Getter of crop's age
     * @return age of the crop
     */
    public int getCropAge(){
        return age;
    }

}
