public class Planet {

	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	/** Planet's first constructor */
	public Planet(double xP, double yP, double xV,
		        double yV, double m, String img) {
		this.xxPos = xP;
		this.yyPos = yP;
		this.xxVel = xV;
		this.yyVel = yV;
		this.mass = m;
		this.imgFileName = img;
	}

	/** Planet's second constructor */
	public Planet(Planet b) {
		this.xxPos = b.xxPos;
		this.yyPos = b.yyPos;
		this.xxVel = b.xxVel;
		this.yyVel = b.yyVel;
		this.mass = b.mass;
		this.imgFileName = b.imgFileName;
	}

	/** calculates the distance between two Planets */
	public double calcDistance(Planet b) {
		double dx2, dy2, r;
		dx2 = Math.pow(this.xxPos - b.xxPos, 2);
		dy2 = Math.pow(this.yyPos - b.yyPos, 2);
		r = Math.sqrt(dx2 + dy2);
		return r;
	}

	/** calculates the force exerted on this planet */
	public  double calcForceExertedBy(Planet b) {
		double r = this.calcDistance(b);
		double f;
		f = 6.67e-11 * this.mass * b.mass / Math.pow(r, 2);
		return f;
	}

	/** calculates the force exerted on this planet in X */
	public  double calcForceExertedByX(Planet b) {
		double dx;
		dx = - this.xxPos + b.xxPos;

		double fx, f, r;
		f = this.calcForceExertedBy(b);
		r = this.calcDistance(b);
		fx = f * dx / r;
		return fx;
	}

	/** calculates the force exerted on this planet in Y */
	public  double calcForceExertedByY(Planet b) {
		double dy;
		dy = - this.yyPos + b.yyPos;

		double fy, f, r;
		f = this.calcForceExertedBy(b);
		r = this.calcDistance(b);
		fy = f * dy / r;
		return fy;
	}

	/** calculates the net force exerted on this planet in X */
	public double calcNetForceExertedByX(Planet[] allb) {
		double nfx = 0;
		for (int i = 0; i < allb.length; i += 1) {
			if (this.equals(allb[i])) {
				continue;
			}
			nfx = nfx + this.calcForceExertedByX(allb[i]);
		}
		return nfx;
	}

	/** calculates the net force exerted on this planet in Y */
	public double calcNetForceExertedByY(Planet[] allb) {
		double nfy = 0;
		for (int i = 0; i < allb.length; i += 1) {
			if (this.equals(allb[i])) {
				continue;
			}
			nfy = nfy + this.calcForceExertedByY(allb[i]);
		}
		return nfy;
	}

	/** updates Planet's velocity and position */
	public void update(double dt, double fx, double fy) {
		double anx, any;
		anx = fx / this.mass;
		any = fy / this.mass;

		this.xxVel = this.xxVel + dt * anx;
		this.yyVel = this.yyVel + dt * any;

		this.xxPos = this.xxPos + dt * this.xxVel;
		this.yyPos = this.yyPos + dt * this.yyVel;
	}

	/** draws Planet */
	public void draw() {
		StdDraw.picture(this.xxPos, this.yyPos, "./images/" + this.imgFileName);
	}
}