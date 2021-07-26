package BusinessLayer.Tiles;

import BusinessLayer.Board.Position;
import BusinessLayer.VisitorPattern.Visitor;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class EmptyTile extends Tile{

  //  private static final String pathToIcon = "";
    private static final String EMPTY_TILE = "";

    public EmptyTile() {
        super(EMPTY_TILE, Color.RED);
    }

    public void accept(Visitor v){ v.visit(this); }
    public void visit(EmptyTile e){}

    @Override
    public List<Position> legalMoves() {
      return new LinkedList<>();
    }
    public boolean legalAccept(Tile t){
        return t.legalVisit(this);
    }
    @Override
    public boolean legalVisit(EmptyTile e) { return false; }
}
