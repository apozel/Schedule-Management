package Schedule.utilities;

import Marchant.Moteur.Noeud;

/**
 * Position
 */
public class Position {

    private double x, y;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Position(int x, int y) {
        this.x = (double) x;
        this.y = (double) y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public boolean equalsPosition(Noeud no) {
        if (no.getX() == this.getX() && no.getY() == this.getY()) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Position [x=" + x + ", y=" + y + "]";
    }
}