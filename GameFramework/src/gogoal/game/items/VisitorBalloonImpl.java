package gogoal.game.items;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import gogoal.game.entities.BalloonEntity;

public class VisitorBalloonImpl implements VisitorBalloon{
	public static final int DEFAULT_ITEM_CHANCE = 30;
	public static final int DEFAULT_MALUS_CHANCE = 30; 
	
	protected ArrayList<CommandItem> bonusItems, malusItems;
	protected final int itemChance, malusChance;
	
	public VisitorBalloonImpl(ArrayList<CommandItem> items){
		this(items, DEFAULT_ITEM_CHANCE, DEFAULT_MALUS_CHANCE);
	}
	
	public VisitorBalloonImpl(ArrayList<CommandItem> items, int itemChance, int malusChance){
		this.bonusItems = new ArrayList<CommandItem>();
		this.malusItems = new ArrayList<CommandItem>();
		this.itemChance = itemChance;
		this.malusChance = malusChance;
		
		for ( CommandItem ci : items ){
			if ( ci.isMalus() )
				malusItems.add(ci);
			else
				bonusItems.add(ci);
		}
	}

	@Override
	public void visit(BalloonEntity be) {
		Random r = new Random(new Date().getTime());
		int check = Math.abs(r.nextInt()) % 100;
		
		if ( check <= itemChance) {
			
			int checkMalus = Math.abs(r.nextInt()) % 100;
			
			if ( checkMalus <= malusChance )
				be.setCarriedItem(getRandomFromList(malusItems));
			else
				be.setCarriedItem(getRandomFromList(bonusItems));
		}
	}
	
	private CommandItem getRandomFromList(ArrayList<CommandItem> list){
		Random r = new Random(new Date().getTime());
		int index = Math.abs(r.nextInt()) % list.size();
		
		return list.get(index);
	}
}
