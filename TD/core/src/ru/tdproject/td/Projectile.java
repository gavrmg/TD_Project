package ru.tdproject.td;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Projectile extends BaseObject {

	public Projectile( Circle form, float speed,  Texture img, World world, int Damage, BaseObject Target) {
		super("Projectile", form, speed, new Vector2(Target.getForm().x-form.x,Target.getForm().y-form.y), img, world);
		this.Damage = Damage;
		this.Target = Target;
		this.setLife(1);
		// TODO Auto-generated constructor stub
	}
	private int Damage;
	BaseObject Target;
	@Override
	public void step(Rectangle Border) {
		// TODO Auto-generated method stub
		synchronized (lock) {
			float speed = getSpeed();
			Vector2 pos = new Vector2(getForm().x,getForm().y);
			Vector2 arrow  = new Vector2(Target.getForm().x - pos.x,Target.getForm().y- pos.y).nor();
			//arrow.x = MathUtils.cos(t);
			//arrow.y = MathUtils.sin(t);
			pos.mulAdd(arrow, speed); 
			getForm().setPosition(pos);
			setArrow(arrow);
			if (!Border.contains(getForm()))
				setLife(0);
			if (Target.getForm().contains(getForm()))
			{
				Target.decLife(Damage);
				setLife(0);
				Target = null;
			}
		}

	}
}