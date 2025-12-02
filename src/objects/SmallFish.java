package objects;

import pt.iscte.poo.game.Room;

public class SmallFish extends GameCharacter implements Slim {

	private static SmallFish sf = new SmallFish(null);
	
	private SmallFish(Room room) {
		super(room);
	}

	public int getStrength() {
		return 1; // SmallFish tem força leve
	}

	public static SmallFish getInstance() {
		return sf;
	}
	
	@Override
	public String getName() {
		return "smallFishLeft";
	}

	@Override
	public int getLayer() {
		return 1;
	}

}
