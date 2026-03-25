import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Map
{
	int maxX; //max size of map
	int maxY; //max size of map
	
	MapBlock[][] map; 

public Map(int x, int y, String fileName)
{
	maxX = x;  //set MaxX
	maxY = y;  //set MaxY
	
	map = new MapBlock[x][y];  //initialize map
	
	for(int X = 0; X < maxX; X++) //fill map blocks
	{
		
		for(int Y = 0; Y < maxY; Y++) 
		{
			map[X][Y] = new MapBlock(); //fill map blocks
		}
	}
	
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
			String title = data[2];
			String description = data[3];
			int n = Integer.parseInt(data[4]);
			int s = Integer.parseInt(data[5]);
			int e = Integer.parseInt(data[6]);
			int w = Integer.parseInt(data[7]);
			
			map[xpos][ypos] = new MapBlock(title,description,n,s,e,w);
			
		}
		
		br.close(); //closes the file error catch
	}
	catch(IOException e) //reads out the file error
	{
		System.out.println("File Error: " + fileName);
	}
	
}

	@Override
	public String toString()
	{
		
		char full = '\u2588';
		char empty = '\u2591';
		
		String out = "MAP:\n";
		
		
		
		for(int y = 0; y < maxY; y++)
		{
			for(int x = 0; x < maxX; x++) 
			{
				if(map[x][y].getTitle().equals("VOID"))
				{
					for(int c = 1; c <= 3; c++)
					{
						out += empty;
					}
					
				}
				else
				{
					for(int c = 1; c <= 3; c++)
					{
						out += full;
					}
					
				}
			}
			out += "\n";
		}
		return out;
	}
	


}
