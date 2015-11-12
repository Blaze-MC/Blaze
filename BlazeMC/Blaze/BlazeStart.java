package info.BlazeMC.Blaze;

import info.BlazeMC.BlazeAPI.Blaze;

public final class BlazeStart
{
	public static void main(String[] args) throws InstantiationException, IllegalAccessException
	{
		Blaze.setServer(new BlazeServer(args, BlazeStart.class.newInstance()));
	}
}
