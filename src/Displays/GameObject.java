package Displays;

import com.sun.xml.internal.bind.v2.model.core.ID;

import java.awt.*;

public abstract class GameObject {

    protected int x,y;
    protected Displays.ID id;
    protected int vX, vY;
    protected Displays.ID belongsTo;
    protected boolean isBottomRow;
    protected boolean currentShooter;
    protected int row, col;
    protected boolean notShotyet;

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getvX() {
        return vX;
    }

    public void setvX(int vX) {
        this.vX = vX;
    }

    public int getvY() {
        return vY;
    }

    public void setvY(int vY) {
        this.vY = vY;
    }

    public Displays.ID getId() {
        return id;
    }

    public void setId(Displays.ID id) {
        this.id = id;
    }

    public Displays.ID getBelongsTo() {
        return belongsTo;
    }

    public void setBelongsTo(Displays.ID belongsTo) {
        this.belongsTo = belongsTo;
    }

    public boolean isBottomRow() {
        return isBottomRow;
    }

    public void setBottomRow(boolean bottomRow) {
        isBottomRow = bottomRow;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public GameObject(int x, int y, Displays.ID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public static void gameOver() {

    }
}
