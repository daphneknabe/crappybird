package crappybird;

import java.awt.Image;
import javax.imageio.*;
import java.io.*;
import java.awt.Graphics;


public class Menu
{
	private Image start;
	private Image depressedStart;
	private Image logo;
	
	public Menu()
	{
		try
		{
			logo = ImageIO.read(new File("./froggy bird.png"));
			start = ImageIO.read(new File("./playbutton.png"));
			depressedStart = ImageIO.read(new File("./depressedstart.png"));
		}
		catch(IOException e) 
		{
			e.printStackTrace();
		}
	}
	public void draw(Graphics g)
	{
		g.drawImage(logo, 200,200, null);
		g.drawImage(start, 550, 475, null);
	}
	public void drawDepressed(Graphics g)
	{
		g.drawImage(depressedStart, 550, 475, null);
	}
	
}
