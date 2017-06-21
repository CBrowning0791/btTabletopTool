import java.io.*;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
public class btTabletopTool
{
	public static void main(String args[])
	{
		toolGui tg;
		System.out.println("Reactor Online. Sensors Online. Weapons Online.\n");
		tg = new toolGui();
		System.out.println("All systems nominal.");
	}
}

class toolGui extends JFrame implements ActionListener
{
	JFrame mainFrame;

	public toolGui ()
	{
		mainFrame = new JFrame("Battletech Tabletop Tool");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e)
	{
		
	}
}