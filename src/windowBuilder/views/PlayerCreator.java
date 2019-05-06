package windowBuilder.views;
import app_core.*;


import javax.swing.JFrame;
import javax.swing.JTextField;

import javax.swing.JSpinner;

import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.Action;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class PlayerCreator extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	private JTextField textField;
	private JSpinner spinner = new JSpinner();
	private final Action action = new SwingAction();

	public PlayerCreator() {
		setBounds(100, 100, 400, 400);
		setTitle("Add member...");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(0, 30, 260, 20);
		getContentPane().add(textField);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setColumns(20);
		spinner.setBounds(80, 87, 109, 20);
		getContentPane().add(spinner);
		
		
		spinner.setToolTipText("Elo rating");
		
		JButton btnNewButton = new JButton("Add Member");
		btnNewButton.setBounds(49, 154, 166, 23);
		getContentPane().add(btnNewButton);
		btnNewButton.setAction(action);
		btnNewButton.setVerticalAlignment(SwingConstants.BOTTOM);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(117, 11, 46, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblElo = new JLabel("Elo");
		lblElo.setBounds(117, 71, 46, 14);
		getContentPane().add(lblElo);
		setVisible(true);
	}



	private class SwingAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction() {
			putValue(NAME, "Add Member");
			putValue(SHORT_DESCRIPTION, "Add member!");
		}
		public void actionPerformed(ActionEvent e) {
			try {
				spinner.commitEdit();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Manager.addPlayer(new Player(textField.getText(), (int)spinner.getValue()));
			try {
				Manager.saveData();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			dispose();
		}
	}
}
