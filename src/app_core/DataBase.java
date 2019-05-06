package app_core;

import java.util.LinkedList;

public class DataBase {
public LinkedList<Match>matches = new LinkedList<Match>();
public LinkedList<Player>players = new LinkedList<Player>();
	public DataBase(LinkedList<Match>matches, LinkedList<Player> players) {
		if(matches!=null){
		this.matches = matches;}
		if(players!=null){
		this.players = players;}

	}

}
