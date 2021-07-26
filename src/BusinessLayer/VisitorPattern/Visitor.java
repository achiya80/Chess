package BusinessLayer.VisitorPattern;

import BusinessLayer.Tiles.BlackTile;
import BusinessLayer.Tiles.EmptyTile;
import BusinessLayer.Tiles.WhiteTile;

public interface Visitor {

    public void visit(BlackTile b);
    public void visit(WhiteTile w);
    public void visit(EmptyTile e);
}
