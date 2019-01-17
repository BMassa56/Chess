package edu.vt.bmassa56.Chess2.player;

import edu.vt.bmassa56.Chess2.board.Board;
import edu.vt.bmassa56.Chess2.board.Move;

public class MoveTransition {

	private final Board transitionBoard;
	private final Move move;
	private final MoveStatus status;
	
	public MoveTransition(final Board tBoard,
						  final Move m,
						  final MoveStatus s) {
		
		transitionBoard = tBoard;
		move = m;
		status = s;
	}

	public MoveStatus getMoveStatus() {

		return this.status;
	}
}
