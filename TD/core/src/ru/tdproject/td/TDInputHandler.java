package ru.tdproject.td;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
public class TDInputHandler implements InputProcessor  {
	private World _world;
	private TD_Game _game;
	private TDContext _context;
	private Vector2 prevPosition;
	private Vector3 currentPosition;
	Buttons _buttons = new Buttons();
	Keys _keys = new Keys();
//	private boolean _cameraMove;
	public Vector2 Delta;
	public TDInputHandler(World _world,TD_Game _game, TDContext _context) {
		this._world = _world;
		this._game = _game;
		this._context = _context;
//		this.prevPosition = this.currentPosition = this.Delta = new Vector2(0,0);
		this.currentPosition = new Vector3(0,0,0);
		this.Delta = new Vector2(0,0);
		//this._cameraMove = false;
		Gdx.input.setInputProcessor(this);
	}
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
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
	//	_game.camera.update();
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		if (keycode == _keys.W||keycode == _keys.A||keycode == _keys.S||keycode == _keys.D){
			Delta.set(0,0);
		}
//		if (keycode == _keys.A){
//			Delta.set(0,0);
//		}
//		if (keycode == _keys.S){
//			Delta.set(0,0);
//		}
//		if (keycode == _keys.D){
//			Delta.set(0,0);
//		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (button == _buttons.LEFT){
			currentPosition.set((float) screenX, (float)screenY, 0);
			currentPosition.set(_game.camera.unproject(currentPosition));
			_world.createTower(currentPosition.x, currentPosition.y);
			System.out.println(_game.camera.unproject(currentPosition).x+" "+(_context.HEIGHT - _game.camera.unproject(currentPosition).y));
		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
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
