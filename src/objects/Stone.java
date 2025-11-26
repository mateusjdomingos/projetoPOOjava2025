package objects;

import pt.iscte.poo.game.Room;

public class Stone extends MovableObject implements Steppable {
	
	public Stone(Room room) {
		super(room);
	}

	public void step() {
		fall();
	}

	@Override
	public String getName() {
		return "stone";
	}

	@Override
	public int getLayer() {
		return 2;
	}

	@Override
	public boolean isHeavy() {
		return true;
	}
}
