package powerups;

import game.DungeonMap;
import game.Player;

public class Key {	
	private int uses = 1;
	private Player player;
	
	public Key(DungeonMap map, Player p) {
		this.player = p;
	}
	
	public void activatePower()
	{
		if(uses > 0)
		{
			//USE KEY ON CHEST
			if(player.atChest())
			{
				uses--;
				System.out.println("Using Key to open chest.");
				player.openChest();
				if(uses == 0)
					player.removeKey();
			}
			else
			{
				System.out.println("This key could unlock something...");
			}
			
		}
		else
		{
			System.out.println("Already used this key.");
		}
	}
}