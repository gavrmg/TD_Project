package ru.tdproject.td;

import com.badlogic.gdx.Gdx;
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
		this.P_Img = new Texture("projectile.png"); 
		
		// TODO Auto-generated constructor stub
	}
	private Texture P_Img;
	private Circle AttackRange;
	private int Cooldown;
	private int Time = 0;
	private void Attack_by_Projectile(BaseObject Target, int Damage){
		get__world().addObject(new Projectile( new Circle(this.getForm().x,this.getForm().y,0), 0.6f, P_Img, get__world(), Damage, Target));
	}
	@Override
	public void step(Rectangle Border) {
		if(Time == 0){
			for(BaseObject o : get__world().getUnits()){
				
				if (AttackRange.contains(o.getForm().x,o.getForm().y) && o.gettype()!="Tower"){
					Attack_by_Projectile(o, 1);
					Time = Cooldown;
					break;
				}
			}
		}
		else
			Time--;
	}

}
