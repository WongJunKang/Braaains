package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * Class for Zombie Club.
 */
public class ZombieClub extends WeaponItem {
    /**
     * Constructor.
     */

    public ZombieClub() {
        super("Zombie Club", '|', 45, "slams");
        this.addCapability(ItemCapability.MELEEWEAPON);
    }
}
