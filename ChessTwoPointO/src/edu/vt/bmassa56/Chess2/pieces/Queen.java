package edu.vt.bmassa56.Chess2.pieces;

import java.util.*;

import edu.vt.bmassa56.Chess2.Alliance;
import edu.vt.bmassa56.Chess2.board.Board;
import edu.vt.bmassa56.Chess2.board.Move;
import edu.vt.bmassa56.Chess2.board.Move.*;
import edu.vt.bmassa56.Chess2.board.Tile;

public class Queen extends Piece {

	/**
	 * Creates a new Queen
	 */
	public Queen(final Alliance a, 
			     final int xPos, final int yPos) {
		
		super(a, xPos, yPos);
		
		if (this.alliance.isBlack()) {
			
			png = "BlackQueen.png";
		}
		else {
			
			png = "WhiteQueen.png";
		}
		type = PieceType.QUEEN;
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
			else if (counter == 2) {
				
				for (int x = this.getX() + 1; x < 8; x++) {
					
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
			else if (counter == 3) {

				for (int y = this.getY() + 1; y < 8; y++) {
					
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
			else if (counter == 4) {
				
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
			else if (counter == 5) {
				
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
			else if (counter == 6) {
				
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
		} while (counter < 8);
		return list;
	}
	@Override
	public String toString() {
		
		if (this.alliance == Alliance.BLACK)
			return "q";
		
		return "Q";
	}

	@Override
	public Piece movePiece(Move move) {

		return new Queen(move.getPiece().getAlliance(), move.desiredTile().getX(),
						 move.desiredTile().getY());
	}
}
