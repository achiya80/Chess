package BusinessLayer.Board;

import BusinessLayer.Tiles.Tile;

public class ChessBoard {


    private Tile[][] tiles = new Tile[8][8];


    public void addTile(Tile t){
        tiles[t.getPosition().getX()][t.getPosition().getY()] = t;
    }

    public Tile getTile(int i, int j){
        return tiles[i][j];
    }


    public Tile getTile(Position p){
        return tiles[p.getX()][p.getY()];
    }

    public void swapPositions(Position p1, Position p2){
        Tile t = tiles[p1.getX()][p1.getY()];
        tiles[p1.getX()][p1.getY()] = tiles[p2.getX()][p2.getY()];
        tiles[p2.getX()][p2.getY()] = t;
    }


}
