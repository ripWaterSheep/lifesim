package game.controls;

public class BetterKey extends BetterInput {

    BetterKey(int keyCode) {
        super(keyCode);
        BetterKeyboard.keys.add(this);
    }


}
