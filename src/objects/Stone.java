package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Stone extends GameObject implements Movable {
	
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
	public void move(Vector2D dir) {
		Point2D newPos = getPosition().plus(dir);
		setPosition(newPos);
	}

	@Override
	public boolean isHeavy() {
		return true;
	}
}
