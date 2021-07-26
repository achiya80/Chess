package BusinessLayer.WhiteChess;

import BusinessLayer.Board.Position;
import BusinessLayer.Tiles.WhiteTile;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

public class WhiteQueen extends WhiteTile {

    private static final List<Supplier<Position>> POSITIONS = new LinkedList<>() {
        {
            add(() -> new Position(1, 1));
            add(() -> new Position(1, -1));
            add(() -> new Position(-1, 1));
            add(() -> new Position(-1, -1));
            add(() -> new Position(1, 0));
            add(() -> new Position(0, -1));
            add(() -> new Position(-1, 0));
            add(() -> new Position(0, 1));
        }
    };
    private static final String QUEEN_TILE = "Qu";

    public WhiteQueen() {
        super(QUEEN_TILE, POSITIONS);
    }

}