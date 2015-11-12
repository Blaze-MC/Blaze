package info.BlazeMC.Blaze.Inventory;

import info.BlazeMC.BlazeAPI.Entity.Item.ItemStack;
import info.BlazeMC.BlazeAPI.Inventory.Inventory;
import info.BlazeMC.BlazeAPI.Inventory.InventoryType;
import info.BlazeMC.BlazeAPI.Inventory.InventoryViewer;

public class BlazeInventory implements Inventory
{
	private InventoryType type;
	private InventoryViewer viewer;
	private ItemStack[] contents;
	
	public BlazeInventory(InventoryType type, InventoryViewer viewer, ItemStack[] contents)
	{
		this.type = type;
		this.viewer = viewer;
		this.contents = contents;
	}
	
	public InventoryType getInventoryType() 
	{
		return type;
	}

	public ItemStack[] getContents()
	{
		return contents;
	}

	public void setItem(ItemStack item, int slot) 
	{
		contents[slot] = item;
	}

	public ItemStack getItem(int slot) 
	{
		return contents[slot];
	}

	public InventoryViewer getViewer() 
	{
		return viewer;
	}
}
