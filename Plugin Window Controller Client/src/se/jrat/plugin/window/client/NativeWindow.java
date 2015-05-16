package se.jrat.plugin.window.client;

public class NativeWindow {

	private int handle;
	private String title;
	private boolean visible;

	public NativeWindow(int handle, String title, boolean visible) {
		this.title = title;
		this.visible = visible;
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

}
