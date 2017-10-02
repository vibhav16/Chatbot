package hacktober;

import java.util.ArrayList;
import java.util.List;


public class ChatRoom {
	
	ChatRoomWindow window;
	List<ChatBot> bots;
	String roomName;
	public ChatRoom(String name) {
		bots = new ArrayList<ChatBot>();
		roomName = name;
	}
	//Start chat room
	public void start() {
		window = new ChatRoomWindow(this,roomName);
	}
	//Add new bot to the chat room
	public void addBot(ChatBot bot) {
		bots.add(bot);
	}
	
	public List<ChatBot> getBots() {
		return bots;
	}
	//gets called every time the user writes something in the chatroom
	public void userInput(String quote) {
		handleMessage("ME", quote);
		for(ChatBot bot:bots) {
			bot.reactToQuote(quote);
		}
	}
	
	public void handleMessage(String sender, String str) {
		addText("-->"+sender+":\t");
		addText(str);
		addText("\n");
	}
	
	//Print text to chatroom
	public void addText(String str) {
		window.addText(str);
	}
	
	
	public static void main(String[] args) {
		ChatRoom room = new ChatRoom("CHAT WITH VIBS !");
		room.addBot(new ChatBotVIBS(room));
		room.start();
	}

	

}
