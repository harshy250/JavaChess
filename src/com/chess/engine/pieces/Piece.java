package com.chess.engine.pieces;

import java.util.*;
import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

public abstract class Piece {
	
	protected final int piecePosition;
	protected final Alliance pieceAlliance; //Black and White
	protected final boolean isFirstMove;
	
	
	Piece(final int piecePosition, final Alliance pieceAlliance){
		this.piecePosition = piecePosition;
		this.pieceAlliance = pieceAlliance;
		//More Work Here
		this.isFirstMove = false;
		
	}
	
	public Alliance getPieceAlliance() {
		return this.pieceAlliance;
	}
	
	public boolean isFirstMove() {
		return this.isFirstMove;
	}
	
	public abstract Collection<Move> calculateLegalMove(final Board board);

	public int getPiecePosition() {
		return this.piecePosition;
	}
	
	public enum PieceType {
		
		PAWN("P"),
		KNIGHT("N"),
		BISHOP("B"),
		ROOK("R"),
		QUEEN("Q"),
		KING("K");
		
		private String pieceName;
		
		PieceType(final String pieceName){
			 this.pieceName = pieceName;
		}
		
		@Override
		public String toString() {
			return this.pieceName;
		}
	}
	
}
