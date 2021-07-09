package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DoNothingAction;

/**
 * Allows Zombies to do speech like yelling.
 */
public class SpeechAction extends DoNothingAction {

    /**
     * Constructor
     */
    public SpeechAction(){
    }

    /**
     * descriptor for SpeechAction
     * @param actor The actor performing the action.
     * @return String description of SpeechAction
     */
    @Override
    public String menuDescription(Actor actor) {
        return (actor + " yells Braaaains");
    }

    /**
     * assigns hotkey (null because zombies aren't controllable, and as such do not need hotkeys.)
     * @return null
     */
    @Override
    public String hotkey() {
        return null;
    }

}
