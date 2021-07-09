package game;

import edu.monash.fit2099.engine.*;

public class MamboMarie extends ZombieActor{

    /**
     * Class for mambo marie
     */

    private int tick = 0;
    public static final int TURN_LIMIT = 30;
    public static final int SPAWN_TURN = 10;
    private Behaviour[] behaviours = {
            new AttackBehaviour(ZombieCapability.ALIVE),
            new HuntBehaviour(Human.class, 10),
            new WanderBehaviour()
    };

    /**
     * constructor.
     */
    public MamboMarie() {super("Mambo Marie", 'M', 200, ZombieCapability.UNDEAD);}

    /**
     * runs mambo marie.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return an action
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if (tick >= TURN_LIMIT){
            return new LeaveAction();
        }
        else{
            tick ++;
            if ((tick % SPAWN_TURN) == 0){
                return new SpawnAction();
            }
            else{
                for (Behaviour behaviour : behaviours) {
                    Action action = behaviour.getAction(this, map);
                    if (action != null)
                        return action;
                }
            }
        }
        return new DoNothingAction();
    }
}
