package crappybird;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Background
{
	private Image city;
	private Image bar;
	private int width, height;
	public Background(int width, int height)
	{
		this.width = width;
		this.height = height;
		try 
		{
			city = ImageIO.read(new File("./city.png"));
			bar = ImageIO.read(new File("./background bar.png"));
		} 
		catch (IOException e) 
		{
			e.getStackTrace();
		}
	}
	public void draw(Graphics g)
	{
		g.drawImage(city, 0, 0, null);
	//	g.drawImage(bar, 0, height - 100, null);
	}
}
