package edu.vt.bmassa56.Chess2;

import edu.vt.bmassa56.Chess2.pieces.Piece;
import edu.vt.bmassa56.Chess2.player.Player;

public enum Alliance {
	
	BLACK {
		@Override
		public boolean isBlack() {
			
			return true;
		}

		@Override
		public boolean isWhite() {
			
			return false;
		}

		@Override
		public boolean isSameAs(final Piece p) {
			
			if (p.getAlliance().isBlack()) {
				
				return true;
			}
			return false;
		}

		@Override
		public Player choosePlayer(Player whitePlayer, Player blackPlayer) {

			return null;
		}
	}, WHITE {
		@Override
		public boolean isBlack() {
			
			return false;
		}

		@Override
		public boolean isWhite() {
			
			return true;
		}

		@Override
		public boolean isSameAs(final Piece p) {
			
			if (p.getAlliance().isWhite()) {
				
				return true;
			}
			return false;
		}

		@Override
		public Player choosePlayer(Player whitePlayer, Player blackPlayer) {

			return null;
		}
	};	
	
	public abstract boolean isBlack();
	public abstract boolean isWhite();
	public abstract boolean isSameAs(Piece p);
	public abstract Player choosePlayer(Player whitePlayer, Player blackPlayer);
}

