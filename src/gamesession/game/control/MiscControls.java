package gamesession.game.control;


public class MiscControls {


    public static boolean getInteractTapped() {
        return (KeyboardControls.getInteractKeyTyped() || MouseControls.getLeftClicked());
    }


    public static void resetControls() {
        KeyboardControls.resetKeys();
        MouseControls.resetButtons();
    }


}
