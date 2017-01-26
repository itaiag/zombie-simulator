package agmon.zombie.view;

import java.awt.Font;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Circle;

import agmon.zombie.model.AbstractEntity;
import agmon.zombie.model.Noise;
import agmon.zombie.model.Person;
import agmon.zombie.model.Turning;
import agmon.zombie.model.Zombie;

public class Renderer {

	private final Graphics g;

	private TrueTypeFont font;

	public Renderer(GameContainer container) {
		this.g = container.getGraphics();
		Font awtFont = new Font("Times New Roman", Font.BOLD, 8);
		font = new TrueTypeFont(awtFont, false);

	}

	public void renderEntities(List<AbstractEntity> people) {

		for (AbstractEntity entity : people) {
			if (entity instanceof Person) {
				renderPerson((Person) entity);
			} else if (entity instanceof Noise) {
				renderNoise((Noise) entity);
			} else if (entity instanceof Zombie) {
				renderZombie((Zombie) entity);
			} else if (entity instanceof Turning) {
				renderTurning((Turning)entity);
			}
		}
	}

	private void renderTurning(Turning turning) {
		g.setColor(Color.blue);
		g.fill(turning.getShape());
	}

	private void renderZombie(Zombie entity) {
		g.setColor(Color.red);
		g.fill(entity.getShape());
	}

	private void renderNoise(Noise noise) {
		Circle circle = (Circle) noise.getShape();
		g.setColor(new Color(95 - noise.getRadius(), 0, 255 - noise.getRadius() * 5));
		g.draw(circle);
	}

	private void renderPerson(Person person) {
		g.setColor(Color.white);
		g.setFont(font);
		g.drawString(person.getExperience() + "", (float) person.getX() - 3,
				(float) (person.getShape().getCenterY() - 14));
		g.setColor(new Color(0, 150 + person.getExperience() * 25, 0));
		g.fill(person.getShape());
	}

}
