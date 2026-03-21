
public class Immoveable extends Character
{
	public Immoveable(String n, String d, int x, int y, boolean c)
	{
		
		super(n,d,x,y,false,c);
		
		//System.out.println("Moveable " + name + " Created");
	}
	
	@Override
	public boolean move(Character[] profile, Map m)
	{
		boolean stillAlive = true;
		//nothing here - character will stay here
		return stillAlive;
	}
}
