package powerups;

public class SpeedActivate extends PowerUps {

	
	Speed speed;
	
	public SpeedActivate(Speed speed)
	{
		this.speed = speed;
	}

	public void execute() {
		speed.activatePower();
		
	}

}
