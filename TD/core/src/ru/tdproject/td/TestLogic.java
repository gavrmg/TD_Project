package ru.tdproject.td;

import com.badlogic.gdx.math.*;

public class TestLogic {
	private Vector2 Pos = new Vector2(200, 200);
	private Vector2 V = new Vector2();
	private float t = 0;
	Object lock = new Object();

	public void move() {
		synchronized (lock) {
			V.x = 10 * MathUtils.cos(t);
			V.y = 10 * MathUtils.sin(t);
			Pos.x += V.x;
			Pos.y += V.y;
			t += 0.1;
			
		}
	};

	synchronized public Vector2 getPos() {
		synchronized (lock) {
			return Pos;
			
		}
	}
}
