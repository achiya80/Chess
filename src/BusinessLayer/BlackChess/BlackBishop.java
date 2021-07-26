package BusinessLayer.BlackChess;

import BusinessLayer.Board.Position;
import BusinessLayer.Tiles.*;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

public class BlackBishop extends BlackTile {


    private static final List<Supplier<Position>> POSITIONS = new LinkedList<>(){
        {
            add(() -> new Position(1,1));
            add(() -> new Position(1,-1));
            add(() -> new Position(-1,1));
            add(() -> new Position(-1,-1));
        }
    };
    private static final String BISHOP_TILE = "Bi";

    public BlackBishop(){
        super(BISHOP_TILE, POSITIONS);
    }
}
