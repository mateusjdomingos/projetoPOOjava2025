package objects;

import pt.iscte.poo.game.Room;

public class Stone extends MovableObject {
	
	public Stone(Room room) {
		super(room);
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
