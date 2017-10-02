package hacktober;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ChatRoomWindow extends JFrame implements KeyListener {
	
	private ChatRoom room;
	private JPanel panel;
	private JTextArea dialog;
	private JTextArea input;
	private JScrollPane scroll;
	
	
	public ChatRoomWindow(ChatRoom room, String windowName) {
		super(windowName);
		this.room = room;
		panel = new JPanel();
		dialog = new JTextArea(20, 50);
		setSize(600, 400); //Set size of chat room
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		dialog.setEditable(false); //Make chat history not editable
		input = new JTextArea(1, 50);
		input.addKeyListener(this);
		scroll = new JScrollPane(dialog,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panel.add(scroll);
		panel.add(input);
		panel.setBackground(new Color(250, 200, 0));
		add(panel);
		setVisible(true);
	}
	
	public ChatRoom getRoom() {
		return room;
	}
	
	//This is rendered before first text is written
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Font f = new Font("Monotype Corsiva", Font.BOLD, 40);
		g.setColor(new Color(250, 170, 155));
		g.setFont(f);
		String botString = "";
		for(ChatBot bot:getRoom().getBots())
			botString = botString+", "+bot.getName();
		botString = botString.substring(2);
		g.drawString("CHAT  WITH  "+botString+" .....!", 100, 100);
		g.setColor(Color.GREEN);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			input.setEditable(false); //Lock input field so text can't be changed while processing this
			String quote = input.getText();
			room.userInput(quote); //process userinput
			input.setText(""); //Clear input field
		}

	}

	//Unlock input field after stop pressing enter
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			input.setEditable(true);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	//Add text to the chat history
	public void addText(String str) {
		dialog.setText(dialog.getText() + str);
	}

}
