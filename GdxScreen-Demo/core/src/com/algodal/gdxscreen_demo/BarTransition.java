package com.algodal.gdxscreen_demo;

import com.algodal.gdxscreen.GdxTransition;
import com.algodal.gdxscreen.utils.GdxLibrary.Content;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class BarTransition extends GdxTransition{
	private Content<ShapeRenderer> content;
	private ShapeRenderer shape;
	private Stage stage;
	private Table table;
	private int counter;
	private Label label;
	
	@Override
	public void create() {
		//This is independent of the library's methods: create, destroy, load and unload
		//therefore, it has to be processed individually.
		content = getGameLibrary().getContent("shaperenderer");
		
		//initialize it
		content.initialize();
		
		shape = (ShapeRenderer)content.get();
		stage = (Stage) getGameLibrary().getContent("stage").get();
		
		table = new Table((Skin)getAsset("skin"));
		label = new Label("Loading", (Skin)getAsset("skin"));
		
		table.add(label);
		table.setBounds(0, 0, 640, 480);
	}
	
	

	@Override
	public void dispose() {
		//dispose it
		content.dispose();
	}


	@Override
	public void show() {
		stage.clear();
		stage.addActor(table);
		Gdx.input.setInputProcessor(stage);
		startAsynchronousLoadingOfNewScreenAssets();
	}

	@Override
	public void render(float delta) {
		shape.begin(ShapeType.Filled);
		shape.rect(0, 0, 640 * getNewScreenAssetProgress(), 480);
		shape.setColor(Color.BLACK);
		shape.end();
		
		stage.act(delta);
		stage.draw();
		
		counter ++;
		label.setText(label.getText() + ".");
		if(counter >= 10){
			counter = 0;
			label.setText("Loading");
		}
		
		if(getNewScreenAssetProgress() == 1.0f) deliverNewScreen();
	}
	
}
