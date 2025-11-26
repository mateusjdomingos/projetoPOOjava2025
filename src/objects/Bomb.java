package objects;

import pt.iscte.poo.game.Room;

public class Bomb extends MovableObject implements Steppable {

	public Bomb(Room room) {
		super(room);
	}

	@Override
	public void step() {
		fall();
	}

	@Override
	public String getName() {
		return "bomb";
	}

	@Override
	public int getLayer() {
		return 2;
	}	
}
