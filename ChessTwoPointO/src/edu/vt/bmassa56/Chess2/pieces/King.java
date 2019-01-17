package edu.vt.bmassa56.Chess2.pieces;

import java.util.*;

import edu.vt.bmassa56.Chess2.Alliance;
import edu.vt.bmassa56.Chess2.board.Board;
import edu.vt.bmassa56.Chess2.board.Move;
import edu.vt.bmassa56.Chess2.board.Move.*;
import edu.vt.bmassa56.Chess2.board.Tile;

public class King extends Piece {

	/**
	 * Creates a King piece
	 */
	public King(final Alliance a, 
				final int xPos, final int yPos) {
		
		super(a, xPos, yPos);
		
		if (a.isBlack()) {
			
			png = "BlackKing.png";
		}
		else {
			
			png = "WhiteKing.png";
		}
		type = PieceType.KING;
	}

	@Override
	public List<Move> getLegalMoves() {

		final List<Move> list = new ArrayList<>();
		
		for (int y = this.getY() - 1; y < this.getY() + 2; y++) {
			
			for (int x = this.getX() - 1; x < this.getX() + 2; x++) {
				
				if (x == y) {
					x++;
				}
				final Tile temp = Board.b.getTileAt(x, y);
				if (temp != null && !temp.hasPiece()) {
					list.add(new RegularMove(this, this.getTile(), temp));
				}
				else if (temp != null && 
						!temp.getPiece().getAlliance().isSameAs(this)) {
					list.add(new AttackMove(this, this.getTile(), 
							                temp, temp.getPiece()));
				}
			}
		}
		return list;
	}
	@Override
	public String toString() {
		
		if (this.alliance == Alliance.BLACK)
			return "k";
		
		return "K";
	}

	@Override
	public Piece movePiece(Move move) {

		return new King(move.getPiece().getAlliance(), move.desiredTile().getX(),
						move.desiredTile().getY());
	}
}
