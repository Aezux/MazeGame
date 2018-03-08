package powerups;

import game.DungeonMap;
import game.Player;

public class Speed {
	private int uses = 3;
	private Player player;

	public Speed(DungeonMap map, Player p) {
		this.player = p;
	}

	public void activatePower()
	{
		if(uses > 0)
		{
			//Decrease enemy speed
			player.alert("speed");
			uses--;
		}
		else
		{
			System.out.println("No uses remaining.");
		}
	}
}
