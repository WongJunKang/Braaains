package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Returns a MoveAction that will take the Human actor away from UNDEAD type actor.
 */
public class RunAwayBehaviour implements Behaviour {

    /**
     * Return a MoveActorAction that moves current actor(human) away from actor with ZombieCapability.UNDEAD. (non-human character.)
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return MoveActorAction that moves current actor(human) away from zombie.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        // higher priority then wander behaviour
        // Check exits of human exits to see which spot has the least zombie, and go to the spot.
        // if all same score return null (perform wander bahaviour)

        Location currentLocation = map.locationOf(actor);
        List<Exit> exits = validExits(new ArrayList<>(currentLocation.getExits()));
        int exitSize = exits.size();
        Location[] moveToLocation = new Location[exitSize];
        int[] tileScore = new int[exitSize];

        for(int i=0; i < exitSize; i++){ // for all exits available
            Exit exit = exits.get(i);
            moveToLocation[i] = exit.getDestination(); // initialise loop up array
            for(Exit eachExit: exit.getDestination().getExits()){ // get the exits of all exits
                Location location = eachExit.getDestination();
                if(location.containsAnActor()){
                    if(location.getActor().hasCapability(ZombieCapability.UNDEAD)){
                        tileScore[i] ++; // add 1 to the score of the tile
                    }
                }
            }
        }
        // find tile with minimum score (higher score implies )
        int index = findMinimum(tileScore);
        // check if no zombie around (range of 2)
        int sum = IntStream.of(tileScore).sum();
        if(sum == 0){ return null;}  // don't run away if no zombie around
        return new MoveActorAction(moveToLocation[index], exits.get(index).getName());// move actor to the location.
    }



    /**
     * return all exits that actors can move into.
     *
     * @param exits list of exits
     * @return a list of valid exits that current actor can move into.
     */
    private List<Exit> validExits(List<Exit> exits){
        List<Exit> validExits = new ArrayList<>();
        for(Exit exit:exits){
            if(!(exit.getDestination().containsAnActor())){
                validExits.add(exit);
            }
        }
        return validExits;
    }

    /**
     *  return index of minimum value in array
     * @param score list of integer indicating score of tiles.
     */
    private int findMinimum(int [] score){
        int index = 0;
        int min = score[index];
        for(int i=1; i<score.length; i++){
            if(score[i] < min){
                min = score[i];
                index = i;
            }
        }
        return index;
    }
}
