package com.algodal.gdxscreen_demo;

import com.algodal.gdxscreen.GdxScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class PlayScreen extends GdxScreen{
	private SpriteBatch batch;
	private Stage stage;
	private Table table;
	
	@Override
	public void create() {
		System.out.println("created");
		batch = (SpriteBatch) getGameLibrary().getContent("spritebatch").get();
		stage = (Stage) getGameLibrary().getContent("stage").get();
		table = new Table((Skin)getAsset("skin"));
		TextButton play = new TextButton("PLAY", (Skin)getAsset("skin"));
		TextButton back = new TextButton("BACK", (Skin)getAsset("skin"));
		
		play.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				launch("loadingbar", "heavy");
			}
		});
		
		back.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Gdx.app.exit();
			}
		});
		
		table.add(play).row();
		table.add(back);
		table.setBounds(0, 0, 640, 480);
	}
	
	@Override
	public void show() {
		stage.clear();
		stage.addActor(table);
		Gdx.input.setInputProcessor(stage);
		System.out.println("show");
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height);
	}

	@Override
	public void render(float delta) {
		batch.begin();
		batch.draw((Texture)getAsset("bad"), 0, 0, 640, 480);
		batch.end();
		
		stage.act(delta);
		stage.draw();
	}
}
