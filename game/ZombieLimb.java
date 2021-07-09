package game;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;

/**
 * Class for zombie limbs.
 */
public class ZombieLimb extends CraftableItem implements Weapon {

    private int damage;
    private String verb;

    /**
     * Constructor
     * @param name name of item
     * @param displayChar display character of item
     * @param damage damage dealt when used to attack
     * @param newVerb verb used when attacking
     * @param newItem item to be crafted into
     */
    public ZombieLimb(String name, char displayChar,int damage, String newVerb, Item newItem) {
        super(name, displayChar, newItem);
        this.damage = damage;
        this.verb = newVerb;
        this.addCapability(ItemCapability.MELEEWEAPON);
    }

    /**
     * getter for item's damage value
     * @return damage of item
     */
    @Override
    public int damage() {
        return damage;
    }

    /**
     * getter for item's verb when used to attack.
     * @return verb of item
     */
    @Override
    public String verb() {
        return verb;
    }
}
