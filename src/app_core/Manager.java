package app_core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.LinkedList;
import java.util.Random;
import com.google.gson.*;

public class Manager {
	public static DataBase database;
	public volatile static LinkedList<Player> PLAYER_BASE = new LinkedList<Player>(), QUEUE = new LinkedList<Player>();
	public volatile static LinkedList<Match> ONGOING_MATCHES = new LinkedList<Match>(),
			MATCH_HISTORY = new LinkedList<Match>();
	public static int MARGIN = 100;
	public static boolean BY_RATING = true;
	public static int FIELD_COUNT = 2;
	public static int TEAM_SIZE = 1;
	public Manager() {
		// TODO Auto-generated constructor stub
	}

	public static void addPlayer(Player player) {
		PLAYER_BASE.add(player);

	}

	public static void deletePlayer(Player player) {
		PLAYER_BASE.remove(player);
	}

	public static void dequeuePlayer(Player player) {
		QUEUE.remove(player);

	}

	public static void queuePlayer(Player player) {
		if (!QUEUE.contains(player))
			QUEUE.add(player);
	}

	// shuffle QUEUE for random alloc
	public static void randomizeQueue() {
		Random rng = new Random();
		for (int x = 0; x < QUEUE.size(); x++) {
			int y = rng.nextInt(QUEUE.size());
			Player temp = QUEUE.get(x);
			QUEUE.set(x, QUEUE.get(y));
			QUEUE.set(y, temp);
		}
	}

	// finds first in line with acceptable rating margin or first in queue if
	// rating sort disabled
	public static Player findWorthyOpponent(Player player) {
		for (Player x : QUEUE) {
			if (Math.abs((player.elo - x.elo)) < MARGIN && BY_RATING) {
				return x;
			}
		}
		// if noone within margin is found just pop the first in queue
		for (Player x : QUEUE) {
			if (x != player) {
				return x;

			}

		}
		// should never reach unless list is empty
		return null;
	}

	// generate match
	public static Match createMatch() {
		Player[] t1 = new Player[TEAM_SIZE];
		Player[] t2 = new Player[TEAM_SIZE];
		for(int x = 0; x<TEAM_SIZE; x++){
		Player player = QUEUE.pop();
		Player opponent = findWorthyOpponent(player);
		t1[x] = player;
		t2[x] = opponent;
		QUEUE.remove(opponent);
		}
		
		return new Match(t1, t2);

	}

	public static void fillMatches() {
		while (QUEUE.size() >= 2*TEAM_SIZE && ONGOING_MATCHES.size() < FIELD_COUNT) {
			ONGOING_MATCHES.add(createMatch());

		}

	}

	public static void finishMatch(Match match, int result) {

		match.finishMatch(result);
		MATCH_HISTORY.add(match);
		ONGOING_MATCHES.remove(match);

	}

	public static void finishMatch(int index, int result) {
		ONGOING_MATCHES.get(index).finishMatch(result);
		MATCH_HISTORY.add(ONGOING_MATCHES.get(index));
		ONGOING_MATCHES.remove(ONGOING_MATCHES.get(index));
	}

	public static void readFromDatabase() throws UnsupportedEncodingException, IOException {
		try (Reader reader = new FileReader("Data.json")) {
			Gson gson = new GsonBuilder().create();
			database = gson.fromJson(reader, DataBase.class);
			if (database != null) {
				if (database.players != null) {
					PLAYER_BASE = database.players;
				}
				if (database.matches != null) {
					MATCH_HISTORY = database.matches;
				}
			}
		} catch (IOException e) {
			PLAYER_BASE = new LinkedList<Player>();
			MATCH_HISTORY = new LinkedList<Match>();
		}
	}

	public static void saveData() throws IOException {
		DataBase dataDump = new DataBase(MATCH_HISTORY, PLAYER_BASE);
		Gson gson = new GsonBuilder().create();
		File file = new File("Data.json");

		file.createNewFile();

		Writer fw = new FileWriter(file, false);
		gson.toJson(dataDump, fw);
		fw.close();
	}

}
