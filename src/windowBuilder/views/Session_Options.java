package windowBuilder.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import app_core.Manager;
import app_core.Player;

import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import javax.swing.JScrollBar;
import javax.swing.JToggleButton;
import javax.swing.JCheckBox;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.ParseException;
import java.util.LinkedList;

import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JRadioButton;

public class Session_Options extends JFrame {

	private JPanel Session_Options;
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();
	private final Action action_2 = new SwingAction_2();
	private final Action action_3 = new SwingAction_3();
	DefaultListModel listModel;
	JSpinner spinner = new JSpinner();
	JList list;
	Thread thread;
	JRadioButton rdbtnMatchByElo;
	JRadioButton rdbtnNewRadioButton = new JRadioButton("Randomize");
	JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Match Pairs");
	private final Action action_4 = new SwingAction_4();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Session_Options frame = new Session_Options();
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
	public Session_Options() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 350);
		Session_Options = new JPanel();
		Session_Options.setBackground(Color.DARK_GRAY);
		Session_Options.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Session_Options);
		Session_Options.setLayout(null);

		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(Session_Options.class.getResource("/windowBuilder/resources/UI_Bar.PNG")));
		label.setBounds(0, 0, 584, 51);
		Session_Options.add(label);

		JButton btnNewButton = new JButton("Search Member");
		btnNewButton.setAction(action);
		btnNewButton.setFont(new Font("Permanent Marker", Font.PLAIN, 14));
		btnNewButton.setBounds(25, 95, 150, 40);
		Session_Options.add(btnNewButton);

		JButton btnAddGuest = new JButton("Add Guest");
		btnAddGuest.setAction(action_1);
		btnAddGuest.setFont(new Font("Permanent Marker", Font.PLAIN, 14));
		btnAddGuest.setBounds(25, 146, 150, 40);
		Session_Options.add(btnAddGuest);

		JButton btnStartMatch = new JButton("Start Match");
		btnStartMatch.setAction(action_3);
		btnStartMatch.setFont(new Font("Permanent Marker", Font.PLAIN, 14));
		btnStartMatch.setBounds(427, 246, 130, 40);
		Session_Options.add(btnStartMatch);

		spinner.setBounds(129, 248, 46, 20);
		Session_Options.add(spinner);

		JLabel lblNewLabel = new JLabel("Fields");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(62, 246, 56, 25);
		Session_Options.add(lblNewLabel);
		listModel = new DefaultListModel();
		list = new JList(listModel);
		for (Player player : Manager.QUEUE) {
			listModel.addElement(player.getName());
		}

		list.setBounds(198, 62, 359, 173);
		list.repaint();
		Session_Options.add(list);

		JButton btnNewButton_1 = new JButton("Clear");
		btnNewButton_1.setAction(action_4);
		btnNewButton_1.setBounds(208, 245, 89, 23);
		Session_Options.add(btnNewButton_1);

		rdbtnMatchByElo = new JRadioButton("Match by elo");
		rdbtnMatchByElo.setForeground(Color.WHITE);

		rdbtnMatchByElo.setBackground(Color.DARK_GRAY);
		rdbtnMatchByElo.setBounds(312, 246, 109, 23);
		Session_Options.add(rdbtnMatchByElo);

		rdbtnNewRadioButton.setForeground(Color.WHITE);
		rdbtnNewRadioButton.setBackground(Color.DARK_GRAY);
		rdbtnNewRadioButton.setBounds(312, 272, 109, 23);
		Session_Options.add(rdbtnNewRadioButton);
		
		
		rdbtnNewRadioButton_1.setForeground(Color.WHITE);
		rdbtnNewRadioButton_1.setBackground(Color.DARK_GRAY);
		rdbtnNewRadioButton_1.setBounds(44, 204, 109, 23);
		Session_Options.add(rdbtnNewRadioButton_1);
		thread = new Thread(new Runnable() {
			public void run() {
				while (true) {
					if (isActive()) {
						// System.out.println("re");
						try {
							listModel = new DefaultListModel();
							for (Player player : Manager.QUEUE) {
								listModel.addElement(player.getName() + " : " + player.getElo());
							}
							list.setModel(listModel);
							list.repaint();

						} catch (Exception e) {
							continue;
						}
					}
				}

			}
		});
		thread.start();

	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Add Guest");
			putValue(SHORT_DESCRIPTION, "Add Guest player");
		}

		public void actionPerformed(ActionEvent e) {
			new Guest();
			repaint();
		}
	}

	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "Add Players");
			putValue(SHORT_DESCRIPTION, "Add chosen club member to session");
		}

		public void actionPerformed(ActionEvent e) {
			new Selection(false);
			repaint();

		}
	}

	private class SwingAction_2 extends AbstractAction {
		public SwingAction_2() {
			putValue(NAME, "Placeholder");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
		}
	}

	private class SwingAction_3 extends AbstractAction {
		public SwingAction_3() {
			putValue(NAME, "Start");
			putValue(SHORT_DESCRIPTION, "Begin session!");
		}

		public void actionPerformed(ActionEvent e) {
			try {
				spinner.commitEdit();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Manager.BY_RATING = rdbtnMatchByElo.isSelected();
			thread.interrupt();
			Manager.FIELD_COUNT = (int) spinner.getValue();
			if (rdbtnNewRadioButton.isSelected()) {
				Manager.randomizeQueue();
			}
			if(rdbtnNewRadioButton_1.isSelected()){
				Manager.TEAM_SIZE = 2;
				
			}else{
				Manager.TEAM_SIZE = 1;
			}
			Manager.fillMatches();
			new Games();
			dispose();
		}
	}

	private class SwingAction_4 extends AbstractAction {
		public SwingAction_4() {
			putValue(NAME, "Clear");
			putValue(SHORT_DESCRIPTION, "Clear queue");
		}

		public void actionPerformed(ActionEvent e) {
			Manager.QUEUE = new LinkedList<Player>();
		}
	}
}