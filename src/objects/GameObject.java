package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public abstract class GameObject implements ImageTile, Interactable{
	
	private Point2D position;
	private Room room;
	
	public GameObject(Room room) {
		this.room = room;
	}
	
	public GameObject(Point2D position, Room room) {
		this.position = position;
		this.room = room;
	}

	public void setPosition(int i, int j) {
		position = new Point2D(i, j);
	}
	
	public void setPosition(Point2D position) {
		this.position = position;
	}

	@Override
	public Point2D getPosition() {
		return position;
	}
	
	public Room getRoom() {
		return room;
	}
	
	public void setRoom(Room room) {
		this.room = room;
	}

	public int getStrength() {
		return 0; //Por defeito os objetos não têm força
	}

	@Override
	public boolean interact(GameObject interactor) { // Os objetos untransponíveis não podem ser interagidos
		return !(this instanceof Untransposable);
	}

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
