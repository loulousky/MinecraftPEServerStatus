package query;

import java.util.ArrayList;

public class QueryResponse
{
	static byte NULL = 00;
	static byte SPACE = 20;
	
	private String hostname, gameMode, mapName, gameID, version, plugins;
	private int onlinePlayers, maxPlayers;
	private short port;
	private boolean whitelist;
	
	private ArrayList<String> playerList;
	
	public QueryResponse(byte[] data) {
		data = ByteUtils.trim(data);
		byte[][] temp = ByteUtils.split(data);
		
		/*for (byte[] temp2 : temp) {
			System.out.println(new String(temp2));
			System.out.flush();
		}*/
			
		hostname = new String(temp[3]);
		gameMode = new String(temp[5]);
		gameID = new String(temp[7]);
		version = new String(temp[9]);
		
		plugins = new String(temp[13]);
		mapName = new String(temp[15]);
		onlinePlayers = Integer.parseInt(new String(temp[17]));
		maxPlayers = Integer.parseInt(new String(temp[19]));
		whitelist = new String(temp[21]).equals("on") ? true : false;
		port = Short.parseShort(new String(temp[23]));
		
		playerList = new ArrayList<String>();
		for(int i=27; i<temp.length; i++) {
			playerList.add(new String(temp[i]));
		}
	}
	
	
	public String toString() {
		String delimiter = ", ";
		StringBuilder str = new StringBuilder();
		str.append(hostname);
		str.append(delimiter);
		str.append(gameMode);
		str.append(delimiter);
		str.append(gameID);
		str.append(delimiter);
		str.append(version);
		str.append(delimiter);
		
		str.append("Plugins: ");
		str.append('[');
		str.append(plugins);
		str.append(']');
		str.append(delimiter);
		
		str.append(mapName);
		str.append(delimiter);
		str.append(onlinePlayers);
		str.append(delimiter);
		str.append(maxPlayers);
		str.append(delimiter);
		str.append(whitelist);
		str.append(delimiter);
		str.append(port);
		str.append(delimiter);
		
		str.append("Players: ");
		str.append('[');
		for(String player : playerList) {
			str.append(player);
			if(playerList.indexOf(player) != playerList.size()-1) {
				str.append(',');
			}
		}
		str.append(']');
		
		return str.toString();
	}
}
