package crappybird;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.Font;
import javax.imageio.*;
import java.io.*;
	
public class CBPanel extends JPanel implements Runnable, MouseListener
{
	private int width, height;
	private Thread thread;
	private Brid brid;
	private int pipeNumber;
	private ArrayList<Pipes> pipesList;
	private boolean alive;
	private Score sc;
	private int score;
	private int bestScore;
	private boolean gameStart;
	Image splash;
	Image death;
	private Font stringFont; 
	public CBPanel(int width, int height)
	{
		this.width = width;
		this.height = height;
		brid = new Brid(width, height);
		score = 0;
		bestScore = 0;
		sc = new Score();
		alive = false;
		gameStart = false;
		pipesList = new ArrayList<Pipes>();
		pipeNumber = pipesList.size();
		thread = new Thread(this);
		thread.start();
		setBackground(Color.cyan);
		addMouseListener(this);
		try 
		{                
			splash = ImageIO.read(new File("./splash screen.png"));
			death = ImageIO.read(new File("./death.png"));
		} 
		catch (IOException e) 
		{
			e.getStackTrace();
		}
	}
	
	
	@Override
	public void run()
	{
		while(true)
	   	{
	   		try
	   		{
	   			Thread.sleep(10);
	   		} 
	   		catch (InterruptedException e)
	   		{ 
	   			System.out.println("Thread stopped");
	   			thread.interrupt();
	   			return;
	   		}
	   		repaint();
	   	}
	}
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		//move the ball and then redraw it
		if(alive && gameStart)
		{
			brid.move();
			brid.draw(g);
			pipeNumber = pipesList.size();
			if (pipeNumber == 0)
			{
				Pipes pipe = new Pipes(width, height);
				pipesList.add(pipe);
				pipeNumber = pipesList.size();
			}
			for(int index = 0; index < pipesList.size(); index++)
			{
				Pipes pipes = pipesList.get(index);
				pipes.move();
				pipes.draw(g);
				if(pipes.getX() == width / 2)
				{
					Pipes pipe = new Pipes(width, height);
					pipesList.add(pipe);
				}
				else if(pipes.getPassed() < 0)
				{
					pipesList.remove(index);
				}
				if(brid.getBounds().intersects(pipes.getBoundsTop()) || brid.getBounds().intersects(pipes.getBoundsBottom()) || brid.getY() < 0 || brid.getY() > height)
				{
					System.out.println("Collision");
					alive = false;
					while(pipesList.size() > 0)
					{
						pipesList.remove(0);
						System.out.println("post collide: " + pipesList.size());
					}
				}
				if(brid.getX() == pipes.getPassed())
				{
					score++;
				}
				
			}
			sc.draw(g, score);
		}
		else if(!alive && gameStart)
		{
			g.drawImage(death, 0, 0, null);
			Font font = new Font("Verdana", Font.BOLD, 40);
			g.setFont(font);
			g.drawString("Score: " + score, 500, 60);
		if(score >= bestScore)
		{
			bestScore = score;				}
			g.drawString("Best score: " + bestScore, 500, 120);
		}
		else if(!alive && !gameStart)
		{
			g.drawImage(splash, 0, 0, null);
			brid.draw(g);
		}
		
	}
		@Override
		public void mouseClicked(MouseEvent e)
		{
			if(e.getButton() == 1 && alive && gameStart)
			{
				brid.flap();
			}
			else if(e.getButton() == 1 && !alive && !gameStart)
			{
				gameStart = true;
				alive = true;
			}
			else if(e.getButton() == 1 && !alive && gameStart)
			{
				alive = true;
				brid.setY((height) / 2);
				brid.setVy(0);
				score = 0;
			}
			
		}
	@Override
	public void mousePressed(MouseEvent e)
	{
		
	}
	@Override
	public void mouseReleased(MouseEvent e)
	{
		
	}
	@Override
	public void mouseEntered(MouseEvent e)
	{
		
	}
	@Override
	public void mouseExited(MouseEvent e)
	{
		
	}
	
}