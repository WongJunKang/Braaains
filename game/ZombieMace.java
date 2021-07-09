package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * Class for Zombie Mace.
 */
public class ZombieMace extends WeaponItem {
    /**
     * Constructor.
     */
    public ZombieMace() {
        super("Zombie Mace", 'M', 50, "dunks");
        this.addCapability(ItemCapability.MELEEWEAPON);
    }

}
