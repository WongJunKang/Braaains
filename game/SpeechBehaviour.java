package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

import java.util.Random;

/**
 * Allows Zombies to do speech
 */
public class SpeechBehaviour implements Behaviour {
    private static final int SPEECH_CHANCE = 10;

    /**
     * uses a random number generator(rng) to determine if zombie will do speech
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return SpeechAction if rng returns integer is smaller or equal to SPEECH_CHANCE, null otherwise
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Random random = new Random();
        if (random.nextInt(100) <= SPEECH_CHANCE){
            return new SpeechAction();
        }
        return null;
    }
}
