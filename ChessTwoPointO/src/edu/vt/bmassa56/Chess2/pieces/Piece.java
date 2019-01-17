package edu.vt.bmassa56.Chess2.pieces;

import java.util.List;

import edu.vt.bmassa56.Chess2.Alliance;
import edu.vt.bmassa56.Chess2.board.Board;
import edu.vt.bmassa56.Chess2.board.Move;
import edu.vt.bmassa56.Chess2.board.Tile;

public abstract class Piece {

	protected String png;
	protected Alliance alliance;
	protected PieceType type;
	protected boolean isFirstMove;
	protected int xCoordinate;
	protected int yCoordinate;
	/**
	 * Creates a new piece
	 */
	public Piece(Alliance a, int xVal, int yVal) {
		
		alliance = a;
		xCoordinate = xVal;
		yCoordinate = yVal;
		//TODO implement isFirstMove
		isFirstMove = false;
	}
	/**
	 * Returns the color of this piece
	 */
	public Alliance getAlliance() {
		
		return alliance;
	}
	/**
	 * Returns the String for the png file
	 */
	public String getPNGString() {
		
		return png;
	}
	/**
	 * Returns the x value of this piece
	 */
	public int getX() {
		
		return xCoordinate;
	}
	/**
	 * Returns the y value of this piece
	 */
	public int getY() {
		
		return yCoordinate;
	}
	/**
	 * Returns the type of this piece
	 */
	public PieceType getType() {
		
		return type;
	}
	/**
	 * Returns the tile that this piece is occupying
	 */
	public Tile getTile() {
		
		return Board.b.getTileAt(this.xCoordinate, this.yCoordinate);
	}
	/**
	 * Returns true if it is the pieces first move
	 */
	public boolean isFirstMove() {
		
		return this.isFirstMove;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Piece other = (Piece) obj;
		if (alliance != other.alliance)
			return false;
		if (isFirstMove != other.isFirstMove)
			return false;
		if (png == null) {
			if (other.png != null)
				return false;
		} else if (!png.equals(other.png))
			return false;
		if (type != other.type)
			return false;
		if (xCoordinate != other.xCoordinate)
			return false;
		if (yCoordinate != other.yCoordinate)
			return false;
		return true;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alliance == null) ? 0 : alliance.hashCode());
		result = prime * result + (isFirstMove ? 1231 : 1237);
		result = prime * result + ((png == null) ? 0 : png.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + xCoordinate;
		result = prime * result + yCoordinate;
		return result;
	}
	public abstract List<Move> getLegalMoves();
	
	public abstract Piece movePiece(Move move);
	
	@Override
	public abstract String toString();
}
