package edu.vt.bmassa56.Chess2.player;

public enum MoveStatus {

	DONE {
		@Override
		public boolean isDone() {

			return true;
		}
	},
	ILLEGAL {
		@Override
		public boolean isDone() {
			
			return false;
		}
	}, 
	LEGAL {
		@Override
		public boolean isDone() {
			
			return false;
		}
	}, 
	PLAYER_IN_CHECK {
		@Override
		public boolean isDone() {
			
			return false;
		}
	};
	public abstract boolean isDone();
}
