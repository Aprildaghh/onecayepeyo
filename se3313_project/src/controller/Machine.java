package controller;

import Db.Dao;
import model.*;
import view.MainView;

import java.util.concurrent.TimeUnit;

public class Machine {
    private State brewingState;
    private State doneState;
    private State emptyState;
    private State idleState;

    private State state;
    private MainView view;

    public Machine()
    {
        this.state = new EmptyState(this);
        this.view = new MainView(this);
        this.brewingState = new BrewingState(this);
        this.doneState = new DoneState(this);
        this.emptyState = new EmptyState(this);
        this.idleState = new IdleState(this);
    }

    public void setState(State state) {
        this.state = state;
    }
    public State getState(){return this.state;}

    public State getBrewingState() {
        return brewingState;
    }

    public State getDoneState() {
        return doneState;
    }

    public State getEmptyState() {
        return emptyState;
    }

    public State getIdleState() {
        return idleState;
    }

    public void showError(String err)
    {
        view.showError(err);
    }

    public void update(Object o)
    {
        if(o instanceof String) updateState((String)o);
        else if (o instanceof Integer) saveCups(((Integer) o));
        else {
            throw new RuntimeException(new Exception("Wrong type of object in Machine.update()"));
        }
    }

    private void brewingTimer()
    {
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        state.timerExpires();
                        view.changeState(state.getClass());
                    }
                },
                3000
        );
    }

    public void updateState(String button)
    {
        switch (button) {
            case "start":
                state.start();
                brewingTimer();
                break;
            case "reset":
                state.reset();
                break;
            case "fill":
                state.filled();
                break;
            case "totalCups":
                int totalCups = Dao.getInstance().getTotalCups();
                view.displayTotalCups(totalCups);
                break;
        }

        view.changeState(state.getClass());
    }

    public void saveCups(int numberOfCups)
    {
        Dao.getInstance().insertCups(numberOfCups);
    }

    public void start()
    {
        view.startWindow();
    }

}
