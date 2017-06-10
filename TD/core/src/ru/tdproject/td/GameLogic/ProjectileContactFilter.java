package ru.tdproject.td.GameLogic;

import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.ContactFilter;
import com.badlogic.gdx.physics.box2d.Fixture;

import ru.tdproject.td.BaseObject;
import ru.tdproject.td.Projectile;

public class ProjectileContactFilter implements ContactFilter {

	@Override
	public boolean shouldCollide(Fixture fixtureA, Fixture fixtureB) {
		if (fixtureA.getBody().getUserData() != null) {
			if (((BaseObject) fixtureA.getBody().getUserData()).getType() == "Projectile") {
				if (((Projectile) fixtureA.getBody().getUserData())
						.getTarget() == (BaseObject) fixtureB.getUserData())
					return true;
				else
					return false;
			}
			else return false;
		} else
			return false;
	}

}
