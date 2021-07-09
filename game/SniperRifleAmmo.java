package game;

/**
 * Class that creates SniperRifleAmmo for SniperRifle ReloadAction.
 */
public class SniperRifleAmmo extends Ammo {
    // set default ammo count of SniperRifleAmmo pack to 2.
    private static final int SNIPER_AMMO_COUNT = 2;


    /**
     * Constructor of sniper rifle object.
     */
    public SniperRifleAmmo() {
        super("Sniper Rifle Ammo", 'A', ItemCapability.SNIPERRIFLE, SNIPER_AMMO_COUNT);
    }
}
