package game;


/**
 * Class that creates ShotgunAmmo for shotgun ReloadAction.
 */
public class ShotgunAmmo extends Ammo {
    /**
     * final onstant
     */
    private static final int SHOTGUN_AMMO_COUNT = 3;

    /**
     * Constructor
     */
    public ShotgunAmmo() {
        super("Shotgun Ammo", 'a', ItemCapability.SHOTGUN, SHOTGUN_AMMO_COUNT);
    }

}
