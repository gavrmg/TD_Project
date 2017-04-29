package ru.tdproject.td;

import com.badlogic.gdx.math.Vector2;

public abstract class BaseObject {
	public BaseObject(String type){
		this.type=type;
	}
	//
	private Vector2 position;
	public Vector2 getPosition(){
		return position;
		};
	public Vector2 setPosition(){
		return position;
	};
	//
	private String type;
	public abstract String gettype();
	//
}
