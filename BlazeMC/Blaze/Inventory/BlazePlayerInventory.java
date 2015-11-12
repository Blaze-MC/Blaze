package info.BlazeMC.Blaze.Inventory;

import info.BlazeMC.BlazeAPI.Entity.Player;
import info.BlazeMC.BlazeAPI.Entity.Item.ItemStack;
import info.BlazeMC.BlazeAPI.Inventory.InventoryType;
import info.BlazeMC.BlazeAPI.Inventory.InventoryViewer;

public class BlazePlayerInventory extends BlazeInventory
{
	private Player player;
	
	public BlazePlayerInventory(Player player, ItemStack[] contents) 
	{
		super(InventoryType.PLAYER, InventoryViewer.PLAYER, contents);
		this.player = player;
	}
	
	public Player getPlayer()
	{
		return player;
	}
}	
