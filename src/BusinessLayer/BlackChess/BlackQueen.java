package BusinessLayer.BlackChess;

import BusinessLayer.Board.Position;
import BusinessLayer.Tiles.BlackTile;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

public class BlackQueen extends BlackTile {

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

    private static final String QUEEN_TILE = "Qu";

    public BlackQueen() {
        super(QUEEN_TILE, POSITIONS);
    }


}
