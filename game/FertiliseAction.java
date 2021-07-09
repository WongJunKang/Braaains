package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Class that perform fertilise action to the crop that the player is currently standing on.
 */
public class FertiliseAction extends Action {
    /**
     * Method that fertilises a crop.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A String description of fertilise action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ((Crop) map.locationOf(actor).getGround()).fertilise();
        return menuDescription(actor);
    }

    /**
     * Method that display a String description of a fertilise action.
     * @param actor The actor performing the action.
     * @return a String description of fertilise action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return (actor + " fertilise a crop") ;
    }
}
