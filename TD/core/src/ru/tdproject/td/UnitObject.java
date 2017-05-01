package ru.tdproject.td;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public abstract class UnitObject extends BaseObject {

	public UnitObject(String type, boolean isSolid, Vector2 pos) {
		super(type, pos);
		this.isSolid = isSolid;
		
	}
	
	public UnitObject(String type, Vector2 pos, float speed, Vector2 arrow, boolean isSolid, Texture img) {
		super(type, pos, speed, arrow);
		// TODO Auto-generated constructor stub
		this.isSolid = isSolid;
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

	//
	private boolean isSolid;
}
