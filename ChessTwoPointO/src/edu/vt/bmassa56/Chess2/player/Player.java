package edu.vt.bmassa56.Chess2.player;

import java.util.*;

import edu.vt.bmassa56.Chess2.Alliance;
import edu.vt.bmassa56.Chess2.board.Board;
import edu.vt.bmassa56.Chess2.board.Move;
import edu.vt.bmassa56.Chess2.board.Tile;
import edu.vt.bmassa56.Chess2.pieces.*;

public abstract class Player {

	protected final King playerKing;
	protected final Collection<Move> legalMoves;
	private final boolean isInCheck;

	public Player(Collection<Move> legalMoves, Collection<Move> enemyLegalMoves) {
		
		playerKing = chooseKing();
		this.legalMoves = legalMoves;
		isInCheck = !Player.calculateAttacksOnTile(this.playerKing.getTile(), 
					 enemyLegalMoves).isEmpty();
	}

	private static Collection<Move> calculateAttacksOnTile(Tile tile, Collection<Move> moves) {

		final List<Move> list = new ArrayList<>();
		
		for (final Move m: moves) {
			
			if (tile.equals(m.desiredTile())) {
				
				list.add(m);
			}
		}
		return list;
	}
	public Collection<Move> getLegalMoves() {
		
		return this.legalMoves;
	}
	public King chooseKing() {

		for (Piece p: getActivePieces()) {
			
			if (p.getType() == PieceType.KING) {
				
				return ((King) p);
			}
		}
		throw new RuntimeException("This board is invalid");
	}
	public King getKing() {
		
		return playerKing;
	}
	public boolean isMoveLegal(Move m) {
		
		return this.legalMoves.contains(m);
	}
	/**
	 * Checks for check, checkmate, and stalemate
	 */
	public boolean isInCheck() {
		
		return this.isInCheck;
	}
	public boolean isInCheckmate() {
		
		return this.isInCheck && hasEscapeMoves();
	}
	public boolean isInStalemate() {
		
		return !this.isInCheck && hasEscapeMoves();
	}
	
	protected boolean hasEscapeMoves() {

		for (final Move m: this.legalMoves) {
			
			final MoveTransition transition = makeMove(m);
			if (transition.getMoveStatus().isDone()) {
				
				return true;
			}
		}
		return false;
	}
	public boolean isCastled() {
		
		return false;
	}
	public MoveTransition makeMove(Move move) {
		
		if (!isMoveLegal(move)) {
			
			return new MoveTransition(Board.b, move, MoveStatus.ILLEGAL);
		}
		final Board transitionBoard = move.execute();
		
		final Collection<Move> kingAttacks = 
				Player.calculateAttacksOnTile(transitionBoard.currentPlayer()
						.getOpponent().getKing().getTile(), 
						transitionBoard.currentPlayer().getLegalMoves());
		
		if (!kingAttacks.isEmpty()) {
			return new MoveTransition(Board.b, move, MoveStatus.PLAYER_IN_CHECK);
		}
		return new MoveTransition(transitionBoard, move, MoveStatus.DONE);
	}
	public abstract Collection<Piece> getActivePieces();
	public abstract Alliance getAlliance();
	public abstract Player getOpponent();
}
