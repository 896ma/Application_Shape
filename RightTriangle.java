import java.awt.*;

// Class that defines a hollow right triangle

class RightTriangle extends HollowPolygon {

    // Constructor that initializes the vertices of the right triangle

    public RightTriangle(Color color, Point upperLeft, int height, int width) {
        super(color, 3);
        int[] x_points = {upperLeft.x, upperLeft.x, upperLeft.x + width};
        int[] y_points = {upperLeft.y, upperLeft.y + height, upperLeft.y + height};
        createPolygon(x_points, y_points);
    }
}

// Class that defines a parallelogram

class Parallelogram extends HollowPolygon {

    // Constructor that initializes the vertices of the parallelogram

    public Parallelogram(Color color, Point topLeft, int width, int height, int offset) {
        super(color, 4);
        int[] x_points = {topLeft.x, topLeft.x + width, topLeft.x + width - offset, topLeft.x - offset};
        int[] y_points = {topLeft.y, topLeft.y, topLeft.y + height, topLeft.y + height};
        createPolygon(x_points, y_points);
    }
}

// Class that defines a regular polygon

class RegularPolygon extends HollowPolygon {

    // Constructor that initializes the vertices of the regular polygon

    public RegularPolygon(Color color, Point center, int sides, int radius) {
        super(color, sides);
        int[] x_points = new int[sides];
        int[] y_points = new int[sides];

        double angle = 2 * Math.PI / sides;
        for (int i = 0; i < sides; i++) {
            double x = center.x + radius * Math.cos(i * angle);
            double y = center.y + radius * Math.sin(i * angle);
            x_points[i] = (int) Math.round(x);
            y_points[i] = (int) Math.round(y);
        }

        createPolygon(x_points, y_points);
    }
}

// Class that defines an isosceles shape

class Isosceles extends HollowPolygon {

    // Constructor that initializes the vertices of the isosceles shape

    public Isosceles(Color color, Point top, int height, int width) {
        super(color, 3);
        int[] x_points = { top.x, top.x - width/2, top.x + width/2 };
        int[] y_points = { top.y, top.y + height, top.y + height };
        createPolygon(x_points, y_points);
    }
}

