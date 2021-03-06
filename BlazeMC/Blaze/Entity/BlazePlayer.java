/*
The MIT License (MIT)

Copyright (c) 2015 BlazeMC

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package info.BlazeMC.Blaze.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

import org.apache.commons.lang3.Validate;

import info.BlazeMC.Blaze.Entity.Item.BlazeItemStack;
import info.BlazeMC.Blaze.Inventory.BlazePlayerInventory;
import info.BlazeMC.BlazeAPI.GameMode;
import info.BlazeMC.BlazeAPI.Entity.Player;
import info.BlazeMC.BlazeAPI.Entity.Item.ItemStack;
import info.BlazeMC.BlazeAPI.Inventory.Inventory;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.WorldSettings.GameType;

public class BlazePlayer implements Player
{
	private EntityPlayerMP base;
	private BlazePlayerInventory inventory;
	private ArrayList<String> permissions = new ArrayList<String>();
	
	public BlazePlayer(EntityPlayerMP base)
	{
		this.base = base;
		updateInventory();
	}
	
	public EntityPlayerMP getBase()
	{
		return base;
	}

	public String getName()
	{
		return base.getName();
	}

	public UUID getUUID() 
	{
		return base.getUniqueID();
	}

	public void sendMessage(String message)
	{
		Validate.notNull(message);
		base.addChatMessage(new ChatComponentText(message));
	}

	public void setGameMode(GameMode mode) 
	{
		Validate.notNull(mode);
		base.setGameType(GameType.valueOf(mode.getName()));
	}

	public GameMode getGamemode() 
	{
		return GameMode.getByName(base.theItemInWorldManager.getGameType().getName());
	}
	
	public boolean isBlocking()
	{
		return base.isBlocking();
	}
	
	public float getHealth()
	{
		return base.getHealth();
	}
	
	public void setHealth(float health)
	{
		Validate.notNull(health);
		base.setHealth(health);
	}
	
	public int getHunger()
	{
		return base.getFoodStats().getFoodLevel();
	}
	
	public void setHunger(int amount)
	{
		Validate.notNull(amount);
		base.getFoodStats().setFoodLevel(amount);
	}
	
	public Inventory getInventory() 
	{
		return inventory;
	}
	
	//TODO
	public void updateInventory()
	{
		ArrayList<BlazeItemStack> contents = new ArrayList<BlazeItemStack>();
		
		for(net.minecraft.item.ItemStack mcItem : base.getInventory())
		{
			int id = Item.getIdFromItem(mcItem.getItem());
			byte data = (byte) mcItem.getMetadata();
			int stackSize = mcItem.stackSize;
	
			contents.add(new BlazeItemStack(id, data, stackSize));
		}
		
		inventory = new BlazePlayerInventory(this, (ItemStack[]) contents.toArray());
	}

	public void addPermission(String permission) 
	{
		Validate.notNull(permission);
		permissions.add(permission);
	}

	public void removePermission(String permission)
	{
		Validate.notNull(permission);
		permissions.remove(permission);
	}
	
	public boolean hasPermission(String permission) 
	{
		Validate.notNull(permission);
		return permissions.contains(permission);
	}
	
	public Collection<String> getPermissions() 
	{
		return Collections.unmodifiableList(permissions);
	}
}
