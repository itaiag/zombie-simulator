package agmon.zombie.view;

import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

import agmon.zombie.model.Person;

public class Renderer {

	private final Graphics g;

	public Renderer(GameContainer container) {
		this.g = container.getGraphics();

	}

	public void renderPeople(List<Person> people) {
		
		for (Person person : people) {
			Circle circle = (Circle) person.getShape();
			if (person.getState() == Person.State.SHOOTING) {
				g.setColor(Color.red);
				g.fill(circle);
				g.setColor(new Color(10,0,person.getShootingTimer() -100));
				Circle newCirc = new Circle(circle.getCenterX(), circle.getCenterY(), (Person.TIME_SHOOTING - person.getShootingTimer()) / 10);
				g.draw(newCirc);
			} else {
				g.setColor(Color.green);
				g.fill(circle);
				
			}
		}

	}

}
