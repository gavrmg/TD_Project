package ru.tdproject.td;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.*;

public abstract class UnitObject extends BaseObject {

	public UnitObject(String type, boolean isSolid, Vector2 pos) {
		super(type, pos);
		this.isSolid = isSolid;
		
	}
	
	public UnitObject(String type, float speed, Vector2 arrow, boolean isSolid, Texture img,Circle form) {
		super(type, arrow, speed, arrow);
		// TODO Auto-generated constructor stub
		this.isSolid = isSolid;
		this.Form = form;
		setImg(img);
	}

	public UnitObject(String type, Vector2 pos, boolean isSolid, Texture img) {
		super(type, pos);
		// TODO Auto-generated constructor stub
		this.isSolid = isSolid;
		setImg(img);
	}

	public UnitObject(String type) {
		super(type);
		// TODO Auto-generated constructor stub
		this.isSolid = true;
	}

	public UnitObject(String type, boolean isSolid) {
		super(type);
		this.isSolid = isSolid;
	}

	public UnitObject(String type, Circle form) {
		super(type);
		Form = form;
	}


	//
	private boolean isSolid;
	
	
	private Circle Form;
	public Circle getForm() {
		return Form;
	}

	public void setForm(Circle form) {
		Form = form;
	}
}
