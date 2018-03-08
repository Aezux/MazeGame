package powerups;

import game.DungeonMap;
import game.Player;

public class Armor{	

	private int uses = 1;
	private Player player;
	
	public Armor(DungeonMap map, Player p) {
		this.player = p;
	}
	
	public void activate()
	{
		if(uses > 0)
		{
			player.addLife();
			uses--;
		}
	}
}
