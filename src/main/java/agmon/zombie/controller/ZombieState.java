package agmon.zombie.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import agmon.zombie.Common;
import agmon.zombie.model.Person;
import agmon.zombie.view.Renderer;

public class ZombieState extends BasicGame {

	private List<Person> people;
	
	private Renderer renderer;
	
	private CollisionDetector colDetector;

	public ZombieState(String title) {
		super(title);
	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		renderer.renderPeople(people);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		Random rand = new Random();
		people = new ArrayList<Person>();
		for (int i = 0; i < 300; i++) {
			Person person = new Person(rand.nextInt(Common.WIDTH - 50), rand.nextInt(Common.HEIGHT - 50));
			people.add(person);
		}
		renderer = new Renderer(container);
		colDetector = new CollisionDetector();

	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		
		for (Person person : people){
			person.update(delta);
		}
		colDetector.detect(people);

	}

}
