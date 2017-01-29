package agmon.zombie.view;

import java.awt.Font;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Circle;

import agmon.zombie.Common;
import agmon.zombie.model.AbstractEntity;
import agmon.zombie.model.Noise;
import agmon.zombie.model.Person;
import agmon.zombie.model.Turning;
import agmon.zombie.model.Zombie;

public class Renderer {

	private final static String STATUS_LINE_TEMPLATE = "People: %d, Zombies: %d, Time: %d, Maximum Experience: %d";

	private final Graphics g;

	private final long gameStartTime;

	private final TrueTypeFont experienceFont;

	private final TrueTypeFont stateFont;

	public Renderer(GameContainer container) {
		this.g = container.getGraphics();

		Font awtFont = new Font("Times New Roman", Font.PLAIN, 8);
		experienceFont = new TrueTypeFont(awtFont, false);

		awtFont = new Font("Times New Roman", Font.BOLD, 12);
		stateFont = new TrueTypeFont(awtFont, false);

		gameStartTime = System.currentTimeMillis();
	}

	public void renderEntities(List<AbstractEntity> people) {

		int numOfPeople = 0;
		int numOfZombies = 0;
		int maxExperience = -1;

		for (AbstractEntity entity : people) {
			if (entity instanceof Person) {
				numOfPeople++;
				int experience = ((Person) entity).getExperience();
				if (experience > maxExperience) {
					maxExperience = experience;
				}
				renderPerson((Person) entity);
			} else if (entity instanceof Noise) {
				renderNoise((Noise) entity);
			} else if (entity instanceof Zombie) {
				numOfZombies++;
				renderZombie((Zombie) entity);
			} else if (entity instanceof Turning) {
				numOfZombies++;
				renderTurning((Turning) entity);
			}
		}
		renderGameState(numOfPeople, numOfZombies, maxExperience);
	}

	private void renderGameState(int numOfPeople, int numOfZombies, int maxExperience) {
		if (maxExperience < 0) {
			maxExperience = 0;
		}
		g.setColor(Color.white);
		g.setFont(stateFont);
		g.drawLine(0, Common.HEIGHT - Common.STATE_AREA_HEIGHT, Common.WIDTH, Common.HEIGHT - Common.STATE_AREA_HEIGHT);
		g.drawString(String.format(STATUS_LINE_TEMPLATE, numOfPeople, numOfZombies,
				(System.currentTimeMillis() - gameStartTime) / 1000, maxExperience), 10, Common.HEIGHT - 20);
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
		g.setFont(experienceFont);
		g.drawString(person.getExperience() + "", (float) person.getX() - 3,
				(float) (person.getShape().getCenterY() - 14));
		g.setColor(new Color(0, 150 + person.getExperience() * 25, 0));
		g.fill(person.getShape());
	}

}
