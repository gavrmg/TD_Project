package ru.tdproject.td.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import ru.tdproject.td.game.TDWorld;
import ru.tdproject.td.utils.attackType;

public class Tower extends BaseObject {
	private float AttackRange;
	private attackType AttackType;
	private String Type;
	private int Health;
	private Location<Vector2> Target;
	private long AttackCooldown;
	// private long AttackCooldown;

	public Tower(String Type, Texture img, float attackRange, int health, Body body, TDWorld world) {
		super(world, body, img);
		setImg(img);
		AttackRange = attackRange;
		AttackType = attackType.Ranged;
		Health = health;
		this.Type = Type;
		Target = null;
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
	public void step() {

		if (Target == null || getPosition().dst(Target.getPosition()) > AttackRange) {
			Target = null;
			for (BaseObject o : world.getUnits()) {
				if (o.getType() == "Unit") {
					if ((Target == null) && (o.getPosition().dst(getPosition()) < AttackRange)) {
						Target = o;
						continue;
					}
					if (Target == null)
						continue;
					else if (o.getPosition().dst(getPosition()) < AttackRange && Target != null) {
						Target = o;

					}
				}
			}
		}
		if (Target != null && System.currentTimeMillis() - AttackCooldown > 1000
				|| (AttackCooldown == 0 && Target != null)) {
			AttackCooldown = System.currentTimeMillis();
			world.createProjectile(1, Target, getPosition().x, getPosition().y);
			System.out.println(((BaseObject)Target).getType());
			if(((BaseObject)Target).isToDispose()){
				Target = null;
			}
			
		}

	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return Type;
	}

}
