package model;

import controller.Machine;

import java.util.concurrent.TimeUnit;

public class IdleState implements State{

    private Machine controller;

    public IdleState(Machine controller)
    {
        this.controller = controller;
    }

    @Override
    public void filled() {
        controller.showError("Machine is already filled!");
    }

    @Override
    public void start() {
        controller.setState(controller.getBrewingState());
    }

    @Override
    public void timerExpires() {
        controller.showError("Nothing was brewing!");
    }

    @Override
    public void reset() {
        controller.showError("Process is not finished!");
    }

    @Override
    public String toString(){
        return "idle";
    }
}
