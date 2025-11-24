package pt.iscte.poo.game;

import pt.iscte.poo.utils.Point2D;
import objects.*;

public class ImageTileFactory {
	
	public static GameObject create(char type, Room room, Point2D pos) {
		GameObject obj = null;
		
		switch(type) {
		case 'W':
			obj = new Wall(room);
			break;
		case 'H':
			obj = new SteelHorizontal(room);
			break;
		case 'B':
			obj = BigFish.getInstance();
			obj.setRoom(room);
			room.setBigFishStartingPosition(pos);
			break;
		case 'S':
			obj = SmallFish.getInstance();
			obj.setRoom(room);
			room.setSmallFishStartingPosition(pos);
			break;
		case 'V':
			obj = new SteelVertical(room);
			obj.setRoom(room);
			break;
		case 'C':
			obj = new Cup(room);
			obj.setRoom(room);
			break;
		case 'R':
			obj = new Stone(room);
			obj.setRoom(room);
			break;
		case 'Y':
			obj = new Trunk(room);
			obj.setRoom(room);
			break;
		case 'X':
			obj = new HoledWall(room);
			obj.setRoom(room);
			break;
		case 'A':
			obj = new Anchor(room);
			obj.setRoom(room);
			break;
		case 'b':
			obj = new Bomb(room);
			obj.setRoom(room);
			break;
		case 'T':
			obj = new Trap(room);
			obj.setRoom(room);
			break;
		}
		
		if(obj != null) {
			obj.setPosition(pos);
		}
		
		return obj;
		
	}
}
