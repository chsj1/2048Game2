package com.example.yuansu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * 暂退游戏时的数据处理逻辑类
 * 主要作用：:
 * 1)分数相关的恢复
 * 2)局面的恢复
 * @author Administrator
 *
 */
public class Prefs {
	public static Prefs instance = new Prefs();
	private static Preferences pref;//用于实际保存和获取数据
	public int score;//当前分数
	public int best;//最高分数
	/**
	 * 是否开启新游戏
	 */
	public boolean isLaunchNewGame;
	public String board;//当前的游戏局面

	private Prefs() {
		pref = Gdx.app.getPreferences("hjd-2048");//获取一个Prefereneces的引用

		// 
		if (!pref.contains("score")) {//如果是第一次进入游戏
			//完成相应的初始化操作
			pref.putInteger("score", 0);
			pref.putInteger("best", 0);
			pref.putBoolean("isFirstLaunchGame", true);
			pref.putString("board", "");
			pref.flush();
		}
		load();//完成变量的初始化
	}

	private void load() {
		score = pref.getInteger("score", 0);
		best = pref.getInteger("best", 0);
		isLaunchNewGame = pref.getBoolean("isFirstLaunchGame", true);
		board = pref.getString("board", "");
		// isLaunchNewGame = true;
	}

	/**
	 * 保存分数
	 */
	public void saveScore() {
		pref.putInteger("score", Score.instance.getScore());
		pref.putInteger("best", Score.instance.getBest());
		pref.flush();
	}

	/**
	 * 保存局面
	 * @param board
	 * @param isLauchNewGame
	 */
	public void saveBoard(String board, boolean isLauchNewGame) {
		if (!isLauchNewGame) {
			pref.putString("board", board);

			Gdx.app.log("Save Board1", board);
		}

		pref.putBoolean("isFirstLaunchGame", isLauchNewGame);
		pref.flush();

	}
}
