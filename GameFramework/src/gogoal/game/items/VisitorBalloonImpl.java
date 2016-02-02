package gogoal.game.items;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import gogoal.game.entities.BalloonEntity;

public class VisitorBalloonImpl implements VisitorBalloon{
	public static final int DEFAULT_ITEM_CHANCE = 10; 
	
	protected ArrayList<CommandItem> items;
	protected final int itemChance;
	
	public VisitorBalloonImpl(ArrayList<CommandItem> items){
		this(items, DEFAULT_ITEM_CHANCE);
	}
	
	public VisitorBalloonImpl(ArrayList<CommandItem> items, int itemChance){
		this.items = items;
		this.itemChance = itemChance;
	}

	@Override
	public void visit(BalloonEntity be) {
		if ( items != null ){
			Random r = new Random(new Date().getTime());
			int check = Math.abs(r.nextInt()) % 100;
			
			if ( check <= itemChance) {
				
				int index = Math.abs(r.nextInt()) % items.size();
				CommandItem given = items.get(index);
				be.setCarriedItem(given);
			}
		}
	}
}
