package powerups;

public class KeyActivate extends PowerUps {

	
	Key key;
	
	public KeyActivate(Key key)
	{
		this.key = key;
	}
	@Override
	public void execute() {
		key.activatePower();
		
	}

}
