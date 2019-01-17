package edu.vt.bmassa56.Chess2.player;

import java.util.*;

import edu.vt.bmassa56.Chess2.Alliance;
import edu.vt.bmassa56.Chess2.board.Board;
import edu.vt.bmassa56.Chess2.board.Move;
import edu.vt.bmassa56.Chess2.board.Tile;
import edu.vt.bmassa56.Chess2.pieces.Piece;

public class BlackPlayer extends Player {

	public BlackPlayer(Collection<Move> whiteMoves, Collection<Move> blackMoves) {
		
		super(whiteMoves, blackMoves);
	}

	@Override
	public Collection<Piece> getActivePieces() {

		return Board.b.getBlackPieces();
	}

	@Override
	public Alliance getAlliance() {

		return Alliance.BLACK;
	}

	@Override
	public Player getOpponent() {

		return Board.b.getWhitePlayer();
	}
}
