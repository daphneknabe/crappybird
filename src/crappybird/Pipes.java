package crappybird;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Image;
import javax.imageio.*;
import java.io.*;

public class Pipes
{
	private int width, height;
	private int x, y;
	private double g;
	private double dt;
	private double vx;
	private int rectHeight;
	private int imageHeight = 643;
	private int rectWidth;
	private Image pipeBottom;
	private Image pipeTop;
	public Pipes(int width, int height)
	{
		this.width = width;
		this.height = height;
		x = width;
		y = 0;
		dt = .1;
		vx = -40;
		rectHeight = (int)(Math.random() * 551);
		rectWidth = 100; 
		try 
		{    
			pipeBottom = ImageIO.read(new File("./pipe bottom.png"));
			pipeTop = ImageIO.read(new File("./pipe top.png"));
		} 
		catch (IOException e) 
		{
			e.getStackTrace();
		}
	}
	public int getX()
	{
		return x;
	}
	public void setX(int x)
	{
		this.x = x;
	}
	public void setRectHeight()
	{
		rectHeight = (int)(Math.random() * 551);
	}
	public int getPassed()
	{
		return x + rectWidth;
	}
	public Rectangle getBoundsTop()
	{
		return new Rectangle(x, y, rectWidth, rectHeight);
	}
	public Rectangle getBoundsBottom()
	{
		return new Rectangle(x, rectHeight + 210, rectWidth, height - 210 - rectHeight);
	}
	public void draw(Graphics g)
	{
		g.drawImage(pipeTop, x - 12, rectHeight - imageHeight, null);
		g.drawImage(pipeBottom, x - 12, rectHeight + 210, null);
		
	}
	
	public void move()
	{
		x += vx * dt;
	}
}
