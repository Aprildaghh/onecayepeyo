package model;

import controller.Machine;

public class EmptyState implements State{

    private Machine controller;

    public EmptyState(Machine controller)
    {
        this.controller = controller;
    }

    @Override
    public void filled() {
        controller.setState(controller.getIdleState());
    }

    @Override
    public void start() {
        controller.showError("Machine should be filled first!");
    }

    @Override
    public void timerExpires() {
        controller.showError("Machine should be filled first!");
    }

    @Override
    public void reset() {
        controller.showError("Machine should be filled first!");
    }

    @Override
    public String toString(){
        return "empty";
    }
}
