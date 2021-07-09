package game;

import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

import java.util.Random;

public class MamboTotem extends Item {
    /**
     * item that checks status of mambo marie and spawns her if meets condition
     */

    private Boolean spawned, killed;
    private Display display;
    public static final int SPAWN_CHANCE = 5, ROLL_CHANCE = 100;
    protected Random rand = new Random();
    /***
     * Constructor.
     * @param worldDisplay, display for world
     */
    public MamboTotem(Display worldDisplay) {
        super("Totem", '.', false);
        display = worldDisplay;
        addCapability(ItemCapability.TOTEM);
        spawned = false;
        killed = false;
    }

    /**
     * allows items to experience time and determines mambo marie spawn based on certain conditions and rng
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        if ((!killed)&&(!spawned)){
            if (rand.nextInt(ROLL_CHANCE) < SPAWN_CHANCE){
                marieSpawn(currentLocation);
            }
        }
    }

    /**
     * spawns mambo marie
     * @param spawnLocation location that mambo marie will spawn
     */
    public void marieSpawn(Location spawnLocation){
        spawned = true;
        spawnLocation.addActor(new MamboMarie());
        display.println("Mambo Marie enters the Area.");
    }

    /**
     * resets spawn boolean. used when she despawns.
     */
    public void resetSpawn(){
        spawned = false;
    }

    /**
     * sets killed to true. done when she is killed
     */
    public void setKilled(){
        killed = true;
    }

    /**
     * checks if mambo marie is killed
     * @return boolean value of killed
     */
    public Boolean getKilled(){
        return killed;
    }
}
