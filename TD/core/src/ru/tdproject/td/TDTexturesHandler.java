package ru.tdproject.td;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class TDTexturesHandler {
	public TDTexturesHandler(int TileTextureSize) {
		setLevelTextures(new Texture("Tiles.png"));
		this.TileTextureSize=TileTextureSize;
		// TODO Auto-generated constructor stub
	}
	private TextureAtlas LevelTextures = new TextureAtlas("LevelTiles.atlas");
	private int TileTextureSize;
	//private Texture LevelTextures;
	
	public Array<AtlasRegion> getLevelTextures(String name) {
		return LevelTextures.findRegions(name);
	}

	public void setLevelTextures(Texture levelTextures) {
//		LevelTextures = levelTextures;
	}
}
