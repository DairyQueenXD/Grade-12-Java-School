package Arcadia;

import java.awt.Image;

public class Machine {
    private String name;
    private Image image;
    private int width; // Grid width
    private int height; // Grid height
    private int x; // Grid X position
    private int y; // Grid Y position

    public Machine(String name, Image image, int width, int height) {
        this.name = name;
        this.image = image;
        this.width = width;
        this.height = height;
        this.x = -1; // Unplaced
        this.y = -1; // Unplaced
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public Image getImage() {
        return image;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isPlaced() {
        return x != -1 && y != -1;
    }
}
