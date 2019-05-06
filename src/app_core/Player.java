package app_core;

public class Player {
 String name;
int elo;
	public Player(String name, int elo) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.elo = elo;
	}
	public String getName(){
		return name;
	}
	
	public int getElo(){
		return elo;
	}

}
