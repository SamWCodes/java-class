import java.util.Scanner;

public class Player extends Character
{
	
	Scanner scan = new Scanner(System.in);
	
	public Player(String n, String d, int x, int y, int hp, int s, int i, int dex, int a)
	{
		super(n,d,x,y,true,true,hp,s,i,dex,a);
		
		//System.out.println("Player " + name + " Created");
	}
	
	@Override
	public void getItem(String itemName, MapBlock here) 
	{
		//remove item from mapblock and add to our inventory
		
		Item i = here.removeItem(itemName);
		
		if(i != null)
		{
			inventory.add(i);
			System.out.println("You now have the " + itemName);
		}
		else
		{
			System.out.println("there is no " + itemName + " here.");
		}
	}
	
	@Override
	public boolean move(Character[] profile, Map m)
	{
		MapBlock here = m.map[xpos][ypos];
		
		System.out.println(here);
		here.showItems();
		
		Character.moveNPCs(profile,m);
		showNPCs(profile);
		
		String response = prompt();
		
		char command = response.toUpperCase().charAt(0);
		String[] data = response.split(" ",2);
		String obj = "";
		if(data.length > 1)
		{
			obj = data[1];//other words after command
		}
		
		switch(command)
		{
		case 'I': //need to write code to list inventory
			showItems();
			break;
		case 'N':
			if(here.go(Direction.NORTH)) 
			{
				ypos--;
			}
			else
			{
				System.out.println("The way is shut");
			}
			break;
		case 'S':
			if(here.go(Direction.SOUTH)) 
			{
				ypos++;
			}
			else
			{
				System.out.println("The way is shut");
			}
			break;
		case 'E':
			if(here.go(Direction.EAST)) 
			{
				xpos++;
			}
			else
			{
				System.out.println("The way is shut");
			}
			break;
		case 'W':
			if(here.go(Direction.WEST)) 
			{
				xpos--;
			}
			else
			{
				System.out.println("The way is shut");
			}
			break;
		case 'G': //Get Item
			System.out.println("Getting " + obj);
			getItem(obj, here);
			
			break;
		case 'D': //Drop Item
			
			//System.out.println("Dropping " + obj);  Can we use this line of code instead?
			//dropItem(obj, here); Can we use this line of code instead?
			
			
			if(obj.equals(""))
			{
				System.out.println("What item would you like to drop?");
			}
			else
			{
				Item dropped = dropItem(obj);
				if(dropped != null)
				{
					here.itemsHere.add(dropped);
					System.out.println("You dropped " + dropped.getName() + ".");
				}
			}
			
			//Need to fill this out still.
			break;
		case 'Q': //Quit Game
			return false;
			
		default:
			System.out.println("Not a Valid Command");
			break;
		}
		
		return true;
	}
	
	public void showItems()
	{
		System.out.println("Inventory");
		for (int x = 0; x < inventory.size(); x++)
		{
			System.out.println("There is a " + inventory.get(x).getName() + " here.");
		}
	}
	
	private String prompt()
	{
		System.out.print("Where would you like to move? ");
		return scan.nextLine();
	}
	
}
