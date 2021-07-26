package BusinessLayer.WhiteChess;

import BusinessLayer.Board.Position;
import BusinessLayer.Tiles.WhiteTile;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

public class WhiteTorre extends WhiteTile {
    private static final String TORRE_TILE = "To";

    private static final List<Supplier<Position>> POSITIONS = new LinkedList<>(){
        {
            add(() -> new Position(1,0));
            add(() -> new Position(0,-1));
            add(() -> new Position(-1,0));
            add(() -> new Position(0,1));
        }
    };
    public WhiteTorre(){
        super(TORRE_TILE, POSITIONS);
    }

}
