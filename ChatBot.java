package hacktober;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

//A generic chatbot class
public class ChatBot {
	private HashMap<Integer, Entry<TriggerEvent,ResponseEvent>> responses;
	private ChatRoom room;
	
	public ChatBot(ChatRoom room) {
		this.room = room;
		responses = new HashMap<Integer, Entry<TriggerEvent,ResponseEvent>>();
	}
	
	//This event is triggered if no TriggerEvent is triggered (null = nothing happens)
	public ResponseEvent getDefaultResponse(String quote) {
		return null;
	}
	//Room this bot is in
	public ChatRoom getRoom() {
		return room;
	}
	//Name of chatbot
	public String getName() {
		return "GenericChatBot";
	}
	
	//Write Text line to chatroom
	public void writeLine(String str) {
		room.handleMessage(getName(), str);
	}
	//Create a new trigger/response pair with a given id
	public void registerNewAction(int id, TriggerEvent trigger, ResponseEvent response) {
		responses.put(id, new AbstractMap.SimpleEntry<TriggerEvent, ResponseEvent>(trigger, response));
	}
	
	public void reactToQuote(String quote) {
		for(Entry<TriggerEvent,ResponseEvent> e:responses.values()) {
			//Check if one of the entries gets triggered
			if(e.getKey() != null && e.getKey().triggered(this, getRoom(), quote)) {
				if(e.getValue() != null)
					e.getValue().trigger(this, getRoom(), quote);
				return; //Stop after first activated TriggerEvent, remove this if you want to check for further triggered events
			}
		}
		ResponseEvent def = getDefaultResponse(quote);
		if(def!=null)
			def.trigger(this, getRoom(), quote);
			
	}
	
	public static abstract class TriggerEvent {
		public abstract boolean triggered(ChatBot subject, ChatRoom context, String quote);
	}
	public static abstract class ResponseEvent {
		public abstract void trigger(ChatBot subject, ChatRoom context, String quote);
	}
	
	public static class TriggerEventLine extends TriggerEvent {
		private List<String> triggerLines;
		public TriggerEventLine(List<String> triggerLines) {
			this.triggerLines = triggerLines;
		}
		public boolean triggered(ChatBot subject, ChatRoom context, String quote) {
			//Remove punctuation from quote and trim
			quote = quote.trim();
			while (quote.charAt(quote.length() - 1) == '!'
					|| quote.charAt(quote.length() - 1) == '?'
					|| quote.charAt(quote.length() - 1) == '.') {
				quote = quote.substring(0, quote.length() - 1);
			}
			quote = quote.trim();
			//return true if the formated quote is equals to one of the lines
			for(String line:triggerLines) {
				if(quote.equalsIgnoreCase(line))
					return true;
			}
			return false;
		}
	}
	public static class ResponseEventLine extends ResponseEvent {
		private List<String> responseLines;
		private Random random;
		public ResponseEventLine(List<String> list) {
			this.responseLines = list;
			this.random = new Random(); //seed this if you want more "randomness"
		}
		public void trigger(ChatBot subject, ChatRoom context, String quote) {
			//Write a random string of the response lines
			subject.writeLine(responseLines.get(random.nextInt(responseLines.size())));
		}
	}
}
