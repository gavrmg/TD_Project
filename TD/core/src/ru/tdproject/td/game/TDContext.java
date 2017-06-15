package ru.tdproject.td.game;

import com.badlogic.gdx.math.Matrix3;

public class TDContext {
	public int HEIGHT = 800;
	public int WIDGHT = 800;
	public int MAP_SIZE = 100;
	public static int PIX_TO_METER = 20;
	public static float[] WORLD_TO_SCREEN = {(float)PIX_TO_METER,0,0,0,(float)PIX_TO_METER,0,0,0,1f};
	public static Matrix3 METER_TO_TILE = new Matrix3(WORLD_TO_SCREEN);
}
