package com.example.yuansu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * ������Ϸʱ�����ݴ����߼���
 * ��Ҫ���ã�:
 * 1)������صĻָ�
 * 2)����Ļָ�
 * @author Administrator
 *
 */
public class Prefs {
	public static Prefs instance = new Prefs();
	private static Preferences pref;//����ʵ�ʱ���ͻ�ȡ����
	public int score;//��ǰ����
	public int best;//��߷���
	/**
	 * �Ƿ�������Ϸ
	 */
	public boolean isLaunchNewGame;
	public String board;//��ǰ����Ϸ����

	private Prefs() {
		pref = Gdx.app.getPreferences("hjd-2048");//��ȡһ��Prefereneces������

		// 
		if (!pref.contains("score")) {//����ǵ�һ�ν�����Ϸ
			//�����Ӧ�ĳ�ʼ������
			pref.putInteger("score", 0);
			pref.putInteger("best", 0);
			pref.putBoolean("isFirstLaunchGame", true);
			pref.putString("board", "");
			pref.flush();
		}
		load();//��ɱ����ĳ�ʼ��
	}

	private void load() {
		score = pref.getInteger("score", 0);
		best = pref.getInteger("best", 0);
		isLaunchNewGame = pref.getBoolean("isFirstLaunchGame", true);
		board = pref.getString("board", "");
		// isLaunchNewGame = true;
	}

	/**
	 * �������
	 */
	public void saveScore() {
		pref.putInteger("score", Score.instance.getScore());
		pref.putInteger("best", Score.instance.getBest());
		pref.flush();
	}

	/**
	 * �������
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
