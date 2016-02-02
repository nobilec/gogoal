package gogoal.game.items;

import java.awt.Canvas;

import gogoal.GoGoal;
import gogoal.perception_effects.builders.BuilderPerceptionEffect;
import gogoal.rendering.Pseudo3DDrawableImage;

public class CommandItemImpl implements CommandItem{
	protected Pseudo3DDrawableImage image;
	protected final BuilderPerceptionEffect effect;
	protected final boolean malus;
	
	public CommandItemImpl(
			String imageFile, Canvas c, int w, int h,
			boolean malus, BuilderPerceptionEffect effect)
	{
		this.image = new Pseudo3DDrawableImage(imageFile, c, w, h);
		this.effect = effect;
		this.malus = malus;
	}
	
	public boolean isMalus(){
		return malus;
	}
	
	public Pseudo3DDrawableImage getImage(){
		return image;
	}
	
	@Override
	public void execute() {
		GoGoal.getInstance()
			.getCurrentTrainingSession()
			.getProxyPreceptionEffect()
			.composeEffect(effect);
	}
}
