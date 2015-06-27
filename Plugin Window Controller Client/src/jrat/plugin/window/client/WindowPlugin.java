package jrat.plugin.window.client;

import jrat.api.Icons;
import jrat.api.Plugin;
import jrat.api.net.Packet;
import jrat.api.ui.RATControlMenuEntry;

public class WindowPlugin extends Plugin {
	
	public static final short HEADER_LIST = -127;

	public WindowPlugin() {
		super("Window Controller", "1.1", "Window Controller Plugin", "jRAT", Icons.getIcon("Window Controller", "/icons/icon.png"));
		
		RATControlMenuEntry entry = new RATControlMenuEntry("Windows", icon, WindowPanel.class);		
		RATControlMenuEntry.addEntry(entry);

		Packet.registerIncoming(HEADER_LIST, new ListPacketListener());
	}

}
