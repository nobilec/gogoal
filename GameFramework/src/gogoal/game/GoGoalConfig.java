package gogoal.game;

public class GoGoalConfig {
	private static GoGoalConfig instance = null;
	
	private GoGoalConfig(){}
	
	public final int WIDTH = 800;
	public final int HEIGHT = 600;
	public final String BACKGROUND_IMG = "gogoal-resources/football-field.png";
	public final String GOAL_IMG = "gogoal-resources/goal.png";
	public final String BALLOON_IMG = "gogoal-resources/balloon-medium-transp.gif";
	public final String GLOVES_IMG = "gogoal-resources/gloves.png";
	public final String MURGEX_IMG = "gogoal-resources/flask-murgex.png";
	
	public static GoGoalConfig getInstance(){
		if ( instance == null )
			instance = new GoGoalConfig();
		return instance;
	}
}
