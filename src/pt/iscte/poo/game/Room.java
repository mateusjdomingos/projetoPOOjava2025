package pt.iscte.poo.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import objects.*;
import pt.iscte.poo.utils.Point2D;

public class Room {
	
	private List<GameObject> objects;
	private String roomName;
	private GameEngine engine;
	private Point2D smallFishStartingPosition;
	private Point2D bigFishStartingPosition;
	
	public Room() {
		objects = new ArrayList<GameObject>();
	}

	private void setName(String name) {
		roomName = name;
	}
	
	public String getName() {
		return roomName;
	}
	
	private void setEngine(GameEngine engine) {
		this.engine = engine;
	}

	public void addObject(GameObject obj) {
		objects.add(obj);
		engine.updateGUI();
	}
	
	public void removeObject(GameObject obj) {
		objects.remove(obj);
		engine.updateGUI();
	}
	
	public List<GameObject> getObjects() {
		return objects;
	}

	public void setSmallFishStartingPosition(Point2D heroStartingPosition) {
		this.smallFishStartingPosition = heroStartingPosition;
	}
	
	public Point2D getSmallFishStartingPosition() {
		return smallFishStartingPosition;
	}
	
	public void setBigFishStartingPosition(Point2D heroStartingPosition) {
		this.bigFishStartingPosition = heroStartingPosition;
	}
	
	public Point2D getBigFishStartingPosition() {
		return bigFishStartingPosition;
	}
	
	public List<GameObject> getObjects(Point2D pos) { //Função criada que retorna todos os objetos que estiverem numa só posição
		List<GameObject> foundObjects = new ArrayList<>();
		
		for(GameObject obj : objects) {
			if(obj.getPosition().equals(pos)) {
				foundObjects.add(obj); 
			}
		}
		return foundObjects;
	}
	
	public static Room readRoom(File f, GameEngine engine) {
		Room r = new Room();
		r.setEngine(engine);
		r.setName(f.getName());
		
		try(Scanner scanner = new Scanner(f)) {
			
			for (int y = 0; scanner.hasNextLine(); y++) {
			    
			    String line = scanner.nextLine();
			    for (int x = 0; x < line.length(); x++) {
			        
			        Point2D pos = new Point2D(x, y);
			        
			        // 1. Criar o fundo (Água)
			        Water water = new Water(r);
			        water.setPosition(pos);
			        r.addObject(water);
			        
			        // 2. Criar o objeto específico
			        char c = line.charAt(x);
			        
			        if(c != ' ') {
			        	GameObject obj = ImageTileFactory.create(c, r, pos);
			        	
			        	if(obj != null) {
			        		r.addObject(obj);
			        	}
			        }
			    }
			}
		} catch (FileNotFoundException e) {
			System.err.println("Erro: Ficheiro da sala não encontrado!");
			e.printStackTrace();
		}
		
		return r;
		
	}
}