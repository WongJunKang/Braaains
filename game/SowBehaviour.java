package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Allows farmers access to SowAction.
 */
public class SowBehaviour implements Behaviour {
    private static final int SOW_PROBABILITY_DENO = 100, SOW_PROBABILITY_NUM = 33;

    /**
     * determines if SowAction is allowed based on random number generator (rng) result
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return returns SowAction if rng of SOW_PROBABILITY_DENO returns an int is smaller or equal to SOW_PROBABILITY_NUM,
     *          or null if otherwise.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Random random = new Random();
        // get adjacent ground.
        List<Exit> exits = new ArrayList<>(map.locationOf(actor).getExits());
        // if actor adjacent location is can be sowed (dirt), and its within 33 percent chance, perform sow action.
        // check probability (33%)
        if(random.nextInt(SOW_PROBABILITY_DENO) <= SOW_PROBABILITY_NUM){
            // Loop through all adjacent ground to find a sowable ground and return sow action.
            for(Exit e: exits){
                // check if ground is sowable and does not have actor on it.
                boolean isSowable = e.getDestination().getGround().hasCapability(FarmingCapability.SOWABLE);
                boolean containsActor = e.getDestination().containsAnActor();

                if(isSowable && !(containsActor)){
                        return new SowAction(e.getDestination());
                    }
                }
        }
        return null;
    }
}
