package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;


/**
 * Allows farmer to sow/create crops
 */
public class SowAction extends Action {
    Location location;

    /**
     * Constructor
     * @param newLocation location to be sowed.
     */
    public SowAction(Location newLocation){
        this.location = newLocation;
    }

    /**
     * executes SowAction.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return descriptor for SowAction
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        this.location.setGround(new Crop());
        return menuDescription(actor);
    }

    /**
     * returns descriptor for SowAction.
     * @param actor The actor performing the action.
     * @return descriptor for SowAction
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " planted a crop." ;
    }
}
