package windowBuilder.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import app_core.Manager;
import app_core.Player;

import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JLayeredPane;
import java.awt.Color;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.swing.Action;


public class MainMenu extends JFrame {

	private JPanel contentPane;
	private final Action start = new SwingAction();
	private final Action addMember = new SwingAction_1();
	private final Action alterMember = new SwingAction_2();
	private final Action quit = new SwingAction_3(this);
	private final Action action = new SwingAction_4();
	private final Action action_1 = new SwingAction_5();


	/**
	 * Launch the application.
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException, IOException 

	{
		Manager.readFromDatabase();
		//Manager.PLAYER_BASE.add(new Player("testP1", 100));
		//Manager.PLAYER_BASE.add(new Player("testP2", 100));
		for(Player player:Manager.PLAYER_BASE){
			//System.out.println("adding "+player.getName());
			//Manager.queuePlayer(player);
		}
		//Manager.ONGOING_MATCHES.add(Manager.createMatch());
//		Manager.finishMatch(Manager.ONGOING_MATCHES.get(0), 1);
		//System.out.println(Manager.ONGOING_MATCHES.get(0).getMatchInfo());
		//Manager.saveData();
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					
					MainMenu frame = new MainMenu();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainMenu() {
		setTitle("Club Nights");
		initcomponents();
		createEvents();
	}
	

	/////////////////////////////////////////////////////////
	// This method contains all of the code for creating and 
	// Initialising components
	////////////////////////////////////////////////////////
	private void initcomponents() {
		setTitle("Club Nights");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton StartSession_btn = new JButton("Start Session");
		StartSession_btn.setAction(start);
		StartSession_btn.setBounds(188, 123, 198, 40);
		StartSession_btn.setBackground(Color.LIGHT_GRAY);
		StartSession_btn.setFont(new Font("Permanent Marker", Font.PLAIN, 14));
		
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 585, 50);
		label.setBackground(Color.BLACK);
		label.setIcon(new ImageIcon(MainMenu.class.getResource("/windowBuilder/resources/UI_Bar.PNG")));
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(403, 85, 1, 1);
		
		JButton btnAddMember = new JButton("Add Member");
		btnAddMember.setAction(addMember);
		btnAddMember.setBounds(188, 174, 198, 40);
		btnAddMember.setFont(new Font("Permanent Marker", Font.PLAIN, 14));
		btnAddMember.setBackground(Color.LIGHT_GRAY);
		contentPane.setLayout(null);
		contentPane.add(label);
		contentPane.add(btnAddMember);
		contentPane.add(StartSession_btn);
		contentPane.add(layeredPane);
		

	//	contentPane.add(btnAlterMember);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.setAction(quit);
		btnQuit.setFont(new Font("Permanent Marker", Font.PLAIN, 14));
		btnQuit.setBackground(Color.LIGHT_GRAY);
		btnQuit.setBounds(188, 276, 198, 40);
		contentPane.add(btnQuit);
		
		JButton btnNewButton = new JButton("Delete Member");
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnNewButton.setAction(action);
		btnNewButton.setBounds(188, 225, 198, 40);
		contentPane.add(btnNewButton);
		
		JButton btnHistory = new JButton("History...");
		btnHistory.setAction(action_1);
		btnHistory.setBounds(451, 394, 89, 23);
		contentPane.add(btnHistory);
	}
	
	///////////////////////////////////////////////////////////
	// This method contains all the events for creating events
	//////////////////////////////////////////////////////////
	
	private void createEvents() 
	{
		// TODO auto-generated method stub
	}
	private class SwingAction extends AbstractAction {
		JFrame frame;
		public SwingAction() {
			putValue(NAME, "Start Session");
			putValue(SHORT_DESCRIPTION, "Start new match session");
			
		}
		public void actionPerformed(ActionEvent e) {
			this.frame = new Session_Options();
			frame.repaint();
			frame.setVisible(true);
			
		}
	}
	private class SwingAction_1 extends AbstractAction {
		JFrame frame;
		public SwingAction_1() {
			putValue(NAME, "Add Member");
			putValue(SHORT_DESCRIPTION, "Add new club member");
			
		}
		public void actionPerformed(ActionEvent e) {
			new PlayerCreator();

			repaint();
		}
	}
	private class SwingAction_2 extends AbstractAction {
		public SwingAction_2() {
			putValue(NAME, "Edit Member");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	private class SwingAction_3 extends AbstractAction {
		JFrame frame;
		public SwingAction_3(JFrame frame) {
			putValue(NAME, "Quit");
			putValue(SHORT_DESCRIPTION, "Exit application");
			this.frame = frame;
		}
		public void actionPerformed(ActionEvent e) {
			try {
				Manager.saveData();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		}
	}
	private class SwingAction_4 extends AbstractAction {
		public SwingAction_4() {
			putValue(NAME, "Delete Member(s)");
			putValue(SHORT_DESCRIPTION, "Delete club member");
		}
		public void actionPerformed(ActionEvent e) {
			new Selection(true);
			repaint();
		}
	}
	private class SwingAction_5 extends AbstractAction {
		public SwingAction_5() {
			putValue(NAME, "History...");
			putValue(SHORT_DESCRIPTION, "Match history recorded on the app");
		}
		public void actionPerformed(ActionEvent e) {
			new MatchHistory();
			repaint();
		}
	}
}
