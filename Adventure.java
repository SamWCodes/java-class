import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Adventure
{

	public static void main(String[] args)
	{
		int maxX = 10;
		int maxY = 10;
		int numChars = 20;
		
		Boolean playing = true;
		
		String mapFile = "../data/map.csv";
		String charFile = "../data/characters.csv";
		String itemFile = "../data/items.csv";
		
		Map map = new Map(maxX,maxY,mapFile);
		Character[] profile = new Character[numChars];
		
		//System.out.println(map); Commenting this out removes the map from showing
		
		//System.out.println(MapBlock.count); // see if this needs to be added or not
		
		
		//this will change later (change for your map)
		
	//	int xpos = 0;
	//	int ypos = 0;
		
	//	System.out.println(map.map[xpos][ypos]);
		
		Character.initChars(profile,charFile);
		Item.LoadItems(itemFile, map, profile);
		
		Player player = (Player)profile[0];
		
		//Start Game
		while(playing)
		{
			playing = player.move(profile, map); //did we die or win or just quit???
		}
		
		
		System.out.println("Game Over");
		}
	

}
