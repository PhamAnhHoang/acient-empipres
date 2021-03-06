

package com.toyknight.aeii.core.event;

import com.toyknight.aeii.core.Game;
import com.toyknight.aeii.core.GameListener;
import com.toyknight.aeii.core.animation.AnimationDispatcher;
import com.toyknight.aeii.core.unit.Unit;
import com.toyknight.aeii.core.unit.UnitFactory;

/**
 *
 * @author toyknight
 */
public class UnitSummonEvent implements GameEvent {
	
	private final Game game;
	private final Unit summoner;
	private final int target_x;
	private final int target_y;
	
	public UnitSummonEvent(Game game, Unit summoner, int target_x, int target_y) {
		this.game = game;
		this.summoner = summoner;
		this.target_x = target_x;
		this.target_y = target_y;
	}
	
	protected Game getGame() {
		return game;
	}
	
	@Override
	public boolean canExecute() {
		return getGame().getMap().isTomb(target_x, target_y);
	}

	@Override
	public void execute(GameListener listener) {
		int skeleton = UnitFactory.getSkeletonIntex();
		getGame().getMap().removeTomb(target_x, target_y);
		getGame().addUnit(skeleton, getGame().getCurrentTeam(), target_x, target_y);
		getGame().getMap().getUnit(target_x, target_y).setStandby(true);
		listener.onSummon(summoner, target_x, target_y);
		if (summoner.gainExperience(30)) {
			listener.onUnitLevelUp(summoner);
		}
	}
	
}
