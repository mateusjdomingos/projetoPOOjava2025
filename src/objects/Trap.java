package objects;

import pt.iscte.poo.game.Room;

public class Trap extends GameObject implements Untransposable {
	//A armadilha deverá ser moavble no futuro mas apenas por objetos e não por personagens
	public Trap(Room room) {
		super(room);
	}

	@Override
	public String getName() {
		return "trap";
	}

	@Override
	public int getLayer() {
		return 2;
	}	
}
