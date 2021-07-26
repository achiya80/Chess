package PresentationLayer.Initializers;

import BusinessLayer.BlackChess.*;
import BusinessLayer.Board.Position;
import BusinessLayer.Tiles.EmptyTile;
import BusinessLayer.WhiteChess.*;
import BusinessLayer.Tiles.Tile;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class TileFactory {


    private static final Map<String, Supplier<Tile>> tilesMap = new HashMap<>(){
        {
            put("BQu", () -> new BlackQueen());
            put("BKi", () -> new BlackKing());
            put("BKn", () -> new BlackKnight());
            put("BBi", () -> new BlackBishop());
            put("BPe", () -> new BlackPeony());
            put("BTo", () -> new BlackTorre());
            put("WQu", () -> new WhiteQueen());
            put("WKi", () -> new WhiteKing());
            put("WKn", () -> new WhiteKnight());
            put("WBi", () -> new WhiteBishop());
            put("WPe", () -> new WhitePeony());
            put("WTo", () -> new WhiteTorre());
            put("E", () -> new EmptyTile());
        }
    };



    public static Tile produceTile(String key, Position position){
        Tile t = tilesMap.get(key).get();
        t.initialize(position);
        return t;
    }
}
