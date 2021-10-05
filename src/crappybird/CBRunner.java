package crappybird;
import javax.swing.JFrame;

public class CBRunner
{

	public static void main(String[] args)
	{
		//String currentDirectory = System.getProperty("user.dir");
		//System.out.println("The current working directory is " + currentDirectory);
		int width = 1200;
		int height = 800;
		CBPanel panel = new CBPanel(width, height);
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.setSize(width, height);
		frame.setVisible(true);	
	}
}
