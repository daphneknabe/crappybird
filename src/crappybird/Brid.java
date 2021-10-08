package crappybird;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.imageio.*;
import java.io.*;

public class Brid
{
	private int width, height;
	private int x, y;
	private int d;
	private double g;
	private double dt;
	private double vy;
	private Color color;
	Image wingsUp;
	Image wingsDown;
	private int rectWidth, rectHeight;
	public Brid(int width, int height)
	{
		this.width = width;
		this.height = height;
		vy = 0.0;
		d = 60;
		dt = .2;
		g = 12;
		x = (width - d)/ 2;
		y = (height - d) / 2;
		color = Color.yellow;
		try 
		{                
			wingsUp = ImageIO.read(new File("./froggywingsupfirst.png"));
			wingsDown = ImageIO.read(new File("./froggywingsdownfirst.png"));
		} 
		catch (IOException e) 
		{
			e.getStackTrace();
		}
	}
	public void drawUp(Graphics g)
	{
		/*
		g.setColor(color);
		g.fillOval(x, y, d, d);
		g.setColor(Color.red);
		g.fillRect(x + 2 * d / 3, y + d / 2, d / 2, d / 4);
		g.setColor(Color.black);
		g.fillOval(x + d / 2, y + d / 3, d / 4, d / 4);
		*/
		g.drawImage(wingsUp, x, y, null);
	}
	public void drawDown(Graphics g)
	{
		g.drawImage(wingsDown, x, y, null);
	}
	public void move()
	{
		vy += g * dt;
		y += vy * dt;
	}
	public void setY(int y)
	{
		this.y = y;
	}
	public void setVy(int vy)
	{
		this.vy = vy;
	}
	public int getX()
	{
		return x + d / 2;
	}
	public int getY()
	{
		return y + d;
	}
	public Rectangle getBounds()
	{
		return new Rectangle(x, y, d, d);
	}	
	public void flap()
	{
		vy = -50;
	}
}