package com.javarush.task.task24.task2413;

public class Ball extends BaseObject {

    private double dx, dy, direction, speed;

    public Ball(double dx, double dy, double speed, double direction) {
        super(dx, dy, 1);

        this.dx = dx;
        this.dy = dy;
        this.direction = direction;
        this.speed = speed;
        isFrozen = true;

    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    public double getDirection() {
        return direction;
    }

    public double getSpeed() {
        return speed;
    }

    private boolean isFrozen;

    @Override
    void draw(Canvas canvas) {
        canvas.setPoint(x, y, 'O');
    }

    @Override
    void move() {
        if (isFrozen == false) {
            x = x + dx;
            y = y + dy;
        }
    }

    void start() {
        isFrozen = false;
    }
    void setDirection(double param){
        this.direction = direction;
        double angle = Math.toRadians(direction);
        dx = Math.cos(angle)*speed;
        dy = -Math.sin(angle)*speed;
    }
    void checkRebound(int minx, int maxx, int miny, int maxy){}

}
