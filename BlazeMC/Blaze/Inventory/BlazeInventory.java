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
