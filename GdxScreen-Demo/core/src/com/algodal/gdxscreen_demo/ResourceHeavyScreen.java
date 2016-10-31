package com.algodal.gdxscreen_demo;

import com.algodal.gdxscreen.GdxScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class ResourceHeavyScreen extends GdxScreen{
	private Sound yeah;
	private InputMultiplexer im;
	private Stage stage;
	private boolean pauseSong;
	private SpriteBatch batch;
	private Table table;
	
	@Override
	public void create() {
		yeah = Gdx.audio.newSound(Gdx.files.internal("yeah.wav"));
		batch = (SpriteBatch) getGameLibrary().getContent("spritebatch").get();
		stage = (Stage) getGameLibrary().getContent("stage").get();
		im = new InputMultiplexer(stage, new InputProcessor() {
			
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
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				((Sound)getAsset("drop")).play();
				return true;
			}
			
			@Override
			public boolean scrolled(int amount) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean mouseMoved(int screenX, int screenY) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean keyUp(int keycode) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean keyTyped(char character) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean keyDown(int keycode) {
				yeah.play();
				return true;
			}
		});
		
		table = new Table((Skin)getAsset("skin"));
		TextButton play = new TextButton("PLAY", (Skin)getAsset("skin"));
		TextButton back = new TextButton("BACK", (Skin)getAsset("skin"));
		TextButton prefs = new TextButton("PREFS", (Skin)getAsset("skin"));
		TextButton data = new TextButton("DATA", (Skin)getAsset("skin"));
		
		play.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if(((Music)getAsset("song")).isPlaying()) ((Music)getAsset("song")).pause();
				else if(!((Music)getAsset("song")).isPlaying())((Music)getAsset("song")).play();
			}
		});
		
		back.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				launch("loadingbar", "play");
			}
		});
		
		prefs.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				launch("trans", "prefs");
			}
		});
		
		data.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				launch("loadingbar", "save");
			}
		});
		
		table.add(play).row();
		table.add(back).row();
		table.add(prefs).row();
		table.add(data).row();
		table.setBounds(0, 0, 640, 480);
	}

	@Override
	public void dispose() {
		yeah.dispose();
		System.out.println("disposed heavy resources");
	}

	@Override
	public void hide() {
		((Music)getAsset("song")).stop();
	}

	@Override
	public void pause() {
		if(((Music)getAsset("song")).isPlaying()){
			((Music)getAsset("song")).pause();
			pauseSong = true;
		}
	}

	@Override
	public void render(float delta) {
		batch.begin();
		batch.draw((Texture)getAsset("abstract"), 0, 0, 640, 480);
		batch.draw((Texture)getAsset("art"), 0, 0, 640, 480);
		batch.end();
		
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height);
	}

	@Override
	public void resume() {
		if(pauseSong){
			((Music)getAsset("song")).play();
		}
	}

	@Override
	public void show() {
		stage.clear();
		stage.addActor(table);
		Gdx.input.setInputProcessor(im);
		((Music)getAsset("song")).setLooping(true);
		((Music)getAsset("song")).play();
	}
	
}
