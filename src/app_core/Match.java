package app_core;

public class Match {
private	Player x;
private	Player y;
//0 draw, 1 player 1 win, 2 player 2 win
private int result;
	
	public Match(Player x, Player y) {
		this.x = x;
		this.y = y;
		
	}
public void finishMatch(int result){
	this.result = result;
}

public String getMatchInfo(){
	return x.name + " versus "+y.name;
	
}
public String convertResult(){
	String a = getMatchInfo();
	String b = " - ";
	switch(result){
	case 1:
		b+=x.getName()+" win";
		break;
	case 2:
		b+=y.getName()+" win";
		break;
	default:
		b+="draw";
		break;
	
	}
	return a+b;
}
}
