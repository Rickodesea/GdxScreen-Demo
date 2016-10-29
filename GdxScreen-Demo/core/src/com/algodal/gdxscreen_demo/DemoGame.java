package com.algodal.gdxscreen_demo;

import com.algodal.gdxscreen.GdxGame;
import com.algodal.gdxscreen.GdxTransition;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DemoGame extends GdxGame {
	public static SpriteBatch batch;
	
	@Override
	public void initialize() {
		batch = new SpriteBatch();
		registerAsset("bad", new AssetDescriptor<>("badlogic.jpg", Texture.class));
		registerScreen("play", PlayScreen.class);
		registerTransition("trans", GdxTransition.class);
		attachAssetToScreen("play", "bad");
		System.out.println("initialized");
	}

	@Override
	public void deinitialize() {
		batch.dispose();
	}
	
	
}
