package ru.tdproject.td;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Castle extends BaseObject {

	public Castle(Circle form, Texture img, World world) {
		super("Castle", form, 0, new Vector2(1f,0f), img, world);
		setLife(10);
	}
	@Override
	public void step(Rectangle Border) {
		// TODO Auto-generated method stub
		
	}

	

}
