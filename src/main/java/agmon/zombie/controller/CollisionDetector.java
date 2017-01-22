package agmon.zombie.controller;

import java.util.List;

import agmon.zombie.Common;
import agmon.zombie.model.AbstractEntity;
import agmon.zombie.model.Direction;
import agmon.zombie.model.Noise;

public class CollisionDetector {

	public void detect(List<AbstractEntity> entities) {
		for (AbstractEntity person : entities) {
			if (person.getX() <= 0) {
				person.wallCollision(Direction.WEST);
			} else if (person.getX() >= Common.WIDTH) {
				person.wallCollision(Direction.EAST);
			} else if (person.getY() <= 0) {
				person.wallCollision(Direction.NORTH);
			} else if (person.getY() >= Common.HEIGHT) {
				person.wallCollision(Direction.SOUTH);
			}
		}

		for (AbstractEntity entity0 : entities) {
			for (AbstractEntity entity1 : entities) {
				if (entity0 == entity1) {
					continue;
				}
				if (entity0 instanceof Noise){
					continue;
				}
				if (entity0.isCollide(entity1)) {
					entity0.collide(entity1);
				}
			}
		}
	}

}
