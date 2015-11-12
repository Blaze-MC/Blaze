package info.BlazeMC.Blaze.Entity.Item;

import info.BlazeMC.BlazeAPI.Entity.Item.ItemStack;
import net.minecraft.item.Item;

public class BlazeItemStack extends ItemStack
{
	private net.minecraft.item.ItemStack base;
	
	public BlazeItemStack(int itemID, byte itemData, int amount)
	{
		super(itemID, itemData, amount);
		
		base = new net.minecraft.item.ItemStack(Item.getItemById(itemID), itemData, amount);
	}
	
	public net.minecraft.item.ItemStack getBase()
	{
		return base;
	}
}
