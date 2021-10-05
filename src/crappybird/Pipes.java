package crappybird;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Pipes
{
	private int width, height;
	private int x, y;
	private double g;
	private double dt;
	private double vx;
	private Color color;
	private int rectHeight;
	private int rectWidth;
	public Pipes(int width, int height)
	{
		this.width = width;
		this.height = height;
		x = width;
		y = 0;
		dt = .1;
		vx = -40;
		rectHeight = (int)(Math.random() * 501);
		rectWidth = 80; 
		color = Color.green;
	}
	public int getX()
	{
		return x;
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
		g.setColor(color);
		g.fillRect(x, y, rectWidth, rectHeight);
		g.fillRect(x, rectHeight + 210, rectWidth, height - 210 - rectHeight);
	}
	public void move()
	{
		x += vx * dt;
	}
}
