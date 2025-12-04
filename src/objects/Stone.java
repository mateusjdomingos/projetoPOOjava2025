package objects;

import java.util.List;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Stone extends MovableObject implements Steppable {
	
	private boolean krabSpawned = false;

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

	@Override
	public int getWeight() {
		return 2;
	}

	@Override
    public void move(Vector2D dir) {
        super.move(dir);

        if (!krabSpawned && dir.getY() == 0) {
            spawnKrab();
            krabSpawned = true; 
        }
    }

	private void spawnKrab() {
        Point2D posAbove = getPosition().plus(Direction.UP.asVector());
        
        if (posAbove.getY() < 0) return;
        List<GameObject> objects = getRoom().getObjects(posAbove);
        
        for(GameObject obj : objects) {
            if ( !(obj instanceof Water) ) {
                return; 
            }
        }
		Krab k = new Krab(getRoom());
        k.setPosition(posAbove);
        getRoom().addObject(k);
	}
}