import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Item
{
	
	//need to create my own code down below - do not AI generate, you can get help with it 
	//but you need to create your own code instead of AI

	//public static int masterKeyID = 2;
	
	private String name;
	private String desc;
	private short offense;
	private short defense;
	private double value;
	
	//public int keyID;
	
	public Item()
	{
		
	}
	
	public Item(String n, String d, short of, short de, double v)
	{
		name = n;
		desc = d;
		offense = of;
		defense = de;
		value = v;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getDesc()
	{
		return desc;
	}
	
	public short getOffense()
	{
		return offense;
	}
	
	public short getDefense()
	{
		return defense;
	}
	
	public double getValue()
	{
		return value;
	}
	
	public static void LoadItems(String fileName, Map m, Character[] p)
	{

	try
	{
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
	
	//Need accessors to get data
	
	//need accessors to retrieve data
	
	String line;
	String splitBy = ",";
	String[] data;

	while ((line = br.readLine()) != null)
		
	{
		data = line.split(splitBy);
		
		int a = Integer.parseInt(data[0]);
		int b = Integer.parseInt(data[1]);
		String name = data[2];
		String desc = data[3];
		short o = Short.parseShort(data[4]);
		short d = Short.parseShort(data[5]);
		double v = Double.parseDouble(data[6]);
		
		Item i = new Item(name,desc,o,d,v);
		
		if(a < 0)
		{
			//character b gets item
			p[b].inventory.add(i);
			//p[b].armWith(i);
		}
		else
		{
			//put item on may at x,y
			int x = a;
			int y = b;
			m.map[x][y].itemsHere.add(i);
		}
	}
	
	br.close();
	}
	catch(IOException e)
	{
	    System.out.println("File Error: " + fileName);
	}
	
	}
	
}
