package edu.vt.bmassa56.Chess2.player;

import java.util.*;

import edu.vt.bmassa56.Chess2.Alliance;
import edu.vt.bmassa56.Chess2.board.Board;
import edu.vt.bmassa56.Chess2.board.Move;
import edu.vt.bmassa56.Chess2.board.Tile;
import edu.vt.bmassa56.Chess2.pieces.Piece;

public class WhitePlayer extends Player {

	public WhitePlayer(Collection<Move> whiteMoves, Collection<Move> blackMoves) {
		
		super(whiteMoves, blackMoves);
	}

	@Override
	public Collection<Piece> getActivePieces() {

		return Board.b.getWhitePieces();
	}

	@Override
	public Alliance getAlliance() {

		return Alliance.WHITE;
	}

	@Override
	public Player getOpponent() {

		return Board.b.getBlackPlayer();
	}
}
