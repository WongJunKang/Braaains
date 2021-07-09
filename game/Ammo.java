package game;

import edu.monash.fit2099.engine.*;

import java.util.List;

/**
 * Base class for ammo object, e.g. SniperRifleAmmo , ShotgunRifleAmmo.
 */
public abstract class Ammo extends PortableItem {
    /**
     * instances
     */
    private int ammoCount;
    boolean hasWeapon;
    private Enum<ItemCapability>rangedWeaponType;
    private String weaponName; // return the weapon name for the ammo type


    // overload for programmer to set ammoCount themselves
    public Ammo(String name, char displayChar, Enum<ItemCapability> currWeaponType, int newAmmoCount) {
        super(name, displayChar);
        hasWeapon = false;
        rangedWeaponType = currWeaponType;
        ammoCount = newAmmoCount;
    }

    /**
     * Tick method allows Ammo object to "experience" passage of time/ turn.
     * Override to check if  actor has any weapon for the specific ammo currently.
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
        Player player = (Player)actor;
        // check player currently has the required Weapon for the ammo.
        hasWeapon = player.hasCapability(rangedWeaponType);
        // check weapon name of current ammo type.
        weaponName = rangedWeaponType == ItemCapability.SHOTGUN ? Shotgun.WEAPON_NAME: SniperRifle.WEAPON_NAME;
    }

    /**
     * Getter for allowable actions of Ammo object.
     * @return a list of allowable actions of the ammo object.
     */
    @Override
    public List<Action> getAllowableActions() {
        Actions actions = new Actions();
        actions.add(this.allowableActions);
        // check if item is in player inventory and player has the weapon for the specific ammo.
        if(this.hasCapability(ItemCapability.INVENTORY) && hasWeapon){
            actions.add(new ReloadAction(ammoCount, this, rangedWeaponType, weaponName));
        }
        return actions.getUnmodifiableActionList();
    }

}
