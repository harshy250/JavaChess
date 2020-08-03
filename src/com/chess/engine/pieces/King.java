package com.chess.engine.pieces;

import java.util.*;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.chess.engine.board.Move.MajorMove;
import com.chess.engine.board.Move.AttackMove;

import static com.chess.engine.board.Move.*;

public class King extends Piece{
	
	private final static int[] CANDIDATE_MOVE_COORDINATE = { -9, -8, -7, -1, 1, 7, 8, 9};


	public King(final Alliance pieceAlliance, final int piecePosition) {
		super(PieceType.KING, piecePosition, pieceAlliance, true);
	}

	public King(final Alliance pieceAlliance, final int piecePosition, final boolean isFirstmove) {
		super(PieceType.KING, piecePosition, pieceAlliance, isFirstmove);
	}

	@Override
	public Collection<Move> calculateLegalMove(Board board) {
		final List<Move> legalMoves = new ArrayList<>();		
		for(final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE) {
			final int candidateDestinationCoordinate = this.piecePosition	+ currentCandidateOffset;
			
			if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
				final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);

				if(isFirstColumnExclusion(this.piecePosition, currentCandidateOffset) ||
						isEighthColumnExclusion(this.piecePosition, currentCandidateOffset)) {
					continue;
				}
				
				if(!candidateDestinationTile.isTileOccupied()) {
					legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
				} else {
					final Piece pieceAtDestination = candidateDestinationTile.getPiece();
					final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
					if(this.pieceAlliance != pieceAlliance) {
						legalMoves.add(new MajorAttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
					}		
				}
			}	
		}		
		return legalMoves;
	}
	@Override
	public String toString() {
		return PieceType.KING.toString();
	}

	@Override
	public King movePiece(final Move move) {
		return new King(move.getMovedPiece().getPieceAlliance(), move.getDestinationCoordinate());
	}
	
	private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
		return BoardUtils.FIRST_COLUMN[currentPosition] && ((candidateOffset==-9) || (candidateOffset==-1) || (candidateOffset==7) );
	}
	private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset) {
		return BoardUtils.EIGHTH_COLUMN[currentPosition] && ((candidateOffset==-7) || (candidateOffset==1) || (candidateOffset==9));
	}

}

