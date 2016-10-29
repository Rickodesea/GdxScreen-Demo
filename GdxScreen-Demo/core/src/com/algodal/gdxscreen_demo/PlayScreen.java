package com.algodal.gdxscreen_demo;

import com.algodal.gdxscreen.GdxScreen;
import com.badlogic.gdx.graphics.Texture;

public class PlayScreen extends GdxScreen{
	
	@Override
	public void render(float delta) {
		DemoGame.batch.begin();
		DemoGame.batch.draw((Texture)getAsset("bad"), 0, 0, 640, 480);
		DemoGame.batch.end();
	}
	
}
