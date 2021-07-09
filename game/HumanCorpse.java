package game;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

public class HumanCorpse extends PortableItem {
    private static int RISE_FROM_DEATH = 5;
    private int countTurn;
    private int reviveTurn;
    private String name;

    /***
     * Constructor.
     * @param name  name of the human.
     */
    public HumanCorpse(String name) {
        super(name + " Corpse", 'X');
        this.name = name;
        countTurn = 0;
        reviveTurn = RISE_FROM_DEATH;
    }


    /**
     * Method that allows HumanCorpse to "experience" play turn, every time a turn passes the counter increases,
     * when the counter reaches its revive turn the HumanCorpse object will be replaced by a zombie.
     *
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        boolean inInventory = this.hasCapability(ItemCapability.INVENTORY);
        // if human supposed to turn into zombie, but some Actor is currently on the tile or Corpse inside inventory,
        // delay the revive turn.
        if((currentLocation.containsAnActor() || inInventory)&& countTurn == reviveTurn){
            reviveTurn ++;
        }
        if(countTurn == reviveTurn){
            currentLocation.removeItem(this); // remove current item.
            // set new ZombieName to (Human Name) + Zombie
            currentLocation.addActor(new Zombie(name + "Zombie"));
        }
        countTurn ++;
    }
}
