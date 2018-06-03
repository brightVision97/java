import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class GeometricMedian
{
    static List<Point.Double> points = new ArrayList<>();
    static double burningTime = 0;

    public Point.Double getCentroid()
    {
        double cx = 0.0D;
        double cy = 0.0D;

        for (int i = 0; i < points.size(); i++)
        {
            Point.Double pt = points.get(i);
            cx += pt.getX();
            cy += pt.getY();
        }

        return new Point.Double(cx / points.size(),
                cy / points.size());
    }

    public Point.Double getGeoMedian(Point.Double start)
    {
        double cx = 0.0D;
        double cy = 0.0D;
        double totalWeight;

        Locale.setDefault(Locale.ENGLISH);
        Scanner input = new Scanner(System.in);

        double singleBurnTime = input.nextDouble();

        while (Math.abs(cx - getCentroid().getX()) > 0.5 ||
                Math.abs(cy - getCentroid().getY()) > 0.5)
        {
            totalWeight = 0;

            for (int i = 0; i < points.size(); i++)
            {
                Point.Double p = points.get(i);

                double weight = 1 / p.distance(getCentroid());

                cx += p.getX() * weight;
                cy += p.getY() * weight;
                totalWeight += weight;
            }

            burningTime = totalWeight * singleBurnTime;

            cx /= totalWeight;
            cy /= totalWeight;
        }

        return new Point.Double(cx, cy);
    }

    public static void main(String[] args)
    {
        GeometricMedian gm = new GeometricMedian();

        points.add(new Point.Double(0, 1));
        points.add(new Point.Double(0, 3));
        points.add(new Point.Double(1, 0));
        points.add(new Point.Double(2, 0));
        points.add(new Point.Double(-1, 0));
        points.add(new Point.Double(-2, -1));
        points.add(new Point.Double(-3, -1));
        points.add(new Point.Double(-1, -2));
        points.add(new Point.Double(-1, 1));
        points.add(new Point.Double(-1, 2));
        points.add(new Point.Double(1, 3));
        points.add(new Point.Double(2, 2));
        points.add(new Point.Double(2, 1));
        points.add(new Point.Double(1, 1));

        Point.Double geoMedian =
                gm.getGeoMedian(gm.getCentroid());

        System.out.println(
                "{" + (int)Math.round(geoMedian.getX()) +
                ", " + (int)Math.round(geoMedian.getY()) +
                "}\n" + String.format("%.2fs", burningTime));
    }
}