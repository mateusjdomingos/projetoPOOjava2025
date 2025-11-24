package objects;

import java.util.List;

//import java.util.Random;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public abstract class GameCharacter extends GameObject implements Untransposable {
	
	public GameCharacter(Room room) {
		super(room);
	}
	

	
	public void move(Vector2D dir) {

		
		
		Point2D newPosition = getPosition().plus(dir);
		if (newPosition.getX() >= 0 && newPosition.getX() < 10 && 
			newPosition.getY() >= 0 && newPosition.getY() < 10) {
			
			List<GameObject> objectsAtDest = getRoom().getObjects(newPosition);
			
			for(GameObject obj : objectsAtDest) {
				if(obj instanceof Untransposable) return;
				
				if(obj instanceof Movable) {
					Movable movableObj = (Movable) obj;
					
					Point2D objectDest = newPosition.plus(dir);
					
					if(!canMoveTo(objectDest)) return;
					
					movableObj.move(dir);
				}
			}
			
			setPosition(newPosition);
		}
		
	}
	
	private boolean canMoveTo(Point2D position) {
		if (position.getX() < 0 || position.getX() >= 10 || 
			position.getY() < 0 || position.getY() >= 10) {
			return false;
		}
		
		List<GameObject> objectsAtPos = getRoom().getObjects(position);
		for (GameObject obj : objectsAtPos) {
			if (obj instanceof Untransposable || obj instanceof Movable) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int getLayer() {
		return 2;
	}
	
}