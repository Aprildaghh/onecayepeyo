package model;

import controller.Machine;

public class BrewingState implements State{

    private Machine controller;

    public BrewingState(Machine controller)
    {
        this.controller = controller;
    }

    @Override
    public void filled() {
        controller.showError("Machine is already filled and brewing!");
    }

    @Override
    public void start() {
        controller.showError("Machine already started!");
    }

    @Override
    public void timerExpires() {
        controller.setState(controller.getDoneState());
    }

    @Override
    public void reset() {
        controller.showError("You can't reset while brewing!");
    }

    @Override
    public String toString(){
        return "brewing";
    }
}
