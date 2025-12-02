package objects;

import pt.iscte.poo.game.Room;

public class HoledWall extends GameObject implements Holed {

	public HoledWall(Room room) {
		super(room);
	}

	@Override
	public boolean interact(GameObject interator) {
		if(interator instanceof Slim) {
			return true;
		}
		return false;
	}

	@Override
	public String getName() {
		return "holedWall";
	}

	@Override
	public int getLayer() {
		return 1;
	}

}
