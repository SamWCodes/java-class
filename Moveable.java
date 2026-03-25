
public class Moveable extends Character
{
	public Moveable(String n, String d, int x, int y, boolean c, int hp, int s, int i, int dex, int a)
	{
		
		super(n,d,x,y,true,c,hp,s,i,dex,a);
		
		//System.out.println("Moveable " + name + " Created");
	}
	
	@Override
	public boolean move(Character[] profile, Map m)
	{
		
		boolean stillAlive = true;
		
		MapBlock here = m.map[xpos][ypos];
		
		Die d = new Die();
		switch(d.roll(4))
		{
		case 1:
			//North
			if(here.go(Direction.NORTH)) 
			{
				ypos--;
			}
			
			break;
		case 2:
			//South
			if(here.go(Direction.SOUTH)) 
			{
				ypos++;
			}
			break;
		case 3:
			//East
			if(here.go(Direction.EAST)) 
			{
				xpos++;
			}
			break;
		case 4:
			//West
			if(here.go(Direction.WEST)) 
			{
				xpos--;
			}
			break;
		default:
			System.out.println("Should never get here");
			break;
			
		}
		
		return stillAlive;
		
	}
}

