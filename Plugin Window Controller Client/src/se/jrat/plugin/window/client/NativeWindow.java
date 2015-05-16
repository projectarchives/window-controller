package se.jrat.plugin.window.client;

import javax.swing.Icon;

public class NativeWindow {

	private int handle;
	private String title;
	private boolean visible;
	private Icon icon;

	public NativeWindow(int handle, String title, boolean visible) {
		this(handle, title, visible, null);
	}
	
	public NativeWindow(int handle, String title, boolean visible, Icon icon) {
		this.handle = handle;
		this.title = title;
		this.visible = visible;
		this.icon = icon;
	}
	
	public int getHandle() {
		return this.handle;
	}

	public String getTitle() {
		return this.title;
	}

	public boolean isVisible() {
		return this.visible;
	}
	
	public Icon getIcon() {
		return this.icon;
	}

}
