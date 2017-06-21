import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.nio.*;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class btTabletopTool
{
	public static void main(String args[])
	{
		toolGui tg;
		System.out.println("Reactor Online. \nSensors Online.");
		tg = new toolGui();
		System.out.println("All systems nominal.");
	}
}

class toolGui extends JFrame implements ActionListener
{
	JFrame mainFrame;
	JFrame weaponFrame;
	JButton diceRollBtn;
	JButton weaponReferenceBtn;
	JButton closeWeaponWindow;
	JTextField diceRollField;
	JPanel dicePanel;
	JPanel listPanel;
	JPanel topButtonPanel;
	Random rand = new Random();
	int diceRoll;
	JTable weaponsTable;
	JScrollPane weaponScrollPane;
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

		///////////adding stuff to various parts///////////
		loadLists();
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

		//////////Debugging//////////
		//System.out.println(Integer.toString(weaponsTable.getColumnCount()));

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
							weaponGui();
							break;
		}
	}

///////////////////////////////////////////////////////////////////////////////////////

	public void weaponGui ()
	{
		weaponFrame = new JFrame("Weapon Reference Sheet");
		weaponScrollPane = new JScrollPane(weaponsTable);
		weaponsTable.setFillsViewportHeight(true);
		weaponFrame.add(weaponScrollPane);
		weaponFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		weaponFrame.setSize(600,300);
		weaponFrame.setLocationRelativeTo(null);
		weaponFrame.setVisible(true);
	}

///////////////////////////////////////////////////////////////////////////////////////

	public void loadLists ()
	{
		String path = "./data/weapons.txt";
		Vector wVector = new Vector();
		Vector colVector = new Vector();
		Vector vVector = new Vector();
		colVector.add("Name");
		colVector.add("Heat");
		colVector.add("Damage");
		colVector.add("Min");
		colVector.add("Short");
		colVector.add("Med");
		colVector.add("Long");
		colVector.add("Ext");
		colVector.add("Type");
		try
		{
			FileInputStream fis = new FileInputStream(path);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			Scanner ws = new Scanner(fis);
			ws.useDelimiter(";");
			while(ws.hasNext())
			{
				wVector.add(ws.next());
				if(wVector.lastElement().equals("E")||wVector.lastElement().equals("B")||wVector.lastElement().equals("M"))
					{
						vVector.add(wVector.clone());
						//System.out.println(vVector.size());//Debugging
						wVector.clear();
					}
			}
			weaponsTable = new JTable(vVector,colVector);
			ws.close();
			fis.close();
			fis.close();
		}
		catch(FileNotFoundException fnfe)
		{
			System.out.println("Error finding weapons file, is it missing?");
			System.exit(1);
		}
		catch(IOException ioe)
		{
			System.out.println("Error opening or closing a file stream");
			System.exit(1);
		}
		System.out.println("Weapons Online.");
		
	}
}