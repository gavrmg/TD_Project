package ru.tdproject.td;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Unit extends UnitObject {

	public Unit(String type, boolean isSolid) {
		super(type, isSolid);
		// TODO Auto-generated constructor stub
	}

	public Unit(String type, boolean isSolid, Vector2 pos) {
		super(type, isSolid, pos);
		// TODO Auto-generated constructor stub
	}

	
	public Unit(String type, Vector2 pos, boolean isSolid, Texture img) {
		super(type, pos, isSolid, img);
		// TODO Auto-generated constructor stub
	}

	public Unit(String type, Vector2 pos, float speed, Vector2 arrow, boolean isSolid, Texture img) {
		super(type, pos, speed, arrow, isSolid,img);
		// TODO Auto-generated constructor stub
	}

	public Unit(String type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String gettype() {
		// TODO Auto-generated method stub
		return gettype();
	}

	Object lock = new Object();

	private float t = 0f;
	
	@Override
	public void move() {
		synchronized (lock) {
			float speed = getSpeed();
			Vector2 arrow  = getArrow();
			Vector2 pos = getPosition();
			arrow.x = MathUtils.cos(t);
			arrow.y = MathUtils.sin(t);
			pos.x += speed * arrow.x;
			pos.y += speed * arrow.y;
			t += 0.1;
			setArrow(arrow);
		}
	}

	@Override
	public void draw(SpriteBatch batch) {
		// TODO Auto-generated method stub
		Texture img = getImg();
		batch.draw(img, getPosition().x, getPosition().y);
		System.out.println("In Draw... "+getPosition().x+" "+ getPosition().y);
		
	}

}
