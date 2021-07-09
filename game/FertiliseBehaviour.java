package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * A class that generates a fertilise action if the current Actor is standing on a unripe crop
 */
 public class FertiliseBehaviour implements Behaviour{

    /**
     * Method that a fertiliseAction on a unripe crop, return null if no unripe crop is found.
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return a fertiliseAction on a unripe crop, if no unripe crop is found, return null.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        // check if the crop has already ripen
        if(map.locationOf(actor).getGround().hasCapability(FarmingCapability.UNRIPE)) {
            return new FertiliseAction();
        }
        return null;

    }
}
