package windowBuilder.views;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;

import app_core.Manager;
import app_core.Match;

import java.awt.BorderLayout;
import java.awt.Color;

public class MatchHistory extends JFrame{

	public MatchHistory() {
		setBounds(100, 100, 350, 517);
		DefaultListModel model = new DefaultListModel();
		for(Match match:Manager.MATCH_HISTORY){
			model.addElement(match.convertResult());
		}
		JList list = new JList(model);
		list.setForeground(Color.WHITE);
		list.setBackground(Color.DARK_GRAY);
		getContentPane().add(list, BorderLayout.CENTER);
		setVisible(true);
		// TODO Auto-generated constructor stub
	}

}
