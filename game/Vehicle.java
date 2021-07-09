package game;

import edu.monash.fit2099.engine.*;

import java.util.List;

public class Vehicle extends Item {
    /**
     * Class to create vehicle. vehicle allows for map traversal.
     */
    private GameMap moveTarget;

    /***
     * Constructor.
     *  @param targetMap the map actor will move to when interacting with this item
     */
    public Vehicle(GameMap targetMap) {
        super("Vehicle", 'V', false);
        addCapability(ItemCapability.VEHICLE);
        moveTarget = targetMap;
    }

    /**
     * returns allowable actions for this item. Added DriveAction(moveTarget) as allowable action.
     * @return a list of allowable actions
     */
    @Override
    public List<Action> getAllowableActions() {
        Actions actions = new Actions();
        actions.add(allowableActions);
        actions.add(new DriveAction(moveTarget));
        return actions.getUnmodifiableActionList();
    }
}
