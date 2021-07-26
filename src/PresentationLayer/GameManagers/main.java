package PresentationLayer.GameManagers;

import PresentationLayer.Initializers.GameInitializer;

public class main {


    public static void main(String[] args){
        GameManager gm = GameInitializer.Initialize();
        gm.init(gm.getChessBoard());
    }
}
