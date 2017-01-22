package agmon.zombie.model;

import org.newdawn.slick.geom.Shape;

public abstract class AbstractEntity {

	protected final Shape shape;
	
	protected Direction direction;

	public AbstractEntity(Shape shape) {
		this.shape = shape;
	}

	public int getX() {
		return (int) shape.getCenterX();
	}

	public int getY() {
		return (int) shape.getCenterY();
	}

	protected void moveX(int deltaX) {
		shape.setCenterX(shape.getCenterX() + deltaX);
	}

	protected void moveY(int deltaY) {
		shape.setCenterY(shape.getCenterY() + deltaY);
	}
	
	protected void move(int delta) {
		switch (direction) {
		case NORTH:
			moveY(-delta);
			break;
		case NORTH_EAST:
			moveY(-delta);
			moveX(delta);
			break;
		case EAST:
			moveX(delta);
			break;
		case SOUTH_EAST:
			moveY(delta);
			moveX(delta);
			break;
		case SOUTH:
			moveY(delta);
			break;
		case SOUTH_WEST:
			moveY(delta);
			moveX(-delta);
			break;
		case WEST:
			moveX(-delta);
			break;
		case NORTH_WEST:
			moveY(-delta);
			moveX(-delta);
			break;
		default:
			break;
		}

	}

	public Shape getShape() {
		return shape;
	}

	public abstract void update(int delta);

}
