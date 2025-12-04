package objects;

import java.util.List;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;

public class Buoy extends MovableObject implements Steppable, Flotable {

    public Buoy(Room room) {
        super(room);
    }

    @Override
    public String getName() {
        return "buoy";
    }

    @Override
    public int getLayer() {
        return 2;
    }

    @Override
    public int getWeight() {
        return 1;
    }

    @Override
    public void step() {
        GameObject objAbove = getObjectAbove();
        if(objAbove != null) {
            if(objAbove instanceof GameCharacter) return;
            
            fall();
        } else {
            floatUp();
        }
    }

    private GameObject getObjectAbove() { //Desvolve o objeto que está em cima, no futuro tem de ir para a classe Abstrata
        Point2D posAbove = getPosition().plus(Direction.UP.asVector());

        if(posAbove.getY() < 0) return null;

        List<GameObject> objectsAbove = getRoom().getObjects(posAbove);

        for(GameObject obj : objectsAbove) {
            if(obj instanceof MovableObject || obj instanceof GameCharacter) {
                    return obj;
            }
        }
        return null;
    }

    @Override
    public boolean hasWeightOnTop() {
        return getObjectAbove() != null;
    }

    @Override
	public boolean canBeMoved(GameCharacter mover, Vector2D dir) {
		if (!super.canBeMoved(mover, dir)) return false;
		
		if (dir.equals(Direction.DOWN.asVector())) {
			if (mover instanceof SmallFish) {
				return false; 
			}
		}
		return true;
	}



    
    
}
