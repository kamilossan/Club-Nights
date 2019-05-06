package app_core;

public class Match {
private	Player[] x;
private	Player[] y;
//0 draw, 1 player 1 win, 2 player 2 win
private int result;
	
	public Match(Player[] x, Player[] y) {
		this.x = x;
		this.y = y;
		
	}
public void finishMatch(int result){
	this.result = result;
}

public String getMatchInfo(){
	if(x.length<2){
		return x[0].name +" versus "+y[0].name;
	}
	return x[0].name +" "+x[1].name+"\n"+" versus "+"\n"+y[0].name +" "+y[1].name;
	
}
public String convertResult(){
	String a = getMatchInfo();
	String b = " - ";
	switch(result){
	case 1:
		b+="team "+ 1 + " win";
		break;
	case 2:
		b+="team " +2+" win";
		break;
	default:
		b+="draw";
		break;
	
	}
	return a+b;
}
}
