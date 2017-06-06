package ru.tdproject.td;

import com.badlogic.gdx.graphics.Texture;

public abstract class BaseObject {
	public abstract void step(TDWorld world);
	private ru.tdproject.td.ObjectType ObjectType;
	private Texture img;
	public abstract ru.tdproject.td.ObjectType getObjectType();
	public abstract String getType();
	public Texture getImg() {
		return img;
	}
	public void setImg(Texture img) {
		this.img = img;
	}
//	public abstract void step(TDWorld world);
	//SolidOBject doesn't have a Texture, so to avoid NullPtrExeption use should filter Objects by ObjectType 
}
