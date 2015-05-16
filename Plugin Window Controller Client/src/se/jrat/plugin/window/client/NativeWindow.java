package se.jrat.plugin.window.client;

public class NativeWindow {

	private String title;
	private boolean visible;

	public NativeWindow(String title, boolean visible) {
		this.title = title;
		this.visible = visible;
	}

	public String getTitle() {
		return this.title;
	}

	public boolean isVisible() {
		return this.visible;
	}

}
