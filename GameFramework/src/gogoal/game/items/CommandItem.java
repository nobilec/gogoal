package gogoal.game.items;

import gogoal.rendering.Pseudo3DDrawableImage;

public interface CommandItem {
	public void execute();
	public boolean isMalus();
	public Pseudo3DDrawableImage getImage();
}
