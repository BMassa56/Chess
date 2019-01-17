package edu.vt.bmassa56.Chess2.pieces;

import java.util.*;

import edu.vt.bmassa56.Chess2.Alliance;
import edu.vt.bmassa56.Chess2.board.Board;
import edu.vt.bmassa56.Chess2.board.Move;
import edu.vt.bmassa56.Chess2.board.Move.*;
import edu.vt.bmassa56.Chess2.board.Tile;

public class Pawn extends Piece {

	/**
	 * Creates a new Pawn
	 */
	public Pawn(final Alliance a, 
			    final int xPos, final int yPos) {
		
		super(a, xPos, yPos);
		if (a.isBlack()) {
			
			png = "BlackPawn.png";
		}
		else {
			
			png = "WhitePawn.png";
		}
		type = PieceType.PAWN;
	}

	@Override
	public List<Move> getLegalMoves() {

		final List<Move> list = new ArrayList<>();
		final int offset;
		final boolean notMoved;
		
		if (this.getAlliance().isBlack()) {
			if (this.getY() == 1) {
				notMoved = true;
			}
			else {
				notMoved = false;
			}
			offset = 1;
		}
		else {
			if (this.getY() == 6) {
				notMoved = true;
			}
			else {
				notMoved = false;
			}
			offset = -1;
		}
		final Tile temp1 = Board.b.getTileAt(this.getX(), this.getY() + offset);
		final Tile temp2 = Board.b.getTileAt(this.getX() - 1, this.getY() + offset);
		final Tile temp3 = Board.b.getTileAt(this.getX() + 1, this.getY() + offset);
		
		if (temp1 != null && !temp1.hasPiece()) {
			list.add(new PawnMove(this, this.getTile(), temp1));
		}
		if (temp2 != null && temp2.hasPiece()) {
			if (!temp2.getPiece().getAlliance().isSameAs(this)) {
				list.add(new PawnAttackMove(this, this.getTile(), 
						                    temp2, temp2.getPiece()));
			}
		}
		if (temp3 != null && temp3.hasPiece()) {
			if (!temp3.getPiece().getAlliance().isSameAs(this)) {
				list.add(new PawnAttackMove(this, this.getTile(), 
						                    temp3, temp3.getPiece()));
			}
		}
		if (notMoved) {
			if (temp1 != null && !temp1.hasPiece()) {
				
				final Tile temp4 = Board.b.getTileAt(this.getX(), this.getY() + (offset * 2));
				if (temp4 != null && !temp4.hasPiece()) {
					list.add(new PawnMove(this, this.getTile(), temp4));
				}
			}
		}
		return list;
	}
	@Override
	public String toString() {
		
		if (this.alliance == Alliance.BLACK)
			return "p";
		
		return "P";
	}

	@Override
	public Piece movePiece(Move move) {

		return new Pawn(move.getPiece().getAlliance(), move.desiredTile().getX(),
					    move.desiredTile().getY());
	}
}
