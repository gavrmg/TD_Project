package ru.tdproject.td;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Tower extends BaseObject {

	
	public Tower(String type, Circle form, float speed, Vector2 arrow, Texture img, World world,float AttackRange,int Cooldown) {
		super(type, form, speed, arrow, img, world);
		this.AttackRange = new Circle(getForm().x,getForm().y,AttackRange);
		this.Cooldown = Cooldown;
		this.setLife(2);
		// TODO Auto-generated constructor stub
	}
	private Circle AttackRange;
	private int Cooldown;
	private int Time = 0;
	@Override
	public void step(Rectangle Border) {
		if(Time == 0){
			for(BaseObject o : get__world().getUnits()){
				if (AttackRange.contains(o.getForm().x,o.getForm().y) && o.gettype()!="Tower"){
					o.decLife(1);
					Time = Cooldown;
					break;
				}
			}
		}
		else
			Time--;
	}

}
