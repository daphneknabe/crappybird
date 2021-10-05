package crappybird;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;

public class Score
{
	private Font font;
	private Color color;
	private String score;
	public Score()
	{
		font = new Font("Verdana", Font.BOLD, 40);
		color = Color.black;
		score = "Score: " ;
	}
	public void draw(Graphics g, int scoreNum)
	{
		g.setColor(color);
		g.setFont(font);
		g.drawString(score + scoreNum, 550, 60);
	}
	
}
