package hacktober;

import java.util.ArrayList;
import java.util.Arrays;

public class ChatBotVIBS extends ChatBot{

	public ChatBotVIBS(ChatRoom room) {
		super(room);
		this.registerNewAction(0, new TriggerEventLine(Arrays.asList(new String[]{"hi","hello"})),
				new ResponseEventLine(Arrays.asList(new String[]{"hi","hey"})));
		String[] trigger = {"how are you","how r u"};
		String[] response = {"doing well","good"};
		this.registerNewAction(1, new TriggerEventLine(Arrays.asList(trigger)),
				new ResponseEventLine(Arrays.asList(response)));
		ArrayList<String> triggerList = new ArrayList<String>();
		triggerList.add("what are you doing");
		ArrayList<String> responseList = new ArrayList<String>();
		responseList.add("nothing");
		responseList.add("talking");
		this.registerNewAction(2, new TriggerEventLine(triggerList),new ResponseEventLine(responseList));
	}
	
	@Override
	public ResponseEvent getDefaultResponse(String quote) {
		return new ResponseEventLine(Arrays.asList(new String[]{"shut up","stop talking","NOOB","vibhav dont wanna talk"}));
	}
	
	@Override
	public String getName() {
		return "VIBS";
	}
}
