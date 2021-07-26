package BusinessLayer.WhiteChess;

import BusinessLayer.Board.Position;
import BusinessLayer.Tiles.BlackTile;
import BusinessLayer.Tiles.EmptyTile;
import BusinessLayer.Tiles.WhiteTile;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class WhitePeony extends WhiteTile {

    private static final String PEONY_TILE = "Pe";

    private boolean isPlayed = false;
    public WhitePeony(){
        super(PEONY_TILE, new LinkedList<>());
    }


    public List<Position> legalMoves(){
        List<Position> res = new LinkedList<>();
        if(!isPlayed){
            res.add(getPosition().Up().Up());
        }
        if(notOutOfBounds(getPosition().Up()) && legalStep(tileCallback.getByPosition(getPosition().Up()))){
            res.add(getPosition().Up());
        }
        if(notOutOfBounds(getPosition().Up().Left()) && tileCallback.getByPosition(getPosition().Up().Left()).getColor().equals(OPPOSITE_COLOR)){
            res.add(getPosition().Up().Left());
        }
        if(notOutOfBounds(getPosition().Up().Right()) && tileCallback.getByPosition(getPosition().Up().Right()).getColor().equals(OPPOSITE_COLOR)){
            res.add(getPosition().Up().Right());
        }
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
    public boolean legalVisit(BlackTile b) {
        return b.getPosition().Down().Left().equals(getPosition()) || b.getPosition().Down().Right().equals(getPosition());
    }
}
