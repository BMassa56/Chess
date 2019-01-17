package edu.vt.bmassa56.Chess2;

import edu.vt.bmassa56.Chess2.board.*;
import edu.vt.bmassa56.Chess2.pieces.*;
import edu.vt.bmassa56.Chess2.player.*;

public class TestClass {

	public static void main(String[] args) {
		
		Board b = new Board(true);
		System.out.print(b.toString());
		Pawn p = ((Pawn)b.getTileAt(4, 6).getPiece());
		System.out.print("\n Number of legal moves: " + p.getLegalMoves().size());
	}
}
