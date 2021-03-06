
package com.toyknight.aeii.core.event;

import com.toyknight.aeii.core.Game;
import com.toyknight.aeii.core.GameListener;

/**
 *
 * @author toyknight
 */
public class TileDestroyEvent implements GameEvent {
	
	private final Game game;
	private final int x;
	private final int y;
	
	public TileDestroyEvent(Game game, int x, int y) {
		this.game = game;
		this.x = x;
		this.y = y;
	}
	
	protected Game getGame() {
		return game;
	}
	
	@Override
	public boolean canExecute() {
		return getGame().getMap().getTile(x, y).isDestroyable();
	}

	@Override
	public void execute(GameListener listener) {
		int tile_index = getGame().getMap().getTileIndex(x, y);
		listener.onTileDestroy(tile_index, x, y);
		short destroyed_tile_index = 
				getGame().getMap().getTile(x, y).getDestroyedTileIndex();
		getGame().getMap().setTile(destroyed_tile_index, x, y);
	}
	
}
