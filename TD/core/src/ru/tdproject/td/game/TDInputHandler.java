package ru.tdproject.td.game;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.physics.box2d.RayCastCallback;

import ru.tdproject.td.TD_Game;
import ru.tdproject.td.Objects.BaseObject;
public class TDInputHandler implements InputProcessor  {
	private TDWorld world;
	private TD_Game _game;
	private TDContext _context;
	private Vector2 prevPosition;
	private Vector3 currentPosition;
	Buttons _buttons = new Buttons();
	public Vector2 Delta;
	private Iterator<BaseObject> iter;
	private BaseObject o;
	private Vector2 RayEnd;
	private Vector2 RayStart;
	private Vector2 UnitRadius;
	private ifUnitNear ObjectSearchQuery = new ifUnitNear();
	
	private class ifUnitNear implements QueryCallback {
		private Vector2 point = new Vector2(0,0);
		private boolean FixtureHit = false;
		@Override
		public boolean reportFixture(Fixture fixture) {
			if (fixture.isSensor())
				return true;
			else{
				FixtureHit = true;
				return false;
			}
		}
		
	}
	public TDInputHandler(TDWorld world,TD_Game _game, TDContext _context) {
		this.world = world;
		this._game = _game;
		this._context = _context;
		this.currentPosition = new Vector3(0,0,0);
		this.Delta = new Vector2(0,0);
		this.RayStart = new Vector2(0,0);
		this.RayEnd = new Vector2(0,0);
		this.UnitRadius = new Vector2(0,0);
		Gdx.input.setInputProcessor(this);
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.W){
			Delta.set(0,1);
		}
		if (keycode == Keys.A){
			Delta.set(-1,0);
		}
		if (keycode == Keys.S){
			Delta.set(0,-1);
		}
		if (keycode == Keys.D){
			Delta.set(1,0);
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.W||keycode == Keys.A||keycode == Keys.S||keycode == Keys.D){
			Delta.set(0,0);
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (button == Buttons.LEFT){
			currentPosition.set((float) screenX, (float)screenY, 0);
			(currentPosition.set(_game.getCamera().unproject(currentPosition))).scl(1f/TDContext.PIX_TO_METER);
			iter = world.getUnits().iterator();
			RayStart.set(currentPosition.x, currentPosition.y);
			UnitRadius.set(5f,5f);
			world.getWorld().QueryAABB(ObjectSearchQuery, currentPosition.x-UnitRadius.x, currentPosition.y-UnitRadius.y, currentPosition.x+UnitRadius.x, currentPosition.y+UnitRadius.y);
			if (!ObjectSearchQuery.FixtureHit)
				world.createTower(5, world.TowerImg, 20, 5, 0, currentPosition.x, currentPosition.y);
			ObjectSearchQuery.FixtureHit = false;
		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	public void cameraConctrol(int key){
		
	}

}
