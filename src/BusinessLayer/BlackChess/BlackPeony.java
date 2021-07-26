package BusinessLayer.BlackChess;

import BusinessLayer.Board.Position;
import BusinessLayer.Tiles.BlackTile;
import BusinessLayer.Tiles.EmptyTile;
import BusinessLayer.Tiles.WhiteTile;

import java.util.LinkedList;
import java.util.List;

public class BlackPeony extends BlackTile {


    private boolean isPlayed = false;
    private static final String PEONY_TILE = "Pe";

    public BlackPeony(){
        super(PEONY_TILE, new LinkedList<>());
    }

    public List<Position> legalMoves(){
        List<Position> res = new LinkedList<>();
        if(!isPlayed){ res.add(getPosition().Down().Down()); }
        if(notOutOfBounds(getPosition().Down()) && legalStep(tileCallback.getByPosition(getPosition().Down()))){ res.add(getPosition().Down()); }
        if(notOutOfBounds(getPosition().Down().Left()) && tileCallback.getByPosition(getPosition().Down().Left()).getColor().equals(OPPOSITE_COLOR)){ res.add(getPosition().Down().Left()); }
        if(notOutOfBounds(getPosition().Down().Right()) && tileCallback.getByPosition(getPosition().Down().Right()).getColor().equals(OPPOSITE_COLOR)){ res.add(getPosition().Down().Right()); }
        return res;
    }

    @Override
    public void visit(WhiteTile w) {
        super.visit(w);
        isPlayed = true;
    }

    @Override
    public void visit(EmptyTile e) {
        super.visit(e);
        isPlayed = true;
    }

    @Override
    public boolean legalVisit(WhiteTile w) {
        return w.getPosition().Up().Left().equals(getPosition()) || w.getPosition().Up().Right().equals(getPosition());
    }
}
