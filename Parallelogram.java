import java.awt.*;

// Abstract base class for all polygon classes

abstract class Polygon_ extends Image {

    private int vertexCount;
    private Polygon polygon;

    // Constructor that initializes color and vertexCount of a polygon

    public Polygon_(Color color, int vertexCount) {
        super(color);
        this.vertexCount = vertexCount;
    }

    // Creates a polygon object given its vertices

    public void createPolygon(int[] x_points, int[] y_points) {
        polygon = new Polygon(x_points, y_points, vertexCount);
    }

    // Draws polygon using polygon object

    @Override
    public void draw(Graphics graphics) {
        colorDrawing(graphics);
        drawPolygon(graphics, polygon);
    }

    // Abstract method to be implemented by subclasses
    // Draws the polygon using the provided graphics object and polygon

    abstract public void drawPolygon(Graphics graphics, Polygon polygon);
}

// Subclass representing a hollow polygon

class HollowPolygon extends Polygon_ {

    // Constructor that calls super constructor

    public HollowPolygon(Color color, int vertexCount) {
        super(color, vertexCount);
    }

    // Draws the hollow polygon using the provided graphics object and polygon

    @Override
    public void drawPolygon(Graphics graphics, Polygon polygon) {
        // Set the color of the polygon
        //graphics.setColor(getColor());
        
        // Draw the outline of the polygon
        graphics.drawPolygon(polygon);
    }
}

// Class representing a parallelogram shape

class Parallelogram extends HollowPolygon {

    // Constructor that calls super constructor

    public Parallelogram(Color color, Point point, Point secondPoint, int offset) {
        super(color, 4);
        int[] x_points = { point.x, secondPoint.x, secondPoint.x - offset, point.x - offset };
        int[] y_points = { point.y, secondPoint.y, secondPoint.y, point.y };
        createPolygon(x_points, y_points);
    }
}
