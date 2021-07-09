package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;

/**
 * The main class for the zombie apocalypse game.
 *
 */
public class Application {

	public static void main(String[] args) {
		Display display = new Display();
//		World world = new EndableWorld(display);
		World world = new World(display);

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Fence(), new Tree());
		
		List<String> map = Arrays.asList(
		"................................................................................",
		"................................................................................",
		"....................................##########..................................",
		"..........................###########........#####..............................",
		"............++...........##......................########.......................",
		"..............++++.......#..............................##......................",
		".............+++...+++...#...............................#......................",
		".........................##..............................##.....................",
		"..........................#...............................#.....................",
		".........................##...............................##....................",
		".........................#...............................##.....................",
		".........................###..............................##....................",
		"...........................####......................######.....................",
		"..............................#########.........####............................",
		"............+++.......................#.........#...............................",
		".............+++++....................#.........#...............................",
		"...............++........................................+++++..................",
		".............+++....................................++++++++....................",
		"............+++.......................................+++.......................",
		"................................................................................",
		".........................................................................++.....",
		"........................................................................++.++...",
		".........................................................................++++...",
		"..........................................................................++....",
		"................................................................................");

		List<String> town = Arrays.asList(
		"................................................................................",
		"................................................................................",
		"..........................#####################.................................",
		".........................##...................##................................",
		".........................#.....................#................................",
		"...............###########.....................###########......................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"...............###########.....................###########......................",
		".........................#.....................#................................",
		".........................##...................##................................",
		"..........................#####################.................................",
		"................................................................................",
		"................................................................................"
		);
		GameMap gameMap = new GameMap(groundFactory, map );
		world.addGameMap(gameMap);
		GameMap townMap = new GameMap(groundFactory, town);
		world.addGameMap(townMap);
		


		int x, y;

		String[] farmers = {"Farmer John", "Farmer Dave", "Farmer Alexander", "Farmer Johnathan"};
		for (String name : farmers) {
			do {
				x = (int) Math.floor(Math.random() * 20.0 + 30.0);
				y = (int) Math.floor(Math.random() * 20);
			}
			while (gameMap.at(x, y).containsAnActor());
			gameMap.at(x,  y).addActor(new Farmer(name));
		}

	    // Place some random humans
		String[] humans = {"Carlton", "May", "Vicente", "Andrea", "Wendy",
				"Elina", "Winter", "Clem", "Jacob", "Jaquelyn"};

		for (String name : humans) {
			do {
				x = (int) Math.floor(Math.random() * 20.0 + 30.0);
				y = (int) Math.floor(Math.random() * 7.0 + 5.0);
			}
			while (gameMap.at(x, y).containsAnActor());
			gameMap.at(x,  y).addActor(new Human(name));

		}

		// place a simple weapon
		gameMap.at(79, 24).addItem(new MamboTotem(display));
		gameMap.at(0,1).addItem(new Vehicle(townMap));
		townMap.at(0,1).addItem(new Vehicle(gameMap));

		townMap.at(41, 6).addItem(new Shotgun());
		townMap.at(47, 7).addItem(new ShotgunAmmo());
		townMap.at(43, 7).addItem(new ShotgunAmmo());
		townMap.at(41, 7).addItem(new ShotgunAmmo());
		townMap.at(42, 4).addItem(new SniperRifleAmmo());
		townMap.at(49, 4).addItem(new SniperRifleAmmo());
		townMap.at(43, 4).addItem(new SniperRifleAmmo());
		townMap.at(42, 9).addItem(new SniperRifle());



		String[] zombies = {"Groan", "Boo", "Uuuurgh", "Mortalis", "Gaaaah",
				"Cleaner", "Retcher", "Scratcher", "Decay", "Brute", "Brooder","Aaargh"};

		for (String name : zombies) {
			do {
				x = (int) Math.floor(Math.random() * 70.0 + 5.0);
				y = (int) Math.floor(Math.random() * 5.0 + 17.0);
			}
			while (gameMap.at(x, y).containsAnActor());
			gameMap.at(x,  y).addActor(new Zombie(name));
		}


		Actor player = new Player("Player", '@', 500);
		world.addPlayer(player, gameMap.at(20, 1));


		//		// specially set up for testing
//		gameMap.at(71, 4).addItem(new Shotgun());
//		gameMap.at(71, 4).addItem(new ShotgunAmmo());
//		gameMap.at(71, 4).addItem(new SniperRifle());
//		gameMap.at(71, 4).addItem(new SniperRifleAmmo());
//
//
//		for(int i=63; i < 80; i ++){
//			for(int j=0; j < 10; j++){
//				if(!(gameMap.at(i, j).containsAnActor())){
//					gameMap.at(i,  j).addActor(new Zombie("a"+i+ ", " + j));
//				}
//			}
//		}


		world.run();
	}
}
