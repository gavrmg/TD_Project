package ru.tdproject.td.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.tdproject.td.TD_Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = TD_Game._context.HEIGHT;
		config.width = TD_Game._context.WIDGHT;
		new LwjglApplication(new TD_Game(), config);
	}
}
