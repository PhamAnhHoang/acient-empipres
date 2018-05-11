package com.toyknight.aeii;

import com.toyknight.aeii.gui.AEIIApplet;
import com.toyknight.aeii.gui.util.DialogUtil;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Launcher implements Runnable {

	private final int TILE_SIZE;
	private final int SCREEN_WIDTH;
	private final int SCREEN_HEIGHT;

	private static JFrame main_frame;
	private static AEIIApplet aeii_applet;

	public Launcher(int ts, int width, int height) {
		this.TILE_SIZE = ts;
		this.SCREEN_WIDTH = width;
		this.SCREEN_HEIGHT = height;
	}

	@Override
	public void run() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException |
				InstantiationException |
				IllegalAccessException |
				UnsupportedLookAndFeelException ex) {
			//do nothing
		}
		try {
			Configuration.init();
			Language.init();
			String title = Language.getText("LB_TITLE");
			main_frame = new JFrame(title);
			main_frame.setIconImage(
					ImageIO.read(Launcher.class.getResource("gameicon.png")));
			main_frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			main_frame.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					exit();
				}
			});
			main_frame.setResizable(false);

			aeii_applet = new AEIIApplet(TILE_SIZE, SCREEN_WIDTH, SCREEN_HEIGHT);
			aeii_applet.init();
			main_frame.setContentPane(aeii_applet.getContentPane());

			main_frame.pack();
			main_frame.setLocationRelativeTo(null);
			main_frame.setVisible(true);
			aeii_applet.start();
		} catch (IOException ex) {
			DialogUtil.showError(null, ex.getMessage());
		}
	}

	public static AEIIApplet getApplet() {
		return aeii_applet;
	}

	public static JFrame getWindow() {
		return main_frame;
	}

	public static void exit() {
		aeii_applet.stop();
		main_frame.dispose();
		if(aeii_applet.isDebugMode()) {
			System.exit(0);
		}
	}

	public static void main(String[] args) throws Exception {
		int ts = 48;
		int width = 1000;
		int height = 700;
		EventQueue.invokeLater(new Launcher(ts, width, height));
	}

}
