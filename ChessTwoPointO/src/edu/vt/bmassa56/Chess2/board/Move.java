package edu.vt.bmassa56.Chess2.board;

import edu.vt.bmassa56.Chess2.pieces.Piece;

public abstract class Move {

	private final Tile from;
	private final Tile to;
	private final Piece piece;
	public static final Move ILLEGAL_MOVE = new IllegalMove();
	
	public Move(final Piece movedPiece, 
				final Tile moveFrom, final Tile moveTo) {
		
		from = moveFrom;
		to = moveTo;
		piece = movedPiece;
	}
	/**
	 * Returns the tile the move is from
	 */
	public Tile originalTile() {
		
		return from;
	}
	/**
	 * Returns the tile the move is to
	 */
	public Tile desiredTile() {
		
		return to;
	}
	/**
	 * Returns the piece of this move
	 */
	public Piece getPiece() {
		
		return piece;
	}
	public Board execute() {

		Board initialBoard = Board.b;
		final Board newBoard = new Board(false);
		
		for (final Piece p: initialBoard.currentPlayer().getActivePieces()) {
			
			if (!this.piece.equals(p)) {
				newBoard.setPiece(p);
			}
		}
		for (final Piece p: initialBoard.currentPlayer().getOpponent().getActivePieces()) {
			newBoard.setPiece(p);
		}
		final Piece temp = piece.movePiece(this);
		newBoard.setPiece(temp);
		newBoard.setMoveMaker(newBoard.currentPlayer().getOpponent().getAlliance());
		
		return newBoard;
	}
	
	public static final class RegularMove extends Move {
		
		public RegularMove(final Piece p, final Tile moveFrom,
					       final Tile moveTo) {
			super(p, moveFrom, moveTo);
		}
	}
	
	public static class AttackMove extends Move {
		
		private Piece attackedPiece;
		/**
		 * Creates a new attack move
		 */
		public AttackMove(final Piece p, final Tile moveFrom,
					      final Tile moveTo, final Piece aP) {
			super(p, moveFrom, moveTo);
			attackedPiece = aP;
		}
	}
	
	public static final class PawnMove extends Move {
		
		public PawnMove(final Piece p, final Tile moveFrom,
					    final Tile moveTo) {
			super(p, moveFrom, moveTo);
		}
	}
	
	public static class PawnAttackMove extends AttackMove {
		
		/**
		 * Created when a pawn attacks
		 */
		public PawnAttackMove(final Piece p, final Tile moveFrom,
							  final Tile moveTo, final Piece aP) {
			super(p, moveFrom, moveTo, aP);
			
		}
	}
	
	public static final class PawnEnPassant extends PawnAttackMove {
		
		public PawnEnPassant(final Piece p, final Tile moveFrom,
				             final Tile moveTo, final Piece aP) {
			super(p, moveFrom, moveTo, aP);
		}
	}
	
	public static abstract class Castle extends Move {
		
		public Castle(final Piece p, final Tile moveFrom,
				      final Tile moveTo) {
			super(p, moveFrom, moveTo);
		}
	}
	
	public static final class QueenSideCastle extends Castle {

		public QueenSideCastle(final Piece p, final Tile moveFrom, 
				               final Tile moveTo) {
			super(p, moveFrom, moveTo);
		}
	}
	
	public static final class KingSideCastle extends Castle {
		
		public KingSideCastle(final Piece p, final Tile moveFrom,
							  final Tile moveTo) {
			super(p, moveFrom, moveTo);
		}
	}
	
	public static final class IllegalMove extends Move {
		
		public IllegalMove() {
			super(null, null, null);
		}
		@Override
		public Board execute() {
			throw new RuntimeException("Illegal Move ya dumbass");
		}
	}
	
	public static class moveFactory {
		
		moveFactory() {
			
			throw new RuntimeException("Don't make me into an object you prick");
		}
		public static Move createMove(final Tile tileFrom, final Tile tileTo) {
			
			for (final Move move: Board.b.getAllLegalMoves()) {
				
				if (move.originalTile() == tileFrom) {
					
					return move;
				}
			}
			return ILLEGAL_MOVE;
		}
	}
}

