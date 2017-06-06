package ru.tdproject.td;

import com.badlogic.gdx.math.Matrix3;

public class TDContext {
	public int HEIGHT = 800;
	public int WIDGHT = 800;
	public int MAP_SIZE = 100;
	public int PIX_TO_METER = 20;
	public float[] WORLD_TO_SCREEN = {(float)PIX_TO_METER,0,0,0,(float)PIX_TO_METER,0,0,0,1f};
	public Matrix3 METER_TO_TILE = new Matrix3(WORLD_TO_SCREEN);
}
