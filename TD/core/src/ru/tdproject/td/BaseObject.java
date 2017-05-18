package ru.tdproject.td;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class BaseObject {

	public BaseObject(String type, Circle form, float speed, Vector2 arrow, Texture img,World world) {
		this.type = type;
		this.Form = form;
		this.speed = speed;
		this.arrow = arrow;
		this.img=img;
		this.__world = world;
	} 
	
	private World __world = null;
	//
	public void setWorld(World world){
		this.__world = world;
	}
	public World get__world() {
		return __world;
	}

	private String type;
	public String gettype() {
		return type;
	}
	private int life;
	
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
	public void decLife(int damage) {
		this.life -= damage;
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
	private Circle Form;
	public Circle getForm() {
		return Form;
	}

	public void setForm(Circle form) {
		Form = form;
	}
	Object lock = new Object();

	// перемешение объекта в World
	public void draw(SpriteBatch batch) {
		synchronized (lock) {
		Texture img = getImg();
		batch.draw(img, getForm().x, getForm().y);
		//System.out.println("In Draw... "+getForm().x+" "+ getForm().y);
		}		

	}
	
	public abstract void step(Rectangle Border);
	
	
}
