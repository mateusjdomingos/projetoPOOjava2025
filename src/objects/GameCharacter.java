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

	public boolean canPush(MovableObject obj, Vector2D dir) {

		if(!obj.canBeMoved(this, dir)) return false;

		Point2D objectDest = obj.getPosition().plus(dir);
		return canMoveTo(objectDest);
	}
	

	
	public void move(Vector2D dir) {
      
	// Não faz sentido o move ser usado para objetos dentro do GameCharacter
	// por isso terá de ser alterado no futuro	
		
		Point2D newPosition = getPosition().plus(dir);
		if (newPosition.getX() >= 0 && newPosition.getX() < 10 && 
			newPosition.getY() >= 0 && newPosition.getY() < 10) {
			
			List<GameObject> objectsAtDest = getRoom().getObjects(newPosition);
			
			for(GameObject obj : objectsAtDest) {
				if(obj instanceof MovableObject movableObj) {
					
					if(moveStack(movableObj, dir)) {
						setPosition(newPosition);
					}
					return;
				}

				if(!obj.interact(this)) return;
			}

			
			
			setPosition(newPosition);
		}
		
	}
	
	private boolean canMoveTo(Point2D position) { //Deverá ser abstrato no futuro
		if (position.getX() < 0 || position.getX() >= 10 || 
			position.getY() < 0 || position.getY() >= 10) {
			return false;
		}
		
		List<GameObject> objectsAtPos = getRoom().getObjects(position);
		for (GameObject obj : objectsAtPos) {
			if(obj instanceof MovableObject) continue;
			
			if(!obj.interact(this)) return false;
		}
		return true;
	}

	private boolean moveStack(MovableObject obj, Vector2D dir) {
		if(!obj.canBeMoved(this, dir)) return false;

		Point2D nextPos = obj.getPosition().plus(dir);

		if(nextPos.getX() < 0 || nextPos.getX() >= 10 || 
		   nextPos.getY() < 0 || nextPos.getY() >= 10) return false;
		
		List<GameObject> objectsAtNextPos = getRoom().getObjects(nextPos);

		for(GameObject nextObj : objectsAtNextPos) { 
//			if (nextObj instanceof Holed) { //Objetos furados podem ser ignorados
//				if(obj instanceof Slim) { //Objetos magros podem passar por objetos furados
//					continue;
//				} else {
//					return false;
//				}
//			}
//			
//			if(nextObj instanceof Untransposable) return false;
//
//			if(nextObj instanceof MovableObject) { //Move em cadeia
//				if(!moveStack((MovableObject)nextObj, dir)) return false;
//			}
//		}
//		obj.move(dir);
//		return true;
		    // 1. Movimento em Cadeia (Recursividade)
            if(nextObj instanceof MovableObject) { 
                if(!moveStack((MovableObject)nextObj, dir)) return false;
                continue; // Já tratámos deste objeto, passar ao próximo
            }

            // 2. Verificação Genérica (Paredes normais E Paredes com buraco)
            // Aqui usamos o facto de o teu interact aceitar "GameObject" e não apenas Character!
            // Perguntamos: "O obstáculo (nextObj) deixa passar o objeto que estou a empurrar (obj)?"
            if (!nextObj.interact(obj)) {
                return false; // Bloqueia (seja parede normal ou parede c/ buraco vs objeto gordo)
            }
        }

        obj.move(dir);
        return true;

	}

	@Override
	public int getLayer() {
		return 2;
	}
	
}