package com.algodal.gdxscreen_demo;

import com.algodal.gdxscreen.GdxGame;
import com.algodal.gdxscreen.GdxTransition;
import com.algodal.gdxscreen.utils.GdxLibrary.Content;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class DemoGame extends GdxGame {
	
	@Override
	public void initialize() {
		
		//REGISTER ASSETS
		registerAsset("bad", new AssetDescriptor<>("badlogic.jpg", Texture.class));
		registerAsset("skin", new AssetDescriptor<>("uiskin.json", Skin.class));
		registerAsset("song", new AssetDescriptor<>("JoshWoodward-AfterTheFlames.mp3", Music.class));
		registerAsset("drop", new AssetDescriptor<>("drop.wav", Sound.class));
		registerAsset("abstract", new AssetDescriptor<>("abstract.png", Texture.class));
		registerAsset("art", new AssetDescriptor<>("art.png", Texture.class));
		
		//REGISTER SCREENS AND TRANSITIONS
		/**
		 * The first registered screen and transitions will be launched by GdxGame.
		 * Therefore the transition and screen you want to start your game must be registered
		 * first.  After them, the order does not matter.
		 */
		registerScreen("play", PlayScreen.class);
		registerTransition("trans", GdxTransition.class);
		
		registerScreen("heavy", ResourceHeavyScreen.class);
		registerScreen("prefs", PrefScreen.class);
		registerScreen("save", SaveScreen.class);
		registerTransition("loadingbar", BarTransition.class);
		
		//ATTACH ASSETS TO SCREENS AND TRANSITIONS
		attachAssetToScreen("play", "bad");
		attachAssetToScreen("play", "skin");
		attachAssetToScreen("heavy", "skin");
		attachAssetToScreen("heavy", "song");
		attachAssetToScreen("heavy", "drop");
		attachAssetToScreen("heavy", "abstract");
		attachAssetToScreen("heavy", "art");
		attachAssetToTransition("loadingbar", "skin");
		attachAssetToScreen("prefs", "skin");
		attachAssetToScreen("save", "skin");
		
		//ADD CONTENT TO THE GAME'S LIBRARY
		
		//here I am adding a global sprite batch to be used by all screens
		library.setContent("spritebatch", new Content<SpriteBatch>() {

			@Override
			public void dispose() {
				object.dispose();
			}

			@Override
			public Content<SpriteBatch> initialize() {
				//MANDATORY: you must store your initialized object in the object variable.
				object = new SpriteBatch();
				//MANDATORY: you must set the initialized variable to true.
				initialized = true;
				return this;
			}

			@Override
			public Content<SpriteBatch> load() {
				// nothing to load
				//MANDATORY: you must set the loaded variable to true.
				loaded = true;
				return this;
			}

			@Override
			public void unload() {
				// nothing to unload
				//MANDATORY: you must set the loaded variable to false.
				loaded = false;
			}
			
		});
		
		//here I am adding a global Stage to be used by all screens
		library.setContent("stage", new Content<Stage>() {

			@Override
			public void dispose() {
				object.dispose();
			}

			@Override
			public Content<Stage> initialize() {
				object = new Stage();
				initialized = true;
				return this;
			}

			@Override
			public Content<Stage> load() {
				loaded = true;
				return this;
			}

			@Override
			public void unload() {
				loaded = false;
			}
		});
		
		//here I am adding a global shape renderer.  I will make it independent of the library batch processing.
		library.setContent("shaperenderer", new Content<ShapeRenderer>() {
			@Override
			public void dispose() {
				object.dispose();
			}

			@Override
			public Content<ShapeRenderer> initialize() {
				object = new ShapeRenderer();
				initialized = true;
				return this;
			}

			@Override
			public Content<ShapeRenderer> load() {
				loaded = true;
				return this;
			}

			@Override
			public void unload() {
				loaded = false;
			}
		}.setIndependent(true)); //set it independent
		
		//ENSURE YOU INITIALIZE THE LIBRARY'S CONTENTS
		//you can do it individual with each content or as a whole as here
		library.create();
	}

	@Override
	public void deinitialize() {
		//DESTROY THE GAME'S LIBRARY CONTENT
		//or you can do it individually
		library.destroy();
	}
	
	
}
