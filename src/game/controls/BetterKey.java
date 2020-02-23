package game.controls;

public class BetterKey extends BetterControl {

    BetterKey(int keyCode) {
        super(keyCode);
        BetterKeyboard.keys.add(this);
    }


}
