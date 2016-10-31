package com.algodal.gdxscreen_demo;

import com.algodal.gdxscreen.GdxScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Field;

public class StageScreen extends GdxScreen{
	public Stage stage;
	public Table table;

	@Override
	public void create() {
		stage = getGameLibrary().getContent("stage", Stage.class).get();
		table = new Table(getAsset("skin", Skin.class));
		table.setBounds(0, 0, 640, 480);
	}
	
	
	@Override
	public void render(float delta) {
		stage.draw();
		stage.act(delta);
	}


	@Override
	public void show() {
		stage.clear();
		stage.addActor(table);
		Gdx.input.setInputProcessor(stage);
	}

	public <T> void generateStage(T object, Class<T> clazz){
		try{
			for(Field field : ClassReflection.getFields(clazz)){
				field.setAccessible(true); //allow you to access restricted fields
				table.add(new Label(field.getName(), getAsset("skin", Skin.class)));
				//when working with Object type, using ' "" + object ' guarantees to output a string representation without throwing any exceptions.
				table.add(new TextField("" + field.get(object), getAsset("skin", Skin.class)));
				table.row();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
