package gogoal.perception_effects.decorators;

public class PerceptionEffectSpeed extends PerceptionEffectAbsTimed
{
	public PerceptionEffectSpeed() {
		this(null);
	}
	
	public PerceptionEffectSpeed(PerceptionEffectAbs deco){
		super(deco);
	}
	
	@Override
	protected void runEffects() {
		/* A impl�menter : modifie la vitesse des objets mouvants du jeu,
		 * � l'avantage du joueur... Ou pas.
		 */
	}

}
