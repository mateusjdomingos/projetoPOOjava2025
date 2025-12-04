package objects;

import java.util.List;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Krab extends MovableObject implements Steppable, Slim, Enemy{

    public Krab(Room room) {
        super(room);
    }

    @Override
    public String getName() {
        return "krab";
    }

    @Override
    public int getLayer() {
        return 2;
    }

    @Override
    public int getWeight() {
        return 0;
    }

    @Override
    public boolean canBeMoved(GameCharacter mover, Vector2D dir) {
        return false;
    }

    @Override
    public void step() {
        fall();
    }

    @Override
    public boolean interact(GameObject interactor) {
        // Se for Peixe Pequeno -> Peixe morre
        if (interactor instanceof SmallFish) {
            getRoom().removeObject(interactor);
            getRoom().setLevelFailed(true);
            ImageGUI.getInstance().setStatusMessage("O Caranguejo matou o Peixe Pequeno! (R para reiniciar)");
            return true;
        }
        
        // Se for Peixe Grande -> Caranguejo morre
        if (interactor instanceof BigFish) {
            getRoom().removeObject(this);
            ImageGUI.getInstance().setStatusMessage("O Peixe Grande esmagou o Caranguejo!");
            return true;
        }

        // Se for Armadilha (caindo em cima) -> Caranguejo morre
        if (interactor instanceof Trap) {
            getRoom().removeObject(this);
            return true;
        }

        return false;
    }

    public void moveRandomly() {
        Vector2D moveDir = Math.random() < 0.5 ? Direction.LEFT.asVector() : Direction.RIGHT.asVector();
        Point2D newPos = getPosition().plus(moveDir);

        if (!isValidMove(newPos)) return;

        List<GameObject> objectsAtDest = getRoom().getObjects(newPos);
        for (GameObject obj : objectsAtDest) {
            
            //Se for Peixe Pequeno, o Peixe morre e o Caranguejo move-se para lá
            if (obj instanceof SmallFish) {
                getRoom().removeObject(obj);
                getRoom().setLevelFailed(true);
                ImageGUI.getInstance().setStatusMessage("O Caranguejo apanhou o Peixe Pequeno! (R para reiniciar)");
                setPosition(newPos); 
                return;
            }

            //Se for Peixe Grande ou Armadilha, o Caranguejo morre
            if (obj instanceof BigFish || obj instanceof Trap) { 
                getRoom().removeObject(this);
                ImageGUI.getInstance().setStatusMessage("O Caranguejo foi contra um perigo e morreu!");
                return;
            }
        }

        setPosition(newPos);
    }

    private boolean isValidMove(Point2D pos) {
        if (pos.getX() < 0 || pos.getX() >= 10 || pos.getY() < 0 || pos.getY() >= 10) return false;

        List<GameObject> objects = getRoom().getObjects(pos);
        for (GameObject obj : objects) {
            if (obj instanceof Holed) return false;
            
            if (obj instanceof MovableObject || obj instanceof Untransposable) return false; 
        }
        return true;
    }
    
}
