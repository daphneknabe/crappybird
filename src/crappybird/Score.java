package crappybird;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;

import javax.imageio.ImageIO;

public class Score
{
	private Image[] scoreArray;
	private Image restart;
	private Image depressedRestart;
	private Image bestText;
	private Image scoreText;
	private Image gameOver;
	private int[] digits;
	public Score()
	{
		digits = new int[3];
		scoreArray = new Image[10];
		try 
		{
			gameOver = ImageIO.read(new File("./game over.png"));
			scoreText = ImageIO.read(new File("./scoretext.png"));
			bestText = ImageIO.read(new File("./best.png"));
			restart = ImageIO.read(new File("./restartbutton.png"));
			depressedRestart = ImageIO.read(new File("./depressedrestart.png"));
			scoreArray[0] = ImageIO.read(new File("./zero.png"));
			scoreArray[1] = ImageIO.read(new File("./oen.png"));
			scoreArray[2] = ImageIO.read(new File("./two.png"));
			scoreArray[3] = ImageIO.read(new File("./three.png"));
			scoreArray[4] = ImageIO.read(new File("./four.png"));
			scoreArray[5] = ImageIO.read(new File("./five.png"));
			scoreArray[6] = ImageIO.read(new File("./six.png"));
			scoreArray[7] = ImageIO.read(new File("./seven.png"));
			scoreArray[8] = ImageIO.read(new File("./eight.png"));
			scoreArray[9] = ImageIO.read(new File("./nine.png"));
		} 
		catch (IOException e) 
		{
			e.getStackTrace();
		}
	}
	public int scoreDigits(int scoreNum)
	{
		System.out.println(scoreNum);
		int digitNum = 0;
		if (scoreNum > 0)
		{
			while (scoreNum > 0)
			{		
				int digit = scoreNum % 10;
				scoreNum /= 10;
				digits[digitNum] = digit;
				digitNum++;
				System.out.println(digit);
			}
		}
		else
		{
			digitNum = 1;
			digits[0] = 0;
		}
		
		return digitNum;
	}
	public void draw(Graphics g, int scoreNum, int x1, int x2, int x3, int y)
	{
		int digitNum = scoreDigits(scoreNum);
		if(digitNum == 1)
		{ 
			g.drawImage(scoreArray[digits[0]], x1, y, null);
		}
		else if(digitNum == 2)
		{
			g.drawImage(scoreArray[digits[0]], x1, y, null);
			g.drawImage(scoreArray[digits[1]], x2, y, null);
		}
		else if(digitNum == 3)
		{
			g.drawImage(scoreArray[digits[0]], x1, y, null);
			g.drawImage(scoreArray[digits[1]], x2, y, null);
			g.drawImage(scoreArray[digits[2]], x3, y, null);
		}
		else
		{
			g.setColor(Color.black);
			g.drawString("Well, ya won.", 550, 60);
		}
	}
	public void drawBest(Graphics g, int scoreNum, int bestScore)
	{
		g.drawImage(scoreText, 400, 60, null);
		draw(g, scoreNum, 750, 705, 660, 60);
		g.drawImage(bestText, 430, 150, null);
		draw(g, bestScore, 750, 705, 660, 150);
		g.drawImage(gameOver, 250, 300, null);
		g.drawImage(restart, 550, 475, null);
		
	}
	public void drawDepressed(Graphics g)
	{
		g.drawImage(depressedRestart, 550, 475, null);
	}
	
	
}
