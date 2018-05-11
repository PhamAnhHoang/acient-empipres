package com.toyknight.aeii.gui.screen.internal;

import com.toyknight.aeii.Language;
import com.toyknight.aeii.Launcher;
import com.toyknight.aeii.core.creator.GameCreator;
import com.toyknight.aeii.core.creator.SkirmishGameCreator;
import com.toyknight.aeii.core.map.MapProvider;
import com.toyknight.aeii.gui.AEIIApplet;
import com.toyknight.aeii.gui.AEIIPanel;
import com.toyknight.aeii.gui.Screen;
import com.toyknight.aeii.gui.control.AEIIButton;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class MainMenu extends JPanel {

	private static final long serialVersionUID = 1L;
	public static final String ID_WELCOME_MENU = "welcome_menu";
	public static final String ID_SINGLE_PLAYER_MENU = "single_player_menu";

	private final AEIIApplet context;
	private final CardLayout menu_container;
	
	//welcome menu
	private final AEIIButton btn_single_player
			= new AEIIButton(Language.getText("LB_SINGLE_PLAYER"));
	private final AEIIButton btn_exit
			= new AEIIButton(Language.getText("LB_EXIT"));
	
	public MainMenu(AEIIApplet context) {
		this.context = context;
		this.menu_container = new CardLayout();
	}

	public void initComponents(int ts, Screen screen) {
		int menu_width = ts * 4;
		int menu_height = ts / 2 * 2 + ts / 4 * 3;
		this.setBounds(400, 250, menu_width, menu_height);
		this.setLayout(menu_container);
		JPanel welcome_menu = new AEIIPanel();
		welcome_menu.setLayout(null);
		btn_single_player.setBounds(ts / 4, ts / 4, ts * 4 - ts / 2, ts / 2);
		btn_single_player.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameCreator game_creator = new SkirmishGameCreator();
				MapProvider map_provider = getContext().getLocalMapProvider();
				getContext().gotoGameCreateScreen(game_creator, map_provider);
			}
		});
		welcome_menu.add(btn_single_player);
		btn_exit.setBounds(ts / 4, ts / 4 * 2 + ts / 2, ts * 4 - ts / 2, ts / 2);
		btn_exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Launcher.exit();
			}
		});
		welcome_menu.add(btn_exit);
		this.add(welcome_menu, ID_WELCOME_MENU);
	}
	
	public AEIIApplet getContext() {
		return context;
	}

	public void showMenu(String menu_id) {
		menu_container.show(this, menu_id);
		this.revalidate();
	}

}
