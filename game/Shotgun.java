package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;

import java.util.List;

public class Shotgun extends RangedWeapon {
    /**
     * Constants
     */
    private static final int DEFAULT_ACCURACY = 75;

    /**
     * instances
     */
    public static final String WEAPON_NAME = "Shotgun";

    /**
     * Constructor.
     *
     */
    public Shotgun() {
        super(WEAPON_NAME, 'S', 30, DEFAULT_ACCURACY, "boom", 3);
        addCapability(ItemCapability.SHOTGUN);
    }

    /**
     * getter for shotgun's allowable actions
     * @return list of allowable actions of shotgun
     */
    @Override
    public List<Action> getAllowableActions() {
        Actions actions = new Actions();
        actions.add(this.allowableActions);
        // player can only use shotgun if it is inside player's inventory and has bullet
        if(this.hasCapability(ItemCapability.INVENTORY) && this.getAmmoCount() > 0){
            actions.add(new ShotgunMenu());
        }
        return actions.getUnmodifiableActionList();
    }

}
