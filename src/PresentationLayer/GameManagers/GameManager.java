package PresentationLayer.GameManagers;

import BusinessLayer.Board.ChessBoard;
import BusinessLayer.Board.Position;
import BusinessLayer.Tiles.Tile;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.function.IntConsumer;

public class GameManager {


    private Label Title = new Label("Chess Game"){
        {
            setFont(new Font("Arial", Font.PLAIN, 30));
            setBounds(500, 0, 300, 100);
        }
    };
    private Label gameInfo = new Label(){
        {
            setFont(new Font("Arial", Font.PLAIN, 20));
            setBounds(500, 200, 300, 300);
            turn = 1;
            setText(getTurnInfo());
        }
    };
    private Position from;
    private int turn = 1;
    private JButton[][] btnArr = new JButton[8][8];
    private ChessBoard chessBoard;
    private static final int width = 52;
    private static final int height = 52;
    private static final int addedWidth = 40;
    private static final int addedHeight = 30;
    private int cellRow,cellCol;
    private IntConsumer clickButton;
    private JFrame frame = new JFrame();
    ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int [] idx;
            if (e.getSource() instanceof JButton) {
                idx = getBtnIdx((JButton)e.getSource());
                String text = "row = "+idx[0]+" col = "+idx[1];
                System.out.println(text +  "   " + turn);
                cellRow = idx[0];
                cellCol = idx[1];
                clickButton.accept(0);
            }
        }
    };

    public ChessBoard getChessBoard(){
        return chessBoard;
    }
    public GameManager(){ }

    private void buttonClick(int x){
        if(from == null){
            if(btnArr[cellRow][cellCol].getForeground().equals((turn == 1) ? Color.WHITE : Color.BLACK)) {
                from = new Position(cellRow, cellCol);
            }
        }
        else{
            Tile t = chessBoard.getTile(new Position(cellRow, cellCol));
            if(chessBoard.getTile(from).legalMoves().contains(t.getPosition()) && !t.getPosition().equals(from)){
                chessBoard.getTile(from).performAction(t.getPosition());
                switchTurn();
            }
            from = null;
        }
    }

    private String getTurnInfo(){
        return String.format("%s player turn", player());
    }
    public void init(ChessBoard chessBoard){
        this.chessBoard = chessBoard;
        dansMethod(0, x -> {
            buttonClick(x);
        });
        frame.setSize(btnArr.length*width + btnArr.length*addedWidth,btnArr[0].length*height + btnArr[0].length*addedHeight);//400 width and 500 height
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(frame,
                        "Are you sure you want to end the game?", "End Game?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){

                    System.exit(0);
                }
            }
        });
        for (int row=0;row<btnArr.length;row++){
            for (int col=0;col<btnArr[0].length;col++){
                Tile t = chessBoard.getTile(row, col);
                btnArr[row][col] = new JButton(t.getTile());
                btnArr[row][col].setBackground(((row + col)%2 == 0) ? new Color(0xFF660D0D, true) : new Color(0xC61C36));
                btnArr[row][col].setForeground(t.getColor());
                btnArr[row][col].setBounds(row*width+row, col*height+col, width,  height);
                btnArr[row][col].addActionListener(listener);
                frame.add(btnArr[row][col]);
            }
        }
        frame.add(Title);
        frame.add(gameInfo);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }
    public void messageBox(String message,String title){
        JOptionPane.showMessageDialog(null, message,title,1);
    }

    public void switchTurn(){
        turn = (turn == 1) ? 2 : 1;
        gameInfo.setText(getTurnInfo());
        if(kingIsTrapped()){
            if(!kingTrappersTrapped()) {
                turn = (turn == 1) ? 2 : 1;
                messageBox(String.format("checkmate %s player won", player()), "Game Ended");
                System.exit(0);
            }
        }
    }

    public boolean kingIsTrapped(){
        return ((turn == 1) ? getKing(whitePlayers()) == null : getKing(blackPlayers()) == null) || !trappingKing().isEmpty();
    }

    public Set<Tile> trappingKing() {
        Set<Tile> trap = new HashSet<>();
        List<Position> res = (turn == 1) ? whitePlayers() : blackPlayers();
        List<Position> list = null;
        boolean flag = false;
        Position p = getKing(res);
        if(p == null) {trap.add(null); return trap;}
        Tile t = chessBoard.getTile(p);
        list = t.legalMoves();
        list.add(t.getPosition());
        for (Position b : res) {
            List<Position> m = chessBoard.getTile(b).legalMoves();
            for (Position r : m) {
                if (list.contains(r)) {
                    flag = true;
                    list.remove(r);
                }
            }
        }
        return trap;
    }

    private boolean kingTrappersTrapped() {
        Set<Tile> trap = trappingKing();
        List<Position> res = (turn == 2) ? whitePlayers() : blackPlayers();
        Set<Tile> isTrapped = new HashSet<>();
        for (Tile t : trap) {
            if(t!=null) {
                for (Position b : res) {
                    List<Position> m = chessBoard.getTile(b).legalMoves();
                    for (Position r : m) {
                        if (r.equals(t.getPosition())) {
                            isTrapped.add(t);
                        }
                    }
                }
            }
        }
        return isTrapped.size() >= 1;
    }

    private Position getKing(List<Position> res){
        for (int i = 0;i < btnArr.length;i++){
            for (int j = 0;j < btnArr[0].length;j++){
                if(btnArr[i][j].getText().equals("Ki") && btnArr[i][j].getForeground().equals((turn == 1) ? Color.WHITE : Color.BLACK)){
                    return new Position(i, j);
                }
            }
        }
        return null;
    }

    private List<Position> whitePlayers(){
        List<Position> res = new LinkedList<>();
        for (int i = 0;i < btnArr.length;i++){
            for (int j = 0;j < btnArr[0].length;j++){
                if(btnArr[i][j].getForeground().equals(Color.WHITE)){
                    res.add(new Position(i, j));
                }
            }
        }
        return res;
    }

    private List<Position> blackPlayers(){
        List<Position> res = new LinkedList<>();
        for (int i = 0;i < btnArr.length;i++){
            for (int j = 0;j < btnArr[0].length;j++){
                if(btnArr[i][j].getForeground().equals(Color.BLACK)){
                    res.add(new Position(i, j));
                }
            }
        }
        return res;
    }

    private int countWhite(){
        return whitePlayers().size();
    }

    private int countBlack(){
        return blackPlayers().size();
    }
    private String player(){
        return (turn == 1) ? "White" : "Black";
    }

    public void dansMethod(int x, IntConsumer aMethod) {

        clickButton = aMethod;
    }

    public void setChessBoard(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }


    private int[] getBtnIdx(JButton b){
        int [] ret = {-1,-1};
        for (int row=0;row<btnArr.length;row++){
            for (int col=0;col<btnArr[0].length;col++){
                if (btnArr[row][col] == b){
                    ret[0] = row;
                    ret[1] = col;
                    return ret;
                }
            }
        }
        return ret;

    }

    public void swapPositions(Tile t1, Tile t2){
        btnArr[t1.getPosition().getX()][t1.getPosition().getY()].setForeground(t1.getColor());
        btnArr[t1.getPosition().getX()][t1.getPosition().getY()].setText(t1.getTile());
        btnArr[t2.getPosition().getX()][t2.getPosition().getY()].setForeground(t2.getColor());
        btnArr[t2.getPosition().getX()][t2.getPosition().getY()].setText(t2.getTile());
    }
}
