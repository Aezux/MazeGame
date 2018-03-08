package powerups;

import game.DungeonMap;
import game.Player;

public class Invisible{
	private int uses = 1;
	private Player player;
	
	public Invisible(DungeonMap map, Player p) {
		this.player = p;
	}

	public void activatePower()
	{
		if(uses > 0)
		{
			player.alert("patrol");
			uses--;
		}
		else
		{
			System.out.println("No uses remaining.");
		}
	}
	
	}
