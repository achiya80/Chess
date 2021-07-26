package BusinessLayer.Tiles;

import BusinessLayer.Board.Position;
import BusinessLayer.VisitorPattern.Visited;
import BusinessLayer.VisitorPattern.Visitor;
import PresentationLayer.Callbacks.DeathCallback;
import PresentationLayer.Callbacks.PositionCallback;
import PresentationLayer.Callbacks.TileCallback;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public abstract class Tile implements Visitor, Visited, Comparable<Tile> {

    protected TileCallback tileCallback;
    protected Color color;
    protected DeathCallback deathCallback;
    protected PositionCallback positionCallback;
    protected String tile;
    protected Position position;
    protected ImageIcon imageIcon;
    public Tile(String tile, Color color){
        this.tile = tile;
        this.color = color;
    }

    public void initialize(Position position){
        this.position = position;
    }
    public void setTileCallback(TileCallback tileCallback){this.tileCallback = tileCallback;}
    public void setDeathCallback(DeathCallback deathCallback){
        this.deathCallback = deathCallback;
    }
    public void setPositionCallback(PositionCallback positionCallback){
        this.positionCallback = positionCallback;
    }
    public Position getPosition() {
        return position;
    }

    protected void swapPositions(Tile t){
        Position temp = t.position;
        t.position = position;
        position = temp;
    }

    @Override
    public int compareTo(Tile t){
        return position.compareTo(t.position);
    }


    public String getTile(){
        return tile;
    }

    public Color getColor(){return color;}

    public void interact(Tile t){
        if(legalMoves().contains(t.position)) {
            t.accept(this);
        }
    }

    public void visit(EmptyTile e){
        swapPositions(e);
    }

    public void visit(WhiteTile w){

    }

    public void visit(BlackTile b){

    }
    public boolean legalStep(Tile t){
        return t.legalAccept(this);
    }

    public void performAction(Position p){
        positionCallback.Move(p);
    }
    public abstract List<Position> legalMoves();
    public abstract boolean legalAccept(Tile t);
    public boolean legalVisit(EmptyTile e){ return true; }
    public boolean legalVisit(WhiteTile w){ return false; }
    public boolean legalVisit(BlackTile b){ return false; }

    public boolean notOutOfBounds(Position p){
        return p.getY() < 8 && p.getX() < 8 && p.getX() >= 0 && p.getY() >= 0;
    }
}
