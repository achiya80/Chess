package BusinessLayer.BlackChess;

import BusinessLayer.Board.Position;
import BusinessLayer.Tiles.BlackTile;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

public class BlackKnight extends BlackTile {

    private static final String KNIGHT_TILE = "Kn";

    private static final List<Supplier<Position>> POSITIONS = new LinkedList<>(){
        {
            add(() -> new Position(2,1));
            add(() -> new Position(2,-1));
            add(() -> new Position(1,2));
            add(() -> new Position(-1,2));
            add(() -> new Position(1,-2));
            add(() -> new Position(-1,-2));
            add(() -> new Position(-2,1));
            add(() -> new Position(-2,-1));
        }
    };

    public BlackKnight() {
        super(KNIGHT_TILE ,POSITIONS);
    }


    public List<Position> legalMoves(){
        List<Position> res = new LinkedList<>();
        POSITIONS.stream().forEach(p -> addIfLegal(advancePosition(p.get(), getPosition()), res));
        return res;
    }
    private void addIfLegal(Position p, List<Position> res){
        if(notOutOfBounds(p) && legalStep(tileCallback.getByPosition(p))){
            res.add(p);
        }
    }

}
