package objects;

import java.util.List;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Bomb extends MovableObject implements Steppable {

	private boolean wasFalling = false;

	public Bomb(Room room) {
		super(room);
	}

	@Override
	public void step() {
		if(!isSupported()) {
			fall();
			wasFalling = true;
		} else if(wasFalling) {
			explode();
			wasFalling = false;
		}
	}

	public void explode() {
		getRoom().removeObject(this);

		List<Point2D> explosionArea = List.of(
			getPosition(),
			getPosition().plus(Direction.UP.asVector()),
			getPosition().plus(Direction.DOWN.asVector()),
			getPosition().plus(Direction.LEFT.asVector()),
			getPosition().plus(Direction.RIGHT.asVector())
		);

		for(Point2D pos : explosionArea) {
			if (pos.getX() < 0 || pos.getX() >= 10 || pos.getY() < 0 || pos.getY() >= 10) continue;

			List<GameObject> targets = getRoom().getObjects(pos);

			for(GameObject obj : targets) {
				if(obj == this || obj instanceof GameCharacter) continue;
				
				if(obj.getLayer() == 0 ) continue;

				getRoom().removeObject(obj);

				if(obj instanceof Transmotable) {
					GameObject newObj = ((Transmotable) obj).getTransformedInstance();
					if(newObj != null) {
						getRoom().addObject(newObj);	
					}
				}
			}
		}
	}

	@Override
	public String getName() {
		return "bomb";
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
