package ru.tdproject.td;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class BaseObject {
	public BaseObject(String type) {
		this.type = type;
		this.position.x = 0.0f;
		this.position.y = 0.0f;
		speed = 1f;
		arrow.x = 1f;
		arrow.y  = 0f;
	} 

	public BaseObject(String type, Vector2 pos) {
		this.type = type;
		this.position = pos;
		speed = 1f;
		arrow.x = 1f;
		arrow.y  = 0f;
	} 

	public BaseObject(String type, Vector2 pos, float speed, Vector2 arrow) {
		this.type = type;
		this.position = pos;
		this.speed = speed;
		this.arrow = arrow;
	} 
	
	//
	private Vector2 position;
	public Vector2 getPosition() {
		return position;
	};
	public Vector2 setPosition() {
		return position;
	};

	//
	private String type;
	public String gettype() {
		return type;
	}

	
	// признак неподвижности объекта
	private Boolean frozen;
	public Boolean getFrozen() {
		return frozen;
	}
	public void setFrozen(Boolean frozen) {
		this.frozen = frozen;
	}
	
	//
	private float speed;
	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	//
	private Vector2 arrow;
	public Vector2 getArrow() {
		return arrow;
	}
	public void setArrow(Vector2 arrow) {
		this.arrow = arrow;
	}

	//
	private Texture img;
	public Texture getImg() {
		return img;
	}
	public void setImg(Texture img) {
		this.img = img;
	}

	// перемешение объекта в World
	public abstract void move();
	// отрисовка на сцене
	public abstract void draw(SpriteBatch batch); 
	
	
}
