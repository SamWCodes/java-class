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
	*/
	int hitPoints;
	int strength;
	int intelligence;
	int dexterity;
	int armor;
	
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
	
	protected Character findCombativeTarget(Character[] profile)
	{
		for(int x=1; x <= count; x++)
		{
			if(profile[x] != null &&
					profile[x].isAlive() &&
					profile[x].combative &&
					isHere(profile[x]))
			{
				return profile[x];
			}
		}
		return null;
	}
	
	protected boolean attackCombativeTargets(Character[] profile, Map m)
	{
		boolean attackedAnything = false;
		
		for(int x = 1; x <= count; x++)
		{
			if(profile[x] != null &&
					profile[x].isAlive() &&
					profile[x].combative &&
					isHere(profile[x]))
			{
				attack(profile[x], m);
				attackedAnything = true;
			}
		}
		return attackedAnything;
	}
	
	protected boolean isAlive()
	{
		return hitPoints > 0;
	}
	
	protected void dropAllItems(MapBlock here)
	{
		while(inventory.size() > 0)
		{
			here.itemsHere.add(inventory.remove(0));
		}
	}
	
	protected void kill(Map m)
	{
		name = "Body of " + name;
		moveable = false;
		combative = false;
		dropAllItems(m.map[xpos][ypos]);
	}
	
	//constructor
	public Character()
	{
		
	}
	
	public Character(String n, String d, int x, int y, boolean m, boolean c, int hp, int s, int i, int dex, int a)
	{
		name = n;
		description = d;
		xpos = x;
		ypos = y;
		moveable = m;
		combative = c;
		hitPoints = hp;
		strength = s;
		intelligence = i;
		dexterity = dex;
		armor = a;
		
		//System.out.println(name + " Created"); 
	}
	
	public void getItem(String itemName, MapBlock here) 
	{
		//remove item from mapblock and add to our inventory
		
		Item i = here.removeItem(itemName);
		
		if(i != null)
		{
			inventory.add(i);
		}
	}
	
	public void dropItem(String itemName, MapBlock here)
	{
		for(int x = 0; x < inventory.size(); x++)
		{
			if(inventory.get(x).getName().equalsIgnoreCase(itemName))
			{
				Item dropped = inventory.remove(x);
				here.itemsHere.add(dropped);
				System.out.println("You no longer have the " + dropped.getName() + ".");
				return;
				
			}
		}
	
	System.out.println("You no longer have " + itemName + ".");
	
	
	}
	
	protected boolean isAlive1()
	{
		return hitPoints > 0;
	}
	
	protected Character findTarget(Character[] profile, String targetName)
	{
		for(int x = 1; x <= count; x++)
		{
			if(profile[x] != null &&
				profile[x].isAlive() &&
				isHere(profile[x]) &&
				profile[x].name.equalsIgnoreCase(targetName))
			{
				return profile[x];
			}
		}
		return null;
	}
	
	protected void takeDamage(int damage)
	{
		hitPoints -= damage;
		
		if(hitPoints < 0)
		{
			hitPoints = 0;
		}
	}
	
	protected void attack(Character target, Map m)
	{
		int hitRoll = Die.roll(20);
		
		System.out.println(name + " attacks " + target.name + ".");
		System.out.println("Hit roll: " + hitRoll + " vs armor " + target.armor);
		
		if(hitRoll >= target.armor)
		{
			int damage;
			
			if (strength > 1)
			{
				damage = Die.roll(strength);
			}
			else
			{
				damage = 1;
			}
			
			System.out.println("Hit for " + damage + " damage.");
			
			target.hitPoints -= damage;
			
			if(target.hitPoints <= 0)
			{
				target.hitPoints = 0;
				target.kill(m);;
				System.out.println(target.name + " is dead.");
			}
			else
			{
				System.out.println(target.name + " has " + target.hitPoints + " hit points left.");
			}
		}
		else
		{
			System.out.println(name + " misses " + target.name + ".");
		}
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
				int hitPoints = Integer.parseInt(data[6]);
				int strength = Integer.parseInt(data[7]);
				int dexterity = Integer.parseInt(data[8]);
				int intelligence = Integer.parseInt(data[9]);
				int armor = Integer.parseInt(data[10]);
				int pType = Integer.parseInt(data[11]);
				boolean combative = (Integer.parseInt(data[12]) > 0)?true:false;
				
				switch(pType)
				{
				case 0:
					//Immoveable
					Immoveable i = new Immoveable(name,description,xpos,ypos,combative, hitPoints, strength, dexterity, intelligence, armor);
					profile[++count] = i;
					break;
				case  1:
					//Moveable
					Moveable m = new Moveable(name,description,xpos,ypos,combative, hitPoints, strength, dexterity, intelligence, armor);
					profile[++count] = m;
					break;
				case 2:
					//Player
					Player p = new Player(name,description,xpos,ypos, hitPoints, strength, dexterity, intelligence, armor);
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
