package model;

public interface State {
    public void filled();
    public void start();
    public void timerExpires();
    public void reset();
}
