package objects;

import java.util.List;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public abstract class MovableObject extends GameObject {

private int movesRemaining = -1; // -1 indica movimentos infinitos

    public MovableObject(Room room) {
        super(room);
    }

    public abstract int getWeight();

    public boolean canBeMoved(GameCharacter mover, Vector2D dir) {
        if(movesRemaining == 0) return false;

        if(this.isHeavy() && mover.getStrength() < 5) return false;
        
        return true;
    }

    public void move(Vector2D dir) {
        Point2D newPos = getPosition().plus(dir);
        setPosition(newPos);

        // Se não for infinito, desconta um movimento
        if(movesRemaining > 0) {
            movesRemaining--;
        }
    }

    private void gravityMove(Vector2D dir) {
        Point2D newPos = getPosition().plus(dir);
        setPosition(newPos);
    }

    public void setMovesRemaining(int moves) {
        this.movesRemaining = moves;
    }

    public boolean isHeavy() {
        return getWeight() > 1; // Implementação padrão, pode ser sobrescrito
    }

    @Override
    public String getName() {
        return ""; // Implemnentação padrão, deve ser sobrescrito
    }

    @Override
    public int getLayer() {
        return 2; // Default layer
    }

    public void fall() {
        if(!isSupported()) {
            gravityMove(Direction.DOWN.asVector());
        }
    }

    public void floatUp() {
        if(!isBlockedAbove()) {
            gravityMove(Direction.UP.asVector());
        }
    }

    public boolean isSupported() {
        Point2D positionBelow = getPosition().plus(Direction.DOWN.asVector());

        if(positionBelow.getY() >= 10) return true; // Chão do aquário

        List<GameObject> objectsBelow = getRoom().getObjects(positionBelow);

        for(GameObject obj : objectsBelow) {
            if(obj instanceof MovableObject || 
               obj instanceof GameCharacter) {
                return true;
            }

            if(!obj.interact(this)) {
                return true;
            }
        }

        return false; 
    }

    public boolean isBlockedAbove() {
        Point2D positionAbove = getPosition().plus(Direction.UP.asVector());

        if(positionAbove.getY() < 0) return true;

        List<GameObject> objectsAbove = getRoom().getObjects(positionAbove);

        for(GameObject obj : objectsAbove) {
            if(obj instanceof MovableObject || 
               obj instanceof GameCharacter) {
                return true;
            }

            if(!obj.interact(this)) {
                return true;
            }
        }

        return false; 
    }

    @Override
    public boolean interact(GameObject interator) {
        return false; // Implementação padrão, pode ser sobrescrito
    }
}
