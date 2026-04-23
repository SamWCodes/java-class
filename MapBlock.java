import java.util.ArrayList;

public class MapBlock
{

	private String title;
	private String description;
	private int N;
	private int S;
	private int E;
	private int W;
	private String yesMsg;
	private String noMsg;
	
	public static int count;
	
	ArrayList<Item> itemsHere = new ArrayList<Item>();
	
	public MapBlock()  //constructor (use these for all the X's or walls to prevent people from entering)
	{
		title = "VOID";
		description = "VOID";
		N = 0;
		S = 0;
		E = 0;
		W = 0;
		yesMsg = "NA";
		noMsg = "NA";
	}
	
	public MapBlock(String t, String d, int n, int s, int e, int w, String yes, String no) //constructor that sets data equal to header
	{
		title = t;
		description = d;
		N = n;
		S = s;
		E = e;
		W = w;
		yesMsg = yes;
		noMsg = no;
	}
	
	public boolean go(Direction d, Character c)
	{
		int doorValue = 0;
		
		switch(d)
		{
		case Direction.NORTH:
			doorValue = N;
			break;
		case Direction.SOUTH:
			doorValue = S;
			break;
		case Direction.EAST:
			doorValue = E;
			break;
		case Direction.WEST:
			doorValue = W;
			break;
		}
		
		if(doorValue == 1)
		{
			return true;
		}
		
		if(doorValue == 0)
		{
			if(c instanceof Player)
			{
				if(noMsg.equalsIgnoreCase("NA"))
				{
					System.out.println("You cannot cross");
				}
				else
				{
					System.out.println(noMsg);
				}
			}
			
			return false;
			
		}
		
		if(c.hasItem(doorValue))
		{
			if(c instanceof Player && !yesMsg.equalsIgnoreCase("NA"))
			{
				System.out.println(yesMsg);
			}
			
			return true;
		}
		
		if(c instanceof Player)
		{
			if(noMsg.equalsIgnoreCase("NA"))
			{
				System.out.println("You cannot cross");
			}
			else
			{
				System.out.println(noMsg);
			}
		}
		
		return false;
	}
	
	//getters
	public String getTitle()
	{
		return title;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public int getN()
	{
		return N;
	}
	
	public int getS()
	{
		return S;
	}
	
	public int getE()
	{
		return E;
	}
	
	public int getW()
	{
		return W;
	}
	
	@Override
	public String toString()
	{
		return String.format("\n%s\n%s",title,description);
	}

	public void showItems()
	{
		for (int x = 0; x < itemsHere.size(); x++)
		{
			System.out.println("There is a " + itemsHere.get(x).getName() + " here.");
		}
		
	}
	public Item removeItem(String i)
	{
		for(int x = 0; x < itemsHere.size(); x++)
		{
			if(itemsHere.get(x).getName().equalsIgnoreCase(i))
			{
				return itemsHere.remove(x);
			}
		}
		return null;
	}
}
