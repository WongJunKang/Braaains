package game;

import edu.monash.fit2099.engine.*;

import java.util.Random;

/**
 * A subclass of human class that has access to fertilise, harvest and sow behaviour.
 */
public class Farmer extends Human {
    private Behaviour[] behaviours = {
            new RunAwayBehaviour(),
            new EatBehaviour(),
            new SowBehaviour(),
            new HarvestBehaviour(),
            new FertiliseBehaviour(),
            new PickUpItemBehaviour(),
            new WanderBehaviour()
    };

    /**
     * Constructor
     *
     * @param name, name of the farmer's actor
     */
    public Farmer(String name) {
        super(name, 'F', 150);
    }

    /**
     * Method that allows farmer to perform actions during its play turn.
     *
     * @param actions actions performed by the farmer.
     * @param lastAction last action performed by the farmer.
     * @param map map that the current farmer is located
     * @param display the display
     * @return an action to be performed else DoNothingAction() if all other actions return null.
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
            for (Behaviour behaviour : behaviours) {
                Action action = behaviour.getAction(this, map);
                if (action != null)
                    return action;
            }
            return new DoNothingAction();
        }
    }


