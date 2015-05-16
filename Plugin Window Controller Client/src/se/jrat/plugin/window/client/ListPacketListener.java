package se.jrat.plugin.window.client;

import java.io.DataInputStream;
import java.util.ArrayList;
import java.util.List;

import jrat.api.Client;
import jrat.api.net.PacketListener;

public class ListPacketListener extends PacketListener {
	
	public static final List<NewWindowListener> LISTENERS = new ArrayList<NewWindowListener>();

	@Override
	public void perform(Client client) {
		for (NewWindowListener l : LISTENERS) {
			l.clear();
		}
		
		try {
			DataInputStream dis = client.getDataInputStream();
			
			int len = dis.readInt();
			
			for (int i = 0; i < len; i++) {
				int handle = dis.readInt();
				String title = client.readString();
				boolean visible = dis.readBoolean();
				
				NativeWindow window = new NativeWindow(handle, title, visible);
				
				for (NewWindowListener l : LISTENERS) {
					l.windowAdded(window);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
