package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Vector2D;

public class Anchor extends MovableObject implements Steppable {

	public Anchor(Room room) {
		super(room);
		setMovesRemaining(1);
	
	}

	@Override
	public String getName() {
		return "anchor";
	}

	@Override
	public int getLayer() {
		return 2;
	}

	@Override
	public boolean isHeavy() {
		return true;
	}

	@Override
	public boolean canMove(Vector2D dir) {
		return dir.getY() == 0;
	} 

	@Override
	public void step() {
		fall();
	}
	
}
