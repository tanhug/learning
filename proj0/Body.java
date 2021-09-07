public class Body {

	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	/** Body's first constructor */
	public Body(double xP, double yP, double xV,
		        double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	/** Body's second constructor */
	public Body(Body b) {
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}

	/** calculates the distance between two Bodys */
	public double calcDistance(Body b) {
		double dx2, dy2, r;
		dx2 = Math.pow(xxPos - b.xxPos, 2);
		dy2 = Math.pow(yyPos - b.yyPos, 2);
		r = Math.sqrt(dx2 + dy2);
		return r;
	}
}