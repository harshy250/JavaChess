package com.chess.engine.pieces;

import java.util.*;
import java.util.Collection;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.chess.engine.board.Move.AttackMove;
import com.chess.engine.board.Move.MajorMove;

public class Bishop extends Piece {
	
	private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES =  {-9, -7, 7, 9};
	
	public Bishop(final Alliance pieceAlliance, final int piecePosition) {
		super(piecePosition, pieceAlliance);
	}

	@Override
	public Collection<Move> calculateLegalMove(final Board board) {
		final List<Move> legalMoves = new ArrayList<>();
		for(final int candidateCoordinateOffset : CANDIDATE_MOVE_VECTOR_COORDINATES) {
			int candidateDestinationCoordinate = this.piecePosition;
			while(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
				candidateDestinationCoordinate += candidateCoordinateOffset;
				
				
				if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) { //valid tile
					if(isFirstColumnExclusion(this.piecePosition, candidateCoordinateOffset) ||
							isEighthColumnExclusion(this.piecePosition, candidateCoordinateOffset)) {
						continue;
					}
					final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
					if(!candidateDestinationTile.isTileOccupied()) {
						legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
					} else {
						final Piece pieceAtDestination = candidateDestinationTile.getPiece();
						final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
						if(this.pieceAlliance != pieceAlliance) {
							legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination)); //attacking move
						}
						break;
					}
				}	
			}
		}
		return legalMoves;
	}	
	@Override
	public String toString() {
		return PieceType.BISHOP.toString();
	}
	
	private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
		return BoardUtils.FIRST_COLUMN[currentPosition] && ((candidateOffset==-9) || (candidateOffset==7));
	}
	private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset) {
		return BoardUtils.EIGHTH_COLUMN[currentPosition] && ((candidateOffset==-7) || (candidateOffset==9));
	}
	
	
}