package crappybird;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.*;
import java.io.*;

public class ScrollingBar
{
	private int width, height;
	private int x;
	private int x2;
	private double g;
	private double dt;
	private double vx;
	private Image bar;
	public ScrollingBar(int width, int height)
	{
		this.width = width;
		this.height = height;
		x = 0;
		x2 = width - 11;
		dt = .1;
		vx = -40;
		try 
		{    
			bar = ImageIO.read(new File("./background bar.png"));
		} 
		catch (IOException e) 
		{
			e.getStackTrace();
		}
	}
	public void resetBar()
	{
		x = 0;
		x2 = width - 11;
	}
	public void draw(Graphics g)
	{
		g.drawImage(bar, x, height - 100, null);
		g.drawImage(bar, x2, height - 100, null);
		
	}
	public void move()
	{
		x += vx * dt;
		x2 += vx * dt;
		if(x2 + 11 == 0)
		{
			x = width - 11;
		}
		if(x + 11 == 0)
		{
			x2 = width - 11;
		}
	}
}
