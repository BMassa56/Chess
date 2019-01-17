package edu.vt.bmassa56.Chess2.board;

import edu.vt.bmassa56.Chess2.pieces.Piece;

public class Tile {
	
	private final int x;
	private final int y;
	/**
	 * Creates a new Tile at x, y
	 */
	public Tile(final int xCoord, final int yCoord) {
		
		x = xCoord;
		y = yCoord;
	}
	/**
	 * Gets the x coordinate
	 */
	public int getX() {
		
		return x;
	}
	/**
	 * Gets the y coordinate
	 */
	public int getY() {
		
		return y;
	}
	/**
	 * Checks if this tile has a piece
	 */
	public boolean hasPiece() {
		
		if (Board.b.getPieces()[x][y] != null) {
			
			return true;
		}
		return false;
	}
	/**
	 * Returns the piece on this tile, or null if there is no piece
	 */
	public Piece getPiece() {
		
		if (this.hasPiece()) {
			
			return Board.b.getPieces()[x][y];
		}
		return null;
	}
	@Override
	public String toString() {
		
		if (!this.hasPiece()) {
			
			return "-";
		}
		else {
			
			return this.getPiece().toString();
		}
	}
}
