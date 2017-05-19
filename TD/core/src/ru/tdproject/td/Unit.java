package ru.tdproject.td;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Unit extends BaseObject {

	public Unit(Circle form, float speed, Texture img, World world) {
		super("Unit", form, speed, new Vector2(1f, 0f), img, world);
		this.setLife(2);
	}

	// @Override
	// public String gettype() {
	// // TODO Auto-generated method stub
	// return type;
	// }

	// Check map collision stub
	private boolean checkMapCollision() {
		return false;
	};

	@Override
	public void step(Rectangle Border) {
		float speed = getSpeed();
		Vector2 arrow = getArrow();
		Vector2 pos = new Vector2(getForm().x, getForm().y);
		// arrow.x = MathUtils.cos(t);
		// arrow.y = MathUtils.sin(t);
		pos.mulAdd(arrow, speed);
		getForm().setPosition(pos);
		setArrow(arrow);
		if (!Border.contains(getForm()))
			setLife(0);
		if (getForm().overlaps(get__world().getGoal().getForm())) {
			setLife(0);
			get__world().getGoal().decLife(1);
		}
	}

}
