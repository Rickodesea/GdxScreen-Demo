package com.algodal.gdxscreen_demo;

/**
 * The class used as POJO for
 * GdxPrefs can only contain java.lang fields.
 *
 */
public class Prefs {
	public String name = "Wally";
	public String description = "Test for GdxPrefs";
	public Integer number = 5;
	public Integer count = 100;
	public Boolean choose = true;
	public short value = 17;
	
	char t;
	private String she;
	protected int score;
	
	public final char getT() {
		return t;
	}
	public final void setT(char t) {
		this.t = t;
	}
	public final String getShe() {
		return she;
	}
	public final void setShe(String she) {
		this.she = she;
	}
	public final int getScore() {
		return score;
	}
	public final void setScore(int score) {
		this.score = score;
	}
	
	
}
