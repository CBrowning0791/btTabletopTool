import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import	javax.swing.*;
import javax.swing.border.EmptyBorder;

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
	JButton weaponReferenceBtn;
	JTextField diceRollField;
	JPanel dicePanel;
	JPanel listPanel;
	JPanel topButtonPanel;
	Random rand = new Random();
	int diceRoll;
	JList rollList;
	DefaultListModel rollListEntries = new DefaultListModel();
	public toolGui ()
	{
		///////////Creating everything///////////

		mainFrame = new JFrame("Battletech Tabletop Tool");
		diceRollBtn = new JButton("Roll Dice");
		weaponReferenceBtn = new JButton("Weapon Sheet");
		diceRollField = new JTextField(10);
		dicePanel = new JPanel();
		dicePanel.setLayout(new BoxLayout(dicePanel, BoxLayout.Y_AXIS));
		listPanel = new JPanel(new FlowLayout());
		topButtonPanel = new JPanel(new FlowLayout());
		rollList = new JList(rollListEntries);
		JScrollPane listPane = new JScrollPane(rollList);

		///////////adding stuff to various panels///////////

		dicePanel.add(diceRollField);
		dicePanel.add(Box.createRigidArea(new Dimension(0,5)));
		dicePanel.add(diceRollBtn);
		listPanel.add(listPane);
		topButtonPanel.add(weaponReferenceBtn);
		mainFrame.add(dicePanel, BorderLayout.CENTER);
		mainFrame.add(listPanel, BorderLayout.LINE_END);
		mainFrame.add(topButtonPanel, BorderLayout.PAGE_START);

		///////////cleaning up sizing issues///////////

		diceRollField.setMaximumSize(new Dimension(Integer.MAX_VALUE, diceRollField.getPreferredSize().height));
		dicePanel.setBorder(new EmptyBorder(5,10,10,10));

		///////////making things visible///////////

		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(500,300);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);

		///////////adding actionListeners///////////

		diceRollBtn.setActionCommand("ROLL");
		diceRollBtn.addActionListener(this);
		weaponReferenceBtn.setActionCommand("WEAPON");
		weaponReferenceBtn.addActionListener(this);

	}

	public void actionPerformed(ActionEvent e)
	{
		System.out.println("Action Received: " +  e.getActionCommand()); //Debugging statement
		switch(e.getActionCommand())
		{
			case "ROLL":    diceRoll = rand.nextInt(6)+ 1;
							diceRoll += rand.nextInt(6)+ 1;
							diceRollField.setText(Integer.toString(diceRoll));
							rollListEntries.add(0, diceRoll);
							break;

			case "WEAPON":

							break;
		}
	}
}