import java.util.Scanner;

public class Player extends Character
{
	
	Scanner scan = new Scanner(System.in);
	
	public Player(String n, String d, int x, int y)
	{
		super(n,d,x,y,true,true);
		
		//System.out.println("Player " + name + " Created");
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
		String obj = data[1]; //other words after command
		
		switch(command)
		{
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
			
			
			break;
		case 'Q': //Quit Game
			return false;
			
		default:
			System.out.println("Not a Valid Command");
			break;
		}
		
		return true;
	}
	
	private String prompt()
	{
		System.out.print("Where would you like to move? ");
		return scan.nextLine();
	}
	
}
