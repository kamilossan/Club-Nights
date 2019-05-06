package windowBuilder.views;

import javax.swing.JFrame;
import javax.swing.JList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.border.BevelBorder;

import app_core.Manager;
import app_core.Player;

import java.awt.TextArea;
import java.awt.Label;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import java.awt.ScrollPane;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;

public class Selection extends JFrame {
	private final Action action = new SwingAction();
	DefaultListModel listModel = new DefaultListModel();
	JList list = new JList(listModel);
	private boolean delete_mode;

	public Selection(boolean delete_mode) {
		this.delete_mode = delete_mode;
		setBounds(100, 100, 281, 517);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		for (Player player : Manager.PLAYER_BASE) {
			listModel.addElement(player.getName()+" : "+player.getElo());
		}
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
				
				JButton btnNewButton = new JButton("Add to Queue");
				if(delete_mode){
					btnNewButton.setText("Delete...");
				}
				getContentPane().add(btnNewButton, BorderLayout.SOUTH);
				btnNewButton.setAction(action);
				getContentPane().add(list, BorderLayout.CENTER);
				list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		// TODO Auto-generated constructor stub
		setVisible(true);
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Select");
			putValue(SHORT_DESCRIPTION, "Proceed with selected");
		}

		public void actionPerformed(ActionEvent e) {
			int[] x = list.getSelectedIndices();
			for (int index : x) {
				if(delete_mode){
					Manager.deletePlayer(Manager.PLAYER_BASE.get(index));
					dispose();
				}else{
				//System.out.println("adding player " + x);
				Manager.queuePlayer(Manager.PLAYER_BASE.get(index));}

			}
		}
	}
}
