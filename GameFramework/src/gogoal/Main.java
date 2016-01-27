package gogoal;

import java.util.ArrayList;

import gameframework.game.GameDefaultImpl;
import gameframework.game.GameLevel;

public class Main
{
	public static void main(String[] args) {
		GameDefaultImpl g = new GameDefaultImpl();
		ArrayList<GameLevel> levels = new ArrayList<GameLevel>();

		levels.add(new TrainingSessionOne(g));
		
		g.setLevels(levels);
		g.start();
	}
}
