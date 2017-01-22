package agmon.zombie.controller;

import java.util.Random;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import agmon.zombie.Common;
import agmon.zombie.model.EntityStore;
import agmon.zombie.model.Person;
import agmon.zombie.model.Zombie;
import agmon.zombie.view.Renderer;

public class ZombieState extends BasicGame {

	private EntityStore store;
	
	private Renderer renderer;
	
	private CollisionDetector colDetector;
	
	private int numberOfPeople = 600;
	
	private int numberOfZombies = 3;

	public ZombieState(String title) {
		super(title);
	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		renderer.renderEntities(store.getEntities());
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		store = new EntityStore();
		Random rand = new Random();
		for (int i = 0; i < numberOfPeople; i++) {
			Person person = new Person(store,rand.nextInt(Common.WIDTH - 50), rand.nextInt(Common.HEIGHT - 50));
			store.add(person);
		}
		for (int i = 0; i < numberOfZombies; i++) {
			Zombie zombie = new Zombie(rand.nextInt(Common.WIDTH - 50), rand.nextInt(Common.HEIGHT - 50));
			store.add(zombie);
		}
		
		renderer = new Renderer(container);
		colDetector = new CollisionDetector();

	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		store.update(delta);
		colDetector.detect(store.getEntities());
		store.removeDeads();

	}

}
