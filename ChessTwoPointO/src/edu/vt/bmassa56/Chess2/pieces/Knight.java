package edu.vt.bmassa56.Chess2.pieces;

import java.util.*;

import edu.vt.bmassa56.Chess2.Alliance;
import edu.vt.bmassa56.Chess2.board.Board;
import edu.vt.bmassa56.Chess2.board.Move;
import edu.vt.bmassa56.Chess2.board.Move.*;
import edu.vt.bmassa56.Chess2.board.Tile;

public class Knight extends Piece {

	/**
	 * Creates a new Knight
	 */
	public Knight(final Alliance a, 
				  final int xPos, final int yPos) {
		
		super(a, xPos, yPos);
		
		if (a.isBlack()) {
			
			png = "BlackKnight.png";
		}
		else {
			
			png = "WhiteKnight.png";
		}
		type = PieceType.KNIGHT;
	}
	/**
	 * Returns a list of legal moves for this piece
	 */
	@Override
	public List<Move> getLegalMoves() {

		final List<Move> list = new ArrayList<>();
		final List<Tile> temp = new ArrayList<>();
		
		final Tile temp1 = Board.b.getTileAt(this.getX() - 2, this.getY() - 1);
		final Tile temp2 = Board.b.getTileAt(this.getX() - 2, this.getY() + 1);
		final Tile temp3 = Board.b.getTileAt(this.getX() - 1, this.getY() - 2);
		final Tile temp4 = Board.b.getTileAt(this.getX() - 1, this.getY() + 2);
		final Tile temp5 = Board.b.getTileAt(this.getX() + 1, this.getY() - 2);
		final Tile temp6 = Board.b.getTileAt(this.getX() + 1, this.getY() + 2);
		final Tile temp7 = Board.b.getTileAt(this.getX() + 2, this.getY() - 1);
		final Tile temp8 = Board.b.getTileAt(this.getX() + 2, this.getY() + 1);
		
		temp.add(temp1);
		temp.add(temp2);
		temp.add(temp3);
		temp.add(temp4);
		temp.add(temp5);
		temp.add(temp6);
		temp.add(temp7);
		temp.add(temp8);
		
		for (final Tile t: temp) {
			
			if (t != null && !t.hasPiece()) {
				list.add(new RegularMove(this, this.getTile(), t));
			}
			else if (t != null &&
					!t.getPiece().getAlliance().isSameAs(this)) {
				list.add(new AttackMove(this, this.getTile(), 
						                t, t.getPiece()));
			}
		}
		return list;
	}
	@Override
	public String toString() {
		
		if (this.alliance == Alliance.BLACK)
			return "n";
		
		return "N";
	}
	@Override
	public Piece movePiece(Move move) {

		return new Knight(move.getPiece().getAlliance(), move.desiredTile().getX(),
						  move.desiredTile().getY());
	}
}
