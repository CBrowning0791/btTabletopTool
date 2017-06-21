import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import	javax.swing.*;

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
	JButton diceRollBtn;
	JTextField diceRollField;
	JPanel dicePanel;
	Random rand = new Random();
	int diceRoll;
	public toolGui ()
	{
		///////////Creating everything///////////
		mainFrame = new JFrame("Battletech Tabletop Tool");
		diceRollBtn = new JButton("Roll Dice");
		diceRollField = new JTextField(25);
		dicePanel = new JPanel(new FlowLayout());
		///////////adding stuff to various panels///////////
		dicePanel.add(diceRollField);
		dicePanel.add(diceRollBtn);
		mainFrame.add(dicePanel, BorderLayout.NORTH);
		///////////making things visible///////////
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(500,500);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
		///////////adding actionListeners///////////
		diceRollBtn.setActionCommand("ROLL");
		diceRollBtn.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e)
	{
		System.out.println("Action Received: " +  e.getActionCommand()); //Debugging statement
		switch(e.getActionCommand())
		{
			case "ROLL":    diceRoll = rand.nextInt(6)+ 1;
							diceRoll += rand.nextInt(6)+ 1;
							diceRollField.setText(Integer.toString(diceRoll));
							break;
		}
	}
}