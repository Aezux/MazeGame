package powerups;

public class ArmorActivate extends PowerUps {

	
	Armor armor;
	
	public ArmorActivate(Armor armor)
	{
		this.armor = armor;
	}

	public void execute() {
		armor.activate();
		
	}

}
