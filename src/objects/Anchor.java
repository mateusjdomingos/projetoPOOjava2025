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
	public boolean canBeMoved(GameCharacter mover, Vector2D dir) {
		boolean parentSaysYes = super.canBeMoved(mover, dir);
		if(!parentSaysYes) return false;

		if(dir.getY() != 0) return false;

		return true;
	} 

	@Override
	public void step() {
		fall();
	}

	@Override
	public int getWeight() {
		return 2;
	}
	
}
