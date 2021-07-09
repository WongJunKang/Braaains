package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that generates an HarvestAction if the current Actor is standing on or next to a ripen crop.
 */
public class HarvestBehaviour implements Behaviour {
    /**
     * Method that returns a HarvestAction if the current or adjacent ground of actor is a ripe crop, else return null.
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return HarvestAction if a ripe crop is detected, else null.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location actorLocation = map.locationOf(actor);

        // return harvest action if the actor(farmer) is currently standing on a ripe crop.
        if(actorLocation.getGround().hasCapability(FarmingCapability.RIPE)){
            return new HarvestAction(actorLocation);
        }else{ // standing next to a ripe crop
            List<Exit> exits = new ArrayList<>(actorLocation.getExits());
            for (Exit e : exits) {
                if (e.getDestination().getGround().hasCapability(FarmingCapability.RIPE)) {
                    return new HarvestAction(actorLocation);
                }
            }
        }
        return null;
    }
}