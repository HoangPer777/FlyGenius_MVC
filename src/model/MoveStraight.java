package model;

public class MoveStraight implements IMoveBehavior {
    @Override
    public void move(Object obj) {
        AObject aObject = (AObject) obj;
        aObject.setY(aObject.getY() + aObject.getDeltaY() * aObject.getSpeed());
    }
}
