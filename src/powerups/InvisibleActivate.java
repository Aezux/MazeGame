package powerups;

public class InvisibleActivate extends PowerUps {

	
	Invisible invis;
	
	public InvisibleActivate(Invisible invis)
	{
		this.invis = invis;
	}

	public void execute() {
		invis.activatePower();
		
	}

}
