package Controls;


public class MiscControlStuff {

    public static boolean getInteractPressed() {
        return (KeyboardControls.getInteractKeyPressed() || MouseControls.getLeftPressed());
    }

    public static boolean getInteractTapped() {
        return (KeyboardControls.getInteractKeyTyped() || MouseControls.getLeftClicked());
    }


    public static void resetControls() {
        KeyboardControls.resetKeys();
        MouseControls.resetButtons();
    }

}
