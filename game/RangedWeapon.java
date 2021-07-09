package game;
import edu.monash.fit2099.engine.WeaponItem;

import java.util.ArrayList;

/**
 * Base class for RangedWeapon (SniperRifle and Shotgun.)
 */
public abstract class RangedWeapon extends WeaponItem {
    /**
     * instances
     */
    private int ammoCount;
    private int accuracy;


    /**
     * Constructor.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param accuracy  accuracy of the weapon
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param newAmmoCount count of ammo
     */
    public RangedWeapon(String name, char displayChar, int damage,  int accuracy, String verb, int newAmmoCount) {
        super(name, displayChar, damage, verb);
        this.ammoCount = newAmmoCount;
        this.accuracy = accuracy;
        addCapability(ItemCapability.RANGED);
    }

    /**
     * increment ammoCount of current ranged weapon by set amount (newAmmoCount).
     * @param newAmmoCount, the number of ammo to reload.
     */
    public void addAmmo(int newAmmoCount){
        this.ammoCount += newAmmoCount;
    }

    /**
     * decrement the number of ammo by 1
     * will be called when the ranged weapon is fired.
     */
    public void minusAmmo(){
        ammoCount --;
    }

    /**
     * The getter for current ammoCount of current ranged weapon.
     * @return current ammo count of the ranged weapon.
     */
    public int getAmmoCount(){
        return ammoCount;
    }

    /**
     * getter for accuracy
     * @return accuracy of the weapon
     */
    public int getAccuracy(){
        return accuracy;
    }

}
