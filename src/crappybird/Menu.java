package crappybird;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Menu
{
	private int width, height;
	private JLabel wIcon;
	public Menu(int width, int height)
	{
		BufferedImage flappyLogo;
		try 
		{
			flappyLogo = ImageIO.read(this.getClass().getResource("eight.png"));
			wIcon = new JLabel(new ImageIcon(flappyLogo));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	public JLabel getLabel()
	{
		return wIcon;
	}
	
}
