public class NBody {
    /** reads radius of the universe */
    public static double readRadius(String s) {
        In in = new In(s);
        int N = in.readInt();

        return in.readDouble();
    }

    /** reads Planets in the file */
    public static Planet[] readPlanets(String s) {
        In in = new In(s);
        int n = in.readInt();
        double r = in.readDouble();
        Planet[] planets = new Planet[n];

        for(int i = 0; i < n; i +=1){
            planets[i] = new Planet(in.readDouble(), in.readDouble(), 
                                    in.readDouble(), in.readDouble(),
                                    in.readDouble(), in.readString());

        }
        return planets;
    }

    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double r = NBody.readRadius(filename);
        Planet[] planets = NBody.readPlanets(filename);

        StdDraw.setScale(-r, r);
        StdDraw.clear();
        StdDraw.picture(0, 0, "./images/starfield.jpg");
        for (Planet s: planets) {
            s.draw();
        }

        StdDraw.enableDoubleBuffering();
        double t = 0;
        int n = planets.length;
        while (t < T) {
            double[] xForces = new double[n];
            double[] yForces = new double[n];
            for (int i = 0; i < n; i += 1) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int i = 0; i < n; i += 1) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, "./images/starfield.jpg");
            for (Planet s: planets) {
                s.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            t = t + dt;
        }
        StdOut.printf("%d\n", n);
        StdOut.printf("%.2e\n", r);
        for (int i = 0; i < n; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
            planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }
    }

}