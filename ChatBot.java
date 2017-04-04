import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.lang.Math;

public class ChatBot extends JFrame implements KeyListener {
	
	JPanel p=new JPanel();
	JTextArea dialog=new JTextArea(20,50);
	JTextArea input=new JTextArea(1,50);
	JScrollPane scroll=new JScrollPane(
			dialog,
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
			);
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		Font f=new Font("Monotype Corsiva", Font.BOLD,40);
		g.setColor(new Color(250,170,155));
		g.setFont(f);
		   g.drawString("CHAT  WITH  VIBS .....!",100,100);
		   
		   g.setColor(Color.GREEN);
	}
	
	String [][] chatBot={
			{"hi","hello"},
			{"hi","hey"},
			
			{"how are you","how r u"},
			{"doing well","good"},
			
			{"what are you doing"},
			{"nothing","talking"},
			
			{"shut up","stop talking","NOOB","vibhav dont wanna talk"}
	};
	
	public static void main(String[] args) {
	new ChatBot();

	}
	 public ChatBot() {
		super("CHAT WITH VIBS !");
		setSize(600, 400);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		dialog.setEditable(false);
		input.addKeyListener(this);
		p.add(scroll);
		p.add(input);
		p.setBackground(new Color(250, 200, 0));
		add(p);
		setVisible(true);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER)
		{
			input.setEditable(false);
			String quote=input.getText();
			input.setText("");
			addText("-->ME:\t"+quote);
			addText("\n");
			quote=quote.trim();
			while(
					quote.charAt(quote.length()-1)=='!'||
					quote.charAt(quote.length()-1)=='?'||
				    quote.charAt(quote.length()-1)=='.')
			{
				quote=quote.substring(0,quote.length()-1);
			}
			quote=quote.trim();
			byte response=0;
			int j=0;
			while(response==0)
			{
				if(inArray(quote.toLowerCase(),chatBot[j*2]))
				{
					response=2;
					int r=(int)Math.floor(Math.random()*chatBot[(j*2)+1].length);
					addText("-->VIBS:\t"+chatBot[(j*2)+1][r]);
				}
				j++;
				if(j*2==chatBot.length-1 && response==0)
				{
					response=1;
				}
			}
			if(response==1)
			{
				int r=(int)Math.floor(Math.random()*chatBot[chatBot.length-1].length);
				addText("-->VIBS:\t"+chatBot[chatBot.length-1][r]);
			}
			addText("\n");
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER)
		{
			input.setEditable(true);
			
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void addText(String str)
	{
		dialog.setText(dialog.getText()+str);
		
	}
	public boolean inArray(String in,String [] str)
	{
		boolean match=false;
		for(int i=0;i<str.length;i++)
			if(str[i].equals(in))
			{
				match=true;
			}
		return match;
	}

}
