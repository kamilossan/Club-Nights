package windowBuilder.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import app_core.Manager;
import app_core.Match;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DropMode;
import java.awt.Choice;
import java.awt.Label;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class Games extends JFrame {
	private int matchState = 0;
	private JPanel contentPane;
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Games frame = new Games();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Games() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 584, 311);
		contentPane.add(panel);
		
		JButton btnQ = new JButton("Queue");
		btnQ.setAction(action_1);
		btnQ.setFont(new Font("Permanent Marker", Font.PLAIN, 14));
		btnQ.setBounds(502, 0, 82, 40);
		panel.add(btnQ);
		
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 584, 51);
		panel.add(label);
		label.setIcon(new ImageIcon(Games.class.getResource("/windowBuilder/resources/UI_Bar.PNG")));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		
		
		tabbedPane.setBounds(0, 96, 584, 164);
		populatePane();
		panel.add(tabbedPane);
		
		
		this.setVisible(true);
//		JList<? extends E> list = new JList();
//		panel_1.add(list);
	}
	private void populatePane(){
		tabbedPane.removeAll();
		if(Manager.ONGOING_MATCHES!=null){
			System.out.println("testin");
				int x = 0;
				for(Match match:Manager.ONGOING_MATCHES){
				
				JPanel panel_1 = new JPanel();
				tabbedPane.addTab("Match "+(x+1), null, panel_1, null);
				x++;
				JTextPane textPane = new JTextPane();
				textPane.setBackground(Color.LIGHT_GRAY);
				textPane.setEditable(false);
				textPane.setText(match.getMatchInfo());
				panel_1.add(textPane);
				
				Choice choice = new Choice();
				choice.add("Draw");
				choice.add("P1 win");
				choice.add("P2 win");
				panel_1.add(choice);
				JButton btnEndMatch = new JButton("End Match");
				btnEndMatch.setAction(action);
				btnEndMatch.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						matchState = choice.getSelectedIndex();
						System.out.println("change");
					}
				});
				
				btnEndMatch.setFont(new Font("Permanent Marker", Font.PLAIN, 14));
				btnEndMatch.setBounds(454, 271, 130, 40);
				panel_1.add(btnEndMatch);
				}
				}
				else{System.out.println("empty");}
	}
	private class SwingAction extends AbstractAction {
		public int state= 0;
		public SwingAction() {
			this.state = state;
			putValue(NAME, "End Match");
			putValue(SHORT_DESCRIPTION, "End current match and save score");
		}
		public void actionPerformed(ActionEvent e) {
			//System.out.println("ending with result "+matchState);
			//System.out.println("set");
			int x = tabbedPane.getSelectedIndex();
			tabbedPane.remove(x);
			Manager.finishMatch(x, matchState);
			
			Manager.fillMatches();
			populatePane();
			tabbedPane.repaint();
			try {
				Manager.saveData();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			repaint();
			
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "Queue");
			putValue(SHORT_DESCRIPTION, "Show remaining players in queue");
		}
		public void actionPerformed(ActionEvent e) {
			new Queue();
			repaint();
		}
	}
}
