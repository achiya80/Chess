package BusinessLayer.BlackChess;

import BusinessLayer.Board.Position;
import BusinessLayer.Tiles.BlackTile;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

public class BlackKing extends BlackTile {

    private static final String KING_TILE = "Ki";

    private static final List<Supplier<Position>> POSITIONS = new LinkedList<>(){
        {
            add(() -> new Position(1,1));
            add(() -> new Position(1,-1));
            add(() -> new Position(-1,1));
            add(() -> new Position(-1,-1));
            add(() -> new Position(1,0));
            add(() -> new Position(0,-1));
            add(() -> new Position(-1,0));
            add(() -> new Position(0,1));
        }
    };

    public BlackKing(){
        super(KING_TILE, POSITIONS);
    }

    public List<Position> legalMoves(){
        List<Position> res = new LinkedList<>();
        bound(res);
        return res;
    }

    private void bound(List<Position> res){
        for (Supplier<Position> p : POSITIONS) {
            if (notOutOfBounds(advancePosition(p.get(), getPosition())) && legalStep(tileCallback.getByPosition(advancePosition(p.get(),getPosition())))) {
                res.add(advancePosition(p.get(),getPosition()));
            }
        }
    }
}
