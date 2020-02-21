package game.ECS.components;

public class RandomAIComponent implements Copyable {

    // TODO: add functionality in MovementSystem
    private double turnChance;

    public RandomAIComponent(double turnChancePerFrame) {
        this.turnChance = turnChancePerFrame;
    }


    @Override
    public RandomAIComponent copyInitialState() {
        return new RandomAIComponent(turnChance);
    }

    @Override
    public RandomAIComponent copyCurrentState() {
        return copyInitialState();
    }
}
