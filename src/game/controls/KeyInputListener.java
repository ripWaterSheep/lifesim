package game.controls;

public class KeyInputListener extends InputListener {

    KeyInputListener(int keyCode) {
        super(keyCode);
        KeyInputManager.keys.add(this);
    }


}
