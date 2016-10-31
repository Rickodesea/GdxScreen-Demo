package com.algodal.gdxscreen_demo;

public class SaveDataA {
	private SaveDataB dog = new SaveDataB();
	private float avg = 0.97f;
	private boolean girlfriend = false;
	
	public final SaveDataB getDog() {
		return dog;
	}
	public final void setDog(SaveDataB dog) {
		this.dog = dog;
	}
	public final float getAvg() {
		return avg;
	}
	public final void setAvg(float avg) {
		this.avg = avg;
	}
	public final boolean isGirlfriend() {
		return girlfriend;
	}
	public final void setGirlfriend(boolean girlfriend) {
		this.girlfriend = girlfriend;
	}
	
	
}
