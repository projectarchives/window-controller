package se.jrat.plugin.window.client;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

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
				
				ImageIcon icon = null;
				
				if (dis.readBoolean()) {
					byte[] bIcon = new byte[dis.readInt()];
					dis.readFully(bIcon);
					icon = new ImageIcon(ImageIO.read(new ByteArrayInputStream(bIcon)));
				}
				
				NativeWindow window = new NativeWindow(handle, title, visible, icon);
				
				for (NewWindowListener l : LISTENERS) {
					l.windowAdded(window);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
