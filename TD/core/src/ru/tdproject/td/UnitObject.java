package ru.tdproject.td;
import com.badlogic.gdx.math.Vector2;

public abstract class UnitObject extends BaseObject {
public UnitObject(String type, boolean isSolid) {
		super(type);
		this.isSolid=isSolid;
	}

//
private boolean isSolid;
}
