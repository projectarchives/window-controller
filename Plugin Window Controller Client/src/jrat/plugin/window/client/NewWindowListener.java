package jrat.plugin.window.client;

public abstract interface NewWindowListener {
	
	public abstract void clear();
	
	public abstract void windowAdded(NativeWindow window);

}
