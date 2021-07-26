package BusinessLayer.Tiles;

import BusinessLayer.TurnSingeltons.CurrentBlackTurn;
import BusinessLayer.Board.Position;
import BusinessLayer.VisitorPattern.Visitor;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

public abstract class BlackTile extends Tile{


    protected static final Color OPPOSITE_COLOR = Color.WHITE;
    protected List<Supplier<Position>> positions;
    protected CurrentBlackTurn instance = CurrentBlackTurn.getInstance();
    private static final Color BLACK = Color.BLACK;

    public BlackTile(String tile, List<Supplier<Position>> positions) {
        super(tile, BLACK);
        this.positions = positions;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    public void visit(WhiteTile w){
        w.deathCallback.call();
        swapPositions(tileCallback.getByPosition(w.position));
    }


    public List<Position> legalMoves(){
        List<Position> res = new LinkedList<>();
        for (Supplier<Position> p : positions){
            addIfLegal(p.get(), res);
        }
        return res;
    }
    private void addIfLegal(Position advanced, List<Position> res){
        Position p = getPosition();
        boolean first = false;
        p = advancePosition(advanced, p);
        while (notOutOfBounds(p) && legalStep(tileCallback.getByPosition(p)) && !first){
            res.add(p);
            first = tileCallback.getByPosition(p).getColor().equals(OPPOSITE_COLOR);
            p = advancePosition(advanced, p);
        }
    }

    public boolean legalAccept(Tile t){
        return t.legalVisit(this);
    }

    public boolean legalVisit(WhiteTile b){ return true; }

    protected Position advancePosition(int idx, Position position){
        return new Position(position.getX() + positions.get(idx).get().getX(), position.getY() + positions.get(idx).get().getY());
    }

    protected Position advancePosition(Position advanced, Position position){
        return new Position(position.getX() + advanced.getX(), position.getY() + advanced.getY());
    }

}
