package com.zanateh.scrapship;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "scrapship";
		cfg.useGL20 = true;
		cfg.width = 1024;
		cfg.height = 768;
		
		new LwjglApplication(new ScrapShipGame(), cfg);
	}
}
