package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

import java.util.Random;

public class SpawnAction extends Action {
    /**
     * Class that allows actor to spawn zombies
     */

    public static final int SPAWN_COUNT = 5;
    private Random rand = new Random();
    private String name;
    private int x,y;

    private String[] zombieNames = {"Pineapple on Pizza", "Good Smelling Vegetable", "Bob", "Steve", "Shawarma Jones", };

    /**
     * spawns 5 zombies at random points in map. (location rng copied from place random human in application)
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a menudescription stating actor spawned zombies
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        for (int i = 0; i < SPAWN_COUNT; i++) {
            do {
                name = zombieNames[rand.nextInt(zombieNames.length-1)] +"---" +  i;
                x = (int) Math.floor(Math.random() * 70.0 + 5.0);
                y = (int) Math.floor(Math.random() * 5.0 + 17.0);
            }
            while (map.at(x, y).containsAnActor());
            map.at(x,  y).addActor(new Zombie(name));
        }
        return menuDescription(actor);
    }

    /**
     * returns description for SpawnAction.
     * @param actor The actor performing the action.
     * @return description for SpawnAction.
     */
    @Override
    public String menuDescription(Actor actor) {
        return (actor + " spawns 5 zombies.");
    }
}