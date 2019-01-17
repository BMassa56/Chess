package edu.vt.bmassa56.Chess2.pieces;

import java.util.*;

import edu.vt.bmassa56.Chess2.Alliance;
import edu.vt.bmassa56.Chess2.board.Board;
import edu.vt.bmassa56.Chess2.board.Move;
import edu.vt.bmassa56.Chess2.board.Move.*;
import edu.vt.bmassa56.Chess2.board.Tile;

public class Rook extends Piece {

	/**
	 * Creates a new Rook
	 */
	public Rook(final Alliance a, 
			    final int xPos, final int yPos) {
		
		super(a, xPos, yPos);
		if (a.isBlack()) {
			
			png = "BlackRook.png";
		}
		else {
			
			png = "WhiteRook.png";
		}
		type = PieceType.ROOK;
	}

	@Override
	public List<Move> getLegalMoves() {
		
		final List<Move> list = new ArrayList<>();
		int counter = 0;
		
		do {
			if (counter == 0) {
				
				for (int x = this.getX() - 1; x >= 0; x--) {
					
					final Tile temp = Board.b.getTileAt(x, this.getY());
					if (temp != null &&
							!temp.hasPiece()) {
						
						list.add(new RegularMove(this, this.getTile(), temp));
					}
					else if (temp != null &&
							!temp.getPiece().getAlliance().isSameAs(this)) {
						
						list.add(new AttackMove(this, this.getTile(),
								                temp, temp.getPiece()));
						break;
					}
					else {
						
						break;
					}
				}
			}
			else if (counter == 1) {
				
				for (int y = this.getY() - 1; y >= 0; y--) {
					
					final Tile temp = Board.b.getTileAt(this.getX(), y);
					if (temp != null && !temp.hasPiece()) {
						
						list.add(new RegularMove(this, this.getTile(), temp));
					}
					else if (temp != null && 
							!temp.getPiece().getAlliance().isSameAs(this)) {
						
						list.add(new AttackMove(this, this.getTile(), 
								                temp, temp.getPiece()));
						break;
					}
					else {
						
						break;
					}
				}
			}
			else if (counter == 2) {
				
				for (int x = this.getX() + 1; x < 8; x++) {
					
					final Tile temp = Board.b.getTileAt(x, this.getY());
					if (temp != null && !temp.hasPiece()) {
						
						list.add(new RegularMove(this, this.getTile(), temp));
					}
					else if (temp != null && 
							!temp.getPiece().getAlliance().isSameAs(this)) {
						
						list.add(new AttackMove(this, this.getTile(),
								                temp, temp.getPiece()));
						break;
					}
					else {
						
						break;
					}
				}
			}
			else {

				for (int y = this.getY() + 1; y < 8; y++) {
					
					final Tile temp = Board.b.getTileAt(this.getX(), y);
					if (temp != null &&
							!temp.hasPiece()) {
						
						list.add(new RegularMove(this, this.getTile(), temp));
					}
					else if (temp != null && 
							!temp.getPiece().getAlliance().isSameAs(this)) {
						
						list.add(new AttackMove(this, this.getTile(), 
								                temp, temp.getPiece()));
						break;
					}
					else {
						
						break;
					}
				}
			}
			counter++;
			
		} while (counter < 4);
		
		return list;
	}
	@Override
	public String toString() {
		
		if (this.alliance == Alliance.BLACK)
			return "r";
		return "R";
	}

	@Override
	public Piece movePiece(final Move move) {

		return new Rook(move.getPiece().getAlliance(), move.desiredTile().getX(),
						move.desiredTile().getY());
	}
}
