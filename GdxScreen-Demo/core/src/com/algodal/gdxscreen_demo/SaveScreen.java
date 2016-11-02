package com.algodal.gdxscreen_demo;

import com.algodal.gdxscreen.utils.GdxLoad;
import com.algodal.gdxscreen.utils.GdxSave;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class SaveScreen extends StageScreen{
	private TextButton play;
	private TextButton back;
	private TextArea text;
	private SaveDataA data;
	private GdxSave save;
	private GdxLoad load;

	@Override
	public void create() {
		super.create();
		SaveDataB subData = new SaveDataB();
		subData.setHiAMe("Hey, Alrick");
		subData.setNumberA(40);
		subData.setNumberB(95);
		subData.setYo('A');
		subData.setT("B".getBytes());
		data = new SaveDataA();
		data.setDog(subData);
		data.setAvg(0.97f);
		data.setGirlfriend(false);
		data.doesWork = "Yes";
		save = new GdxSave(Gdx.files.local("SaveDataForGdxScreenDemo.txt"), "TestingGdxSave");
		save.getPlainOldJavaObjects().add(data); //Remember to add your object that you want to save.
		String string = save.save();
		System.out.println(string);
		text = new TextArea(string, (Skin)getAsset("skin"));
		load = new GdxLoad(Gdx.files.local("SaveDataForGdxScreenDemo.txt"));
		
		play = new TextButton("PLAY", (Skin)getAsset("skin"));
		back = new TextButton("BACK", (Skin)getAsset("skin"));
		
		play.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				text.setText(load.load().getRepresentation());
				System.out.println("Loaded");
			}
		});
		
		back.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				launch("loadingbar", "heavy");
			}
		});
		
		table.add(text).row();
		table.add(play).row();
		table.add(back).row();
	}

	@Override
	public void show() {
		super.show();
	}
}
