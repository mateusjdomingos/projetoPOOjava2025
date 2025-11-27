package objects;

import pt.iscte.poo.game.Room;

public class Cup extends MovableObject implements Steppable {

	public Cup(Room room) {
		super(room);
	}

	@Override
	public void step() {
		fall();
	}

	@Override
	public String getName() {
		return "cup";
	}

	@Override
	public int getLayer() {
		return 2;
	}

	@Override
	public int getWeight() {
		return 1;
	}

}
