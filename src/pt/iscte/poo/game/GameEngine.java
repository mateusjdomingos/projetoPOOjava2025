package pt.iscte.poo.game;

import java.awt.event.KeyEvent;
import objects.GameCharacter;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import objects.SmallFish;
import objects.BigFish;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.observer.Observed;
import pt.iscte.poo.observer.Observer;
import pt.iscte.poo.utils.Direction;

public class GameEngine implements Observer {
	
	private Map<String,Room> rooms;
	private Room currentRoom;
	private int lastTickProcessed = 0;
	
	private GameCharacter selectedFish;
	
	public GameEngine() {
		rooms = new HashMap<String,Room>();
		loadGame();
		currentRoom = rooms.get("room0.txt");
		updateGUI();		
		SmallFish.getInstance().setRoom(currentRoom);
		BigFish.getInstance().setRoom(currentRoom);
		
		selectedFish = SmallFish.getInstance();
	}

	private void loadGame() {
		File[] files = new File("./rooms").listFiles();
		for(File f : files) {
			rooms.put(f.getName(),Room.readRoom(f,this));
		}
	}

	@Override
	public void update(Observed source) {

		if (ImageGUI.getInstance().wasKeyPressed()) {
			int k = ImageGUI.getInstance().keyPressed();
			
			
			if(k == KeyEvent.VK_SPACE) { //Este if verifica se a tecla é espaço
				if (selectedFish == SmallFish.getInstance()) { //se for o peixe pequeno passamos a movimentar o grande
					selectedFish = BigFish.getInstance();
					ImageGUI.getInstance().setStatusMessage("Peixe Grande Selecionado");
				} else {
					selectedFish = SmallFish.getInstance();
					ImageGUI.getInstance().setStatusMessage("Peixe Pequeno Selecionado");
				}
			}
			
			//Se a tecla premida for direção vai mover o peixe selecionado
			if (Direction.isDirection(k)) {
		        selectedFish.move(Direction.directionFor(k).asVector());
		    }
			
		}
		int t = ImageGUI.getInstance().getTicks();
		while (lastTickProcessed < t) {
			processTick();
		}
		ImageGUI.getInstance().update();
	}

	private void processTick() {		
		lastTickProcessed++;
	}

	public void updateGUI() {
		if(currentRoom!=null) {
			ImageGUI.getInstance().clearImages();
			ImageGUI.getInstance().addImages(currentRoom.getObjects());
		}
	}
	
}
