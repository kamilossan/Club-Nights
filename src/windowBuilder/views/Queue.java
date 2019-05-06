package windowBuilder.views;

import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import app_core.Manager;
import app_core.Player;

public class Queue extends JFrame {
 Thread thread;
 DefaultListModel listModel = new DefaultListModel();
 JList list;
 JPanel Queue;
 boolean active;
	public Queue() {
		Queue = new JPanel();
		Queue.setBackground(Color.DARK_GRAY);
		Queue.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Queue);
		Queue.setLayout(null);
		// TODO Auto-generated constructor stub
		setBounds(100, 100, 600, 350);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		list = new JList(listModel);
		for (Player player : Manager.QUEUE) {
			listModel.addElement(player.getName());
		}

		list.setBounds(198, 62, 359, 173);
		list.repaint();
		Queue.add(list);
		setVisible(true);
		
	}

}
