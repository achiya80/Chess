package PresentationLayer.Initializers;

import BusinessLayer.Board.ChessBoard;
import BusinessLayer.Board.Position;
import BusinessLayer.Tiles.Tile;
import PresentationLayer.GameManagers.GameManager;

public class GameInitializer {

    private static final String[][] BOARD = {
            {"BTo", "BKn", "BBi", "BKi", "BQu", "BBi", "BKn", "BTo"},
            {"BPe","BPe","BPe","BPe","BPe","BPe","BPe","BPe"},
            {"E","E","E","E","E","E","E","E"},
            {"E","E","E","E","E","E","E","E"},
            {"E","E","E","E","E","E","E","E"},
            {"E","E","E","E","E","E","E","E"},
            {"WPe","WPe","WPe","WPe","WPe","WPe","WPe","WPe"},
            {"WTo", "WKn", "WBi", "WQu", "WKi", "WBi", "WKn", "WTo"}};


    public static GameManager Initialize(){
        GameManager gm = new GameManager();
        ChessBoard chessBoard = new ChessBoard();
        gm.setChessBoard(chessBoard);
        for(int i = 0;i < BOARD.length;i++){
            for (int j = 0;j < BOARD[0].length;j++){
                Position p = new Position(j, i);
                Tile t = TileFactory.produceTile(BOARD[i][j], p);
                chessBoard.addTile(t);
                t.setPositionCallback((pos) -> {Position p1 = t.getPosition();
                    t.interact(chessBoard.getTile(pos));
                    if(t.getPosition().equals(pos)){
                        chessBoard.swapPositions(pos, p1);
                    }
                gm.swapPositions(chessBoard.getTile(pos), chessBoard.getTile(p1));});
                t.setDeathCallback(() -> chessBoard.addTile(TileFactory.produceTile("E", t.getPosition())));
                t.setTileCallback((pos) -> chessBoard.getTile(pos));
            }
        }
        return gm;
    }
}
