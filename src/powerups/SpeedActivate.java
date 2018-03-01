package powerups;

public class SpeedActivate extends PowerUps {

	
	Speed speed;
	
	public SpeedActivate(Speed speed)
	{
		this.speed = speed;
	}
	@Override
	public void execute() {
		speed.activatePower();
		
	}

}
