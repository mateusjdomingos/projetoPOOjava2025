package objects;

import pt.iscte.poo.utils.Vector2D;

public interface Movable {
	
	void move(Vector2D dir);
	
	boolean isHeavy();

	boolean canMove(Vector2D dir);
}
