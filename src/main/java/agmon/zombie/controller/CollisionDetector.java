package agmon.zombie.controller;

import java.util.List;

import agmon.zombie.Common;
import agmon.zombie.model.Direction;
import agmon.zombie.model.Person;

public class CollisionDetector {

	public void detect(List<Person> people) {
		for (Person person : people) {
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

		for (Person person0 : people) {
			for (Person person1 : people) {
				if (person0 == person1) {
					continue;
				}
				if (person0.isCollide(person1)) {
					person1.personCollide(person1);
				}
			}
		}
	}

}
