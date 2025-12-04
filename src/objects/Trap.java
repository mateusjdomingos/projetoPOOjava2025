package objects;


import pt.iscte.poo.game.Room;
import pt.iscte.poo.gui.ImageGUI;

public class Trap extends MovableObject implements Steppable {
	public Trap(Room room) {
		super(room);
	}

	@Override
	public String getName() {
		return "trap";
	}

	@Override
	public int getLayer() {
		return 1;
	}

	@Override
	public void step() {
		fall();
	}

	@Override
	public int getWeight() {
		return 2;
	}

	@Override
	public boolean interact(GameObject interator) {
		if(interator instanceof SmallFish) {
			return true;
		}

		if(interator instanceof BigFish) {
			getRoom().removeObject(interator);
			getRoom().setLevelFailed(true);
			ImageGUI.getInstance().setStatusMessage("O peixe grande morreu! Precionar R para reiniciar o jogo.");

			return true;
		}

		return false;
	}
}
