package ru.tdproject.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Tower extends BaseObject  {
	private float AttackRange;
	private attackType AttackType;
	private String Type;
	private int Health;
	public Tower(String Type,Texture img, float attackRange, int health) {
		//super();
		setImg(img);
		AttackRange = attackRange;
		AttackType = attackType.Ranged;
		Health = health;
		this.Type = Type;
	}
	public float getAttackRange() {
		return AttackRange;
	}
	public void setAttackRange(float attackRange) {
		AttackRange = attackRange;
	}
	public attackType getAttackType() {
		return AttackType;
	}
	public void setAttackType(attackType attackType) {
		AttackType = attackType;
	}
	public int getHealth() {
		return Health;
	}
	public void setHealth(int health) {
		Health = health;
	}
	@Override
	public void step(TDWorld world){
		
	}
	@Override
	public ru.tdproject.td.ObjectType getObjectType() {
		// TODO Auto-generated method stub
		return ObjectType.Active;
	}
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return Type;
	}






}
