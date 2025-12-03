package objects;

import pt.iscte.poo.game.Room;

public class Wall extends GameObject implements Untransposable, Transmotable{

	public Wall(Room room) {
		super(room);
	}

	@Override
	public GameObject getTransformedInstance() {
		HoledWall holedWall = new HoledWall(getRoom());
		holedWall.setPosition(getPosition());
		return holedWall;
	}

	@Override
	public String getName() {
		return "wall";
	}	

	@Override
	public int getLayer() {
		return 1;
	}

}
