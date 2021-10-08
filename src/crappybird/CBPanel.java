package crappybird;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Font;
import javax.imageio.*;
import java.io.*;
	
public class CBPanel extends JPanel implements Runnable, MouseListener
{
	private int width, height;
	private Thread thread;
	private Brid brid;
	private Pipes[] pipesList;
	private boolean alive;
	private boolean buttonPressed;
	private Score sc;
	private Menu menu;
	private ScrollingBar bar;
	private int score;
	private int bestScore;
	private int flapCount;
	private boolean gameStart;
	private Background background;
	Image splash;
	Image death;
	Image city;
	public CBPanel(int width, int height)
	{
		this.width = width;
		this.height = height;
		brid = new Brid(width, height);
		buttonPressed = false;
		background = new Background(width, height);
		score = 0;
		menu = new Menu();
		bar = new ScrollingBar(width, height);
		flapCount = 0;
		bestScore = 0;
		sc = new Score();
		alive = false;
		gameStart = false;
		pipesList = new Pipes[3];
		pipesList[0] = new Pipes(width, height);
		pipesList[1] = new Pipes(3 * width / 2, height);
		pipesList[2] = new Pipes(2 * width, height);
		thread = new Thread(this);
		thread.start();
		setBackground(Color.cyan);
		addMouseListener(this);
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
		background.draw(g);
		if(alive && gameStart)
		{
			brid.move();
			if(flapCount < 5)
			{
			brid.drawUp(g);
			flapCount++;
			}
			else if(flapCount >= 5 && flapCount < 10)
			{
				brid.drawDown(g);
				flapCount++;
			}
			else
			{
				brid.drawDown(g);
				flapCount = 0;
			}
			for(int index = 0; index < pipesList.length; index++)
			{
				Pipes pipes = pipesList[index];
				pipes.move();
				pipes.draw(g);
				if(pipes.getPassed() < 0)
				{
					pipes.setX(3 * width / 2);
					pipes.setRectHeight();
				}
				if(brid.getBounds().intersects(pipes.getBoundsTop()) || brid.getBounds().intersects(pipes.getBoundsBottom()) || brid.getY() < 0 || brid.getY() > height - 100)
				{
					alive = false;
				}
				if(brid.getX() == pipes.getPassed())
				{
					score++;
				}	
			}
			bar.move();
			bar.draw(g);
			sc.draw(g, score, 600, 555, 510, 60);	
		}
		else if(!alive && gameStart)
		{
			bar.draw(g);
			if(score > bestScore)
			{
				bestScore = score;
			}
			sc.drawBest(g, score, bestScore);
			if(buttonPressed == true)
			{
				sc.drawDepressed(g);
			}
		}
		else if(!alive && !gameStart)
		{
			menu.draw(g);
			brid.drawUp(g);
			bar.draw(g);
			if(buttonPressed == true)
			{
				menu.drawDepressed(g);
			}	
		}	
	}
	@Override
	public void mouseClicked(MouseEvent e)
	{
		
	}
	@Override
	public void mousePressed(MouseEvent e)
	{
		int xpos = e.getX();
		int ypos = e.getY();
		buttonPressed = false;
		//show indentation of the correct button
		if(!alive)
		{
			if(xpos >= 550 && ypos >=475)
			{
				if(xpos <= 650 && ypos <=525)
				{
					buttonPressed = true;
				}
			}
		}
	}
	@Override
	public void mouseReleased(MouseEvent e)
	{
		int xpos = e.getX();
		int ypos = e.getY();
		//check if game has started and if player releases on the correct button)
		if(e.getButton() == 1 && alive && gameStart)
		{
			brid.flap();
		}
		else if(e.getButton() == 1 && !alive && !gameStart && buttonPressed)
		{
			gameStart = true;
			alive = true;
		}
		if(gameStart && e.getButton() == 1 && !alive && buttonPressed)
		{
			for(int i = 0; i < pipesList.length; i++)
			{
				pipesList[i].setX((i + 2) * width / 2);
				pipesList[i].setRectHeight();
			}
			bar.resetBar();
			alive = true;
			brid.setY((height) / 2);
			brid.setVy(0);
			score = 0;
		}
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