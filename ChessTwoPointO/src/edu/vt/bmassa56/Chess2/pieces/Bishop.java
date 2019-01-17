package edu.vt.bmassa56.Chess2.pieces;

import java.util.*;

import edu.vt.bmassa56.Chess2.Alliance;
import edu.vt.bmassa56.Chess2.board.Board;
import edu.vt.bmassa56.Chess2.board.Move;
import edu.vt.bmassa56.Chess2.board.Move.*;
import edu.vt.bmassa56.Chess2.board.Tile;

public class Bishop extends Piece {

	/**
	 * Creates a new Bishop
	 */
	public Bishop(final Alliance a, 
				  final int xPos, final int yPos) {
		
		super(a, xPos, yPos);
		
		if (a.isBlack()) {
			
			png = "BlackBishop.png";
		}
		else {
			
			png = "WhiteBishop.png";
		}
		type = PieceType.BISHOP;
	}
	/**
	 * Returns a list of legal moves
	 */
	@Override
	public List<Move> getLegalMoves() {
		
		final List<Move> list = new ArrayList<>();
		
		int counter = 0;
		
		do {
			
			if (counter == 0) {
				
				int yCounter = this.getY() - 1;
				int xCounter = this.getX() - 1;
				
				while (yCounter > 0 && xCounter > 0) {
					
					final Tile temp = Board.b.getTileAt(xCounter, yCounter);
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
					yCounter--;
					xCounter--;
				}
			}
			else if (counter == 1) {
				
				int yCounter = this.getY() - 1;
				int xCounter = this.getX() + 1;
				
				while (yCounter > 0 && xCounter < 8) {
					
					final Tile temp = Board.b.getTileAt(xCounter, yCounter);
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
					yCounter--;
					xCounter++;
				}
			}
			else if (counter == 2) {
				
				int yCounter = this.getY() + 1;
				int xCounter = this.getX() + 1;
				
				while (yCounter < 8 && xCounter < 8) {
					
					final Tile temp = Board.b.getTileAt(xCounter, yCounter);
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
					yCounter++;
					xCounter++;
				}
			}
			else {
				
				int yCounter = this.getY() + 1;
				int xCounter = this.getX() - 1;
				
				while (yCounter < 8 && xCounter > 0) {
					
					final Tile temp = Board.b.getTileAt(xCounter, yCounter);
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
					yCounter++;
					xCounter--;
				}
			}
			counter++;
		} while (counter < 4);
		return list;
	}
	@Override
	public String toString() {
		
		if (this.alliance == Alliance.BLACK)
			return "b";
		
		return "B";
	}
	@Override
	public Piece movePiece(Move move) {

		return new Bishop(move.getPiece().getAlliance(), 
						  move.desiredTile().getX(), move.desiredTile().getY());
							
	}
}
