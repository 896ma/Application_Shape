import java.awt.*;

// Class that defines all hollow polygons

class HollowPolygon extends Polygon_ {

    // Constructor that calls super constructor

    public HollowPolygon(Color color, int vertexCount) {
        super(color, vertexCount);
    }

    // Draws hollow polygon

    @Override
    public void drawPolygon(Graphics graphics, Polygon polygon) {
        // Set the color of the polygon
        //graphics.setColor(getColor());
        
        // Draw the outline of the polygon
        graphics.drawPolygon(polygon);
    }
}
