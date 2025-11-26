package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class MovableObject extends GameObject implements Movable {

private int movesRemaining = -1; // -1 indicates unlimited moves

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
        return false; // Default implementation, can be overridden
    }

    @Override
    public String getName() {
        return ""; // Default implementation, should be overridden
    }

    @Override
    public int getLayer() {
        return 2; // Default layer
    }

}
