import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Character
{
	static int count = 0;
	
	String name;
	String description;
	int xpos;
	int ypos;
	
	/*
	String race;
	String cClass;
	int hitPoints;
	int strength;
	int intelligence;
	int dexterity;
	int armor;
	*/
	boolean moveable;
	boolean combative;
	
	
	//future inventory - code below is temporary
	ArrayList<Item> inventory = new ArrayList<Item>();
	
	//Item[] inventory = new Item[InvSize]();
	
	//end temporary code
	
	public abstract boolean move(Character[] profile, Map m); //write our own move function
	
	protected static void moveNPCs(Character[] profile,Map m)
	{
		for(int x = 1; x <= count; x++)
		{		
			profile[x].move(profile, m);
		}
	}
	
	protected boolean isHere(Character c)
	{
		if((xpos == c.xpos) && (ypos == c.ypos))
		{
			return true;
		}
		return false;
	}
	
	protected void showNPCs(Character[] profile)
	{
		for(int x = 1; x <= count; x++)
		{
			if (profile[0].isHere(profile[x]))
			{
				System.out.println(profile[x].name + " is here.");
			}
		}
	}
	
	//constructor
	public Character()
	{
		
	}
	
	public Character(String n, String d, int x, int y, boolean m, boolean c)
	{
		name = n;
		description = d;
		xpos = x;
		ypos = y;
		moveable = m;
		combative = c;
		
		//System.out.println(name + " Created"); 
	}
	
	public void getItem(String itemName, MapBlock here) 
	{
		//remove item from mapblock and add to our inventory
		inventory.add(here.removeItem(itemName));
	}
	
	public static void initChars(Character[] profile, String fileName)
	{
		try //catches file errors
		{
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			
			String line;
			String splitBy = ",";
			String[] data;
			
			while ((line = br.readLine()) != null)
			{
				data = line.split(splitBy);
				
				int xpos = Integer.parseInt(data[0]);
				int ypos = Integer.parseInt(data[1]);
				String name = data[2];
				String description = data[3];
				/*
4 - race;
5 - class;
6 - hitPoints;
7 - strength;
8 - dexterity;
9 - intelligence;
10 - armor;
11 - pType (player/moveable/immoveable)
12 - combative;
				 */
				
				int pType = Integer.parseInt(data[11]);
				boolean combative = (Integer.parseInt(data[12]) > 0)?true:false;
				
				switch(pType)
				{
				case 0:
					//Immoveable
					Immoveable i = new Immoveable(name,description,xpos,ypos,combative);
					profile[++count] = i;
					break;
				case  1:
					//Moveable
					Moveable m = new Moveable(name,description,xpos,ypos,combative);
					profile[++count] = m;
					break;
				case 2:
					//Player
					Player p = new Player(name,description,xpos,ypos);
					profile[0] = p;
					break;
				default:
					System.out.println("Bad Character Type " + pType);
					break;
				}
				
			}
			
			br.close(); //closes the file error catch
			
			/*Code below here is temporary, just for example purposes on what to do
			
			Item i = new Item();
			
			profile[0].inventory.add(i);
			profile[0].inventory.add(profile[2].inventory.remove(3)); //remove item from player when picked up and add to inventory.
			profile[0].inventory.get(3) //array list
			profile[0].inventory[3] //array
			
			*/
			
			// end temporary code
		}
		catch(IOException e) //reads out the file error
		{
			System.out.println("File Error: " + fileName);
		}
		
	}
}
