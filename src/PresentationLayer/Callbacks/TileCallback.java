package PresentationLayer.Callbacks;

import BusinessLayer.Board.Position;
import BusinessLayer.Tiles.Tile;

public interface TileCallback {
    public Tile getByPosition(Position p);
}
