package agmon.zombie.view;

import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

import agmon.zombie.model.AbstractEntity;
import agmon.zombie.model.Noise;
import agmon.zombie.model.Person;
import agmon.zombie.model.Zombie;

public class Renderer {

	private final Graphics g;

	public Renderer(GameContainer container) {
		this.g = container.getGraphics();

	}

	public void renderEntities(List<AbstractEntity> people) {

		for (AbstractEntity entity : people) {
			if (entity instanceof Person) {
				renderPerson((Person) entity);
			} else if (entity instanceof Noise) {
				renderNoise((Noise) entity);
			} else if (entity instanceof Zombie) {
				renderZombie((Zombie) entity);
			}
		}
	}

	private void renderZombie(Zombie entity) {
		g.setColor(Color.red);
		g.fill(entity.getShape());
	}

	private void renderNoise(Noise noise) {
		Circle circle = (Circle) noise.getShape();
		g.setColor(new Color(100, 0, 255 - noise.getRadius() * 5));
		g.draw(circle);
	}

	private void renderPerson(Person person) {
		g.setColor(Color.green);
		g.fill(person.getShape());
	}

}
