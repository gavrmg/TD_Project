package ru.tdproject.td.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import ru.tdproject.td.TD_Game;
public class TDInputHandler implements InputProcessor  {
	private TDWorld world;
	private TD_Game _game;
	private TDContext _context;
	private Vector2 prevPosition;
	private Vector3 currentPosition;
	Buttons _buttons = new Buttons();
	Keys _keys = new Keys();
	public Vector2 Delta;
	public TDInputHandler(TDWorld world,TD_Game _game, TDContext _context) {
		this.world = world;
		this._game = _game;
		this._context = _context;
		this.currentPosition = new Vector3(0,0,0);
		this.Delta = new Vector2(0,0);
		Gdx.input.setInputProcessor(this);
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if (keycode == _keys.W){
			Delta.set(0,1);
		}
		if (keycode == _keys.A){
			Delta.set(-1,0);
		}
		if (keycode == _keys.S){
			Delta.set(0,-1);
		}
		if (keycode == _keys.D){
			Delta.set(1,0);
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == _keys.W||keycode == _keys.A||keycode == _keys.S||keycode == _keys.D){
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
			System.out.println(currentPosition.x+" "+(_context.HEIGHT - currentPosition.y));
			(currentPosition.set(_game.getCamera().unproject(currentPosition))).scl(1f/TDContext.PIX_TO_METER);
			world.createTower(5, world.TowerImg, 20, 5, 0, currentPosition.x, currentPosition.y);
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
