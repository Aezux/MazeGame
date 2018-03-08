package powerups;

public class Toolbar {
	//USE THIS ARRAY TO REPRESENT THE POWERUP TOOLBAR
	PowerUps[] powers;
	
	//THIS CONSTANT HERE CHANGE IF ADDING MORE POWERS
	final int NUMPOWERUPS = 4;
	
	public Toolbar()
	{
		powers = new PowerUps[4];
		PowerUps noPower = new NoPower();
		for(int i = 0 ; i < powers.length ; i++)
		{
			powers[i] = noPower;
		}
		
	}
	
	//USE THIS WHEN PICKING UP A POWERUP TO ASSIGN TO TOOLBAR
	public void setPower(int slot, PowerUps power)
	{
		powers[slot-1] = power;
	}
	
	public void removePower(int slot)
	{
		powers[slot-1] = new NoPower();
	}
	
	//HERE USE THIS METHOD WHEN USING A POWERUP SLOT
	public void useSlot(int slot)
	{
		powers[slot-1].execute();
	}
}
