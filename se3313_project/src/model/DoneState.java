package model;

import controller.Machine;

public class DoneState implements State{

    private Machine controller;

    public DoneState(Machine controller)
    {
        this.controller = controller;
    }

    @Override
    public void filled() {
        controller.showError("Machine needs to reset first!");
    }

    @Override
    public void start() {
        controller.showError("Machine needs to be reset and filled!");
    }

    @Override
    public void timerExpires() {
        controller.showError("Timer already expired!");
    }

    @Override
    public void reset() {
        controller.setState(controller.getEmptyState());
    }

    @Override
    public String toString(){
        return "done";
    }
}
