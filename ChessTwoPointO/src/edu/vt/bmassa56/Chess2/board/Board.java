package edu.vt.bmassa56.Chess2.board;

import java.util.*;
import java.util.List;

import edu.vt.bmassa56.Chess2.Alliance;
import edu.vt.bmassa56.Chess2.pieces.*;
import edu.vt.bmassa56.Chess2.player.*;

import java.awt.*;

public class Board {
	
	private final Tile[][] tiles;
	private Piece[][] pieces;
	private final Collection<Piece> whitePieces;
	private final Collection<Piece> blackPieces;
	private final Collection<Move> whiteLegalMoves;
	private final Collection<Move> blackLegalMoves;
	private final WhitePlayer whitePlayer;
	private final BlackPlayer blackPlayer;
	private final Player currentPlayer;
	private Alliance nextMoveMaker;
	public static Board b;
	/**
	 * Creates a new chess board
	 */
	public Board(boolean setPieces) {
		
		tiles = new Tile[8][8];
		pieces = new Piece[8][8];
		b = this;
		nextMoveMaker = Alliance.WHITE;
		createTiles();
		if (setPieces) {
			createBoard();
		}
		this.whitePieces = calculateActivePieces(this.pieces, Alliance.WHITE);
		this.blackPieces = calculateActivePieces(this.pieces, Alliance.BLACK);
		
		whiteLegalMoves = calculateLegalMoves(this.whitePieces);
		blackLegalMoves = calculateLegalMoves(this.blackPieces);
		
		whitePlayer = new WhitePlayer(whiteLegalMoves, blackLegalMoves);
		blackPlayer = new BlackPlayer(blackLegalMoves, whiteLegalMoves);
		
		this.currentPlayer = nextMoveMaker.choosePlayer(this.whitePlayer,
														this.blackPlayer);
	}
	/**
	 * Returns all the legal moves for the type of piece
	 */
	private Collection<Move> calculateLegalMoves(final Collection<Piece> pieces) {

		final List<Move> moveList = new ArrayList<>();
		
		for (final Piece p: pieces) {
			
			moveList.addAll(p.getLegalMoves());
		}
		return moveList;
	}
	/**
	 * Calculates active pieces of Alliance alliance
	 */
	private static Collection<Piece> calculateActivePieces(final Piece[][] pieceList, 
														   final Alliance alliance) {

		final List<Piece> activePieces = new ArrayList<>();
		for (final Piece[] piecelist: pieceList) {
			
			for (final Piece p: piecelist) {
				
				if (p != null) {
					
					if (p.getAlliance() == alliance) {
						
						activePieces.add(p);
					}
				}
			}
		}
		return activePieces;
	}
	/**
	 * Returns all legal moves for all players
	 */
	public Collection<Move> getAllLegalMoves() {
		
		final List<Move> temp = new ArrayList<>();
		
		for (Move m: this.whiteLegalMoves) {
			
			temp.add(m);
		}
		for (Move m: this.blackLegalMoves) {
			
			temp.add(m);
		}
		return temp;
	}
	public Collection<Piece> getBlackPieces() {
		
		return this.blackPieces;
	}
	public Collection<Piece> getWhitePieces() {
		
		return this.whitePieces;
	}
	/**
	 * Builds the tileList array
	 */
	public void createTiles() {
		
		for (int y = 0; y < 8; y++) {
			
			for (int x = 0; x < 8; x++) {
				
				tiles[x][y] = new Tile(x, y);
			}
		}
	}
	/**
	 * Sets a piece at its location
	 */
	public void setPiece(final Piece p) {
		
		pieces[p.getX()][p.getY()] = p;
	}
	/**
	 * Returns the White Player
	 */
	public Player getWhitePlayer() {
		
		return whitePlayer;
	}
	/**
	 * Returns the Black Player
	 */
	public Player getBlackPlayer() {
		
		return blackPlayer;
	}
	/**
	 * Returns the current player
	 */
	public Player currentPlayer() {
		
		return this.currentPlayer;
	}
	/**
	 * Returns the tile at x, y
	 */
	public Tile getTileAt(final int xVal, final int yVal) {
		
		if (xVal < 0 || xVal > 7 || yVal < 0 || yVal > 7) {
			
			return null;
		}
		return tiles[xVal][yVal];
	}
	/**
	 * Returns the pieces array
	 */
	public Piece[][] getPieces() {
		
		return pieces;
	}
	public Tile[][] getTiles() {
		
		return tiles;
	}
	/**
	 * Sets the pieces for the board
	 */
	public void createBoard() {
		
		this.setPiece(new Rook(Alliance.BLACK, 0, 0));
		this.setPiece(new Knight(Alliance.BLACK, 1, 0));
		this.setPiece(new Bishop(Alliance.BLACK, 2, 0));
		this.setPiece(new Queen(Alliance.BLACK, 3, 0));
		this.setPiece(new King(Alliance.BLACK, 4, 0));
		this.setPiece(new Bishop(Alliance.BLACK, 5, 0));
		this.setPiece(new Knight(Alliance.BLACK, 6, 0));
		this.setPiece(new Rook(Alliance.BLACK, 7, 0));
		
		for (int i = 0; i < 8; i++) {
			this.setPiece(new Pawn(Alliance.BLACK, i, 1));
			this.setPiece(new Pawn(Alliance.WHITE, i, 6));
		}
		
		this.setPiece(new Rook(Alliance.WHITE, 0, 7));
		this.setPiece(new Knight(Alliance.WHITE, 1, 7));
		this.setPiece(new Bishop(Alliance.WHITE, 2, 7));
		this.setPiece(new Queen(Alliance.WHITE, 3, 7));
		this.setPiece(new King(Alliance.WHITE, 4, 7));
		this.setPiece(new Bishop(Alliance.WHITE, 5, 7));
		this.setPiece(new Knight(Alliance.WHITE, 6, 7));
		this.setPiece(new Rook(Alliance.WHITE, 7, 7));
	}
	/**
	 * Prints out a board into a string
	 */
	@Override
	public String toString() {
		
		final StringBuilder sBuilder = new StringBuilder();
		for (int y = 0; y < 8; y++) {
			
			for (int x = 0; x < 8; x++) {
				final String tileText = this.tiles[x][y].toString();
				sBuilder.append(String.format("%3s", tileText));
			}
			sBuilder.append("\n");
		}
		return sBuilder.toString();
	}
	public void setMoveMaker(Alliance alliance) {

		this.nextMoveMaker = alliance;
	}
}

