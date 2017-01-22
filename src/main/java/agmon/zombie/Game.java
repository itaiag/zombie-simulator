package agmon.zombie;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import agmon.zombie.controller.ZombieState;

public class Game  {

	

	private static final String GAME_NAME = "Zombie Simulator";

	public static void main(String[] args) {
		AppGameContainer appgc;
		try {
			appgc = new AppGameContainer(new ZombieState(GAME_NAME));
			appgc.setDisplayMode(Common.WIDTH, Common.HEIGHT, false);
			appgc.setShowFPS(true);
			int interval = 25;
			appgc.setMaximumLogicUpdateInterval(interval);
			appgc.setMinimumLogicUpdateInterval(interval);
			appgc.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
