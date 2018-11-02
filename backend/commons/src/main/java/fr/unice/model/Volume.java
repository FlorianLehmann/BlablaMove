package fr.unice.model;

import javax.persistence.Embeddable;

@Embeddable
public class Volume {

    private int height;
    private int width;
    private int depth;

    public Volume(int height, int width, int depth) {
        this.height = height;
        this.width = width;
        this.depth = depth;
    }

    public Volume() {
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
}
