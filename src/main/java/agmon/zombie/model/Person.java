package agmon.zombie.model;

import java.util.Random;

import org.newdawn.slick.geom.Circle;

public class Person extends AbstractEntity {

	public enum State {
		WALKING, FLEEING, ATTACKING
	}

	private State state;

	private int experience;

	private int changeDirectionTimer;

	private int respondedToCollisionTimer;

	private final EntityAdder entities;

	private final Dice dice;

	private AbstractEntity other;

	public Person(EntityAdder entities, float x, float y) {
		super(new Circle(x, y, 3), 30);
		this.entities = entities;
		state = State.WALKING;
		direction = Direction.values()[new Random().nextInt(Direction.values().length)];
		dice = new Dice(rand);
	}

	@Override
	public void update(int delta) {
		changeDirectionTimer -= delta;
		respondedToCollisionTimer -= delta;
		if (changeDirectionTimer <= 0) {
			changeDirectionTimer = 4500 + rand.nextInt(1000);
			direction = Direction.values()[new Random().nextInt(Direction.values().length)];

		}
		updateWalkingState(delta);
		switch (state) {
		case FLEEING:
			setDirectionFrom(other.getX(), other.getY());
			move(3);
			break;
		case WALKING:
			move(2);
			break;
		case ATTACKING:
			setDirectionTo(other.getX(), other.getY());
			attack();
			break;

		}
	}

	private void updateWalkingState(int delta) {
		if (null == other) {
			state = State.WALKING;
			return;
		}
		if (!isCollide(other)) {
			state = State.WALKING;
			other = null;
			return;
		}
	}

	@Override
	public void collide(AbstractEntity other) {
		if (respondedToCollisionTimer > 0) {
			return;
		}
		respondedToCollisionTimer = 10;
		float experienceAffect = calculateExperienceAffect();
		if (other instanceof Zombie) {
			this.other = other;
			switch (dice.throwDice(0.6f - experienceAffect)) {
			case 0:
				state = State.FLEEING;
				break;
			case 1:
				state = State.ATTACKING;
				break;
			default:
				throw new IllegalStateException("Unkown percentage");

			}
		} else if (other instanceof Turning) {
			if (this.getShape().intersects(other.getShape())) {
				other.setExist(false);
			}
		}

	}

	private float calculateExperienceAffect() {
		float experienceAffect = 0.05f * experience;
		if (experienceAffect > 0.35f) {
			experienceAffect = 0.35f;
		}
		return experienceAffect;
	}

	private void attack() {
		if (!shape.intersects(other.getShape())) {
			return;
		}
		if (dice.throwDice(0.66f) == 0) {
			Noise noise = new Noise(getX(), getY());
			entities.add(noise);
		}
		switch (dice.throwDice(0.6f - calculateExperienceAffect())) {
		case 0:
			lose();
			break;
		case 1:
			win();
			break;
		}
	}

	private void win() {
		other.setExist(false);
		other = null;
		experience++;
	}

	private void lose() {
		// Becoming a zombie
		setExist(false);
		Turning turning = new Turning(entities, getX(), getY());
		entities.add(turning);
	}

	public int getExperience() {
		return experience;
	}

}
