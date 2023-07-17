import java.awt.*;
import java.util.*;
import javax.swing.*;

// Class that defines the panel for drawing the images

class DrawingPanel extends JPanel {

    private ArrayList<Image> images = new ArrayList<>();

    // Adds a graphic object to the drawing panel

    public void addImage(Image image) {
        images.add(image);
    }

    // Draws all the images on the drawing panel

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        
        for (Image image : images) {
            if (image instanceof HollowPolygon) {
                HollowPolygon hollowPolygon = (HollowPolygon) image;
                //hollowPolygon.drawPolygon(graphics, hollowPolygon.getPolygon());
            } else {
                image.draw(graphics);
            }
        }
    }
}
