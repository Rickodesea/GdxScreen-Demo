package com.algodal.gdxscreen_demo;

import com.algodal.gdxscreen.utils.GdxPrefs;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class PrefScreen extends StageScreen{
	private GdxPrefs<Prefs> gdxPrefs;
	private TextButton play;
	private TextButton back;

	@Override
	public void create() {
		super.create();
		gdxPrefs = new GdxPrefs<>("GdxScreenDemoTestPrefs", Prefs.class);
		
		play = new TextButton("PLAY", (Skin)getAsset("skin"));
		back = new TextButton("BACK", (Skin)getAsset("skin"));
		
		play.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				gdxPrefs.save();
				play.setText("SAVED TO PREFS FILE");
			}
		});
		
		back.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				launch("trans", "heavy");
			}
		});
	}

	@Override
	public void show() {
		super.show();
		table.clear();
		generateStage(gdxPrefs.object, Prefs.class);
		table.add(play).row();
		table.add(back);
	}
}
