package com.chess.engine.pieces;

import java.util.*;
import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

public abstract class Piece {

	protected final PieceType pieceType;
	protected final int piecePosition;
	protected final Alliance pieceAlliance; //Black and White
	protected final boolean isFirstMove;
	
	
	Piece(final PieceType pieceType, final int piecePosition, final Alliance pieceAlliance){
		this.pieceType = pieceType;
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
	public PieceType getPieceType(){
		return this.pieceType;
	}

	public enum PieceType {

		
		PAWN("P"){
			@Override
			public boolean isKing(){
				return false;
			}
		},
		KNIGHT("N"){
			@Override
			public boolean isKing(){
				return false;
			}
		},
		BISHOP("B"){
			@Override
			public boolean isKing(){
				return false;
			}
		},
		ROOK("R"){
			@Override
			public boolean isKing(){
				return false;
			}
		},
		QUEEN("Q"){
			@Override
			public boolean isKing(){
				return false;
			}
		},
		KING("K"){
			@Override
			public boolean isKing(){
				return true;
			}
		};
		
		private String pieceName;
		
		PieceType(final String pieceName){
			 this.pieceName = pieceName;
		}
		
		@Override
		public String toString() {
			return this.pieceName;
		}

		public abstract boolean isKing();
	}
	
}
