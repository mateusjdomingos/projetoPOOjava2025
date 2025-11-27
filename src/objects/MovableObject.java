package objects;

import java.util.List;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class MovableObject extends GameObject implements Movable {

private int movesRemaining = -1; // -1 indica movimentos infinitos

    public MovableObject(Room room) {
        super(room);
    }

    @Override
    public void move(Vector2D dir) {
        Point2D newPos = getPosition().plus(dir);
        setPosition(newPos);

        // Se não for infinito, desconta um movimento
        if(movesRemaining > 0) {
            movesRemaining--;
        }
    }

    @Override
    public boolean canMove(Vector2D dir) {
        if(movesRemaining == 0) {
            return false;
        }
        
        return true; 
    }

    public void setMovesRemaining(int moves) {
        this.movesRemaining = moves;
    }

    @Override
    public boolean isHeavy() {
        return false; // Implementação padrão, pode ser sobrescrito
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
            move(Direction.DOWN.asVector());
        }
    }

    public boolean isSupported() {
        Point2D positionBelow = getPosition().plus(Direction.DOWN.asVector());

        if(positionBelow.getY() >= 10) return true; // Chão do aquário

        List<GameObject> objectsBelow = getRoom().getObjects(positionBelow);

        for(GameObject obj : objectsBelow) {
            if(obj instanceof Untransposable || 
               obj instanceof Movable || 
               obj instanceof GameCharacter) {
                return true;
            }
        }

        return false; 
    }
}
