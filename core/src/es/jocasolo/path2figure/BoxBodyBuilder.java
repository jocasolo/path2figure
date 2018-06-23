package es.jocasolo.path2figure;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Class used as a tool to create different bodies that will be affected by gravity, 
 * forces, impulses, etc.
 * Each method returns an object of the type Body initialized with the desired parameters.
 * @author José Carlos Solís Lojo
 */
public class BoxBodyBuilder {
	
	/**
	 *	Type of body that can be created in relation to how it is affected with other bodies.
	 */
	public static enum types {
		/**
		 * A dynamic body is affected by all kinds of forces.
		 */
		DYNAMIC,
		/**
		 * A static body is not affected by any force. The dynamic bodies do react with him.
		 */
		STATIC,
		/**
		 * A kinematic body is a static body but has a linear or angular velocity.
		 */
		KINEMATIC;
	}
	
	/**
	 *	Type of figures that we can create for bodies.
	 */
	public static enum shapes {
		/**
		 * 	Square or rectangle if the height is different from the width.
		 */
		SQUARE,
		/**
		 *	Figure of circular type given a radius. 
		 */
		CIRCLE,
		/**
		 * Polygon with indeterminate number of sides.
		 */
		POLYGON
	}
	
	private World world;
	
	/**
	 * Main constructor of the class.
	 * @param world Object of the Box2d library that contains all 
	 * the bodies in the world that are affected by forces.
	 */
	public BoxBodyBuilder(World world){
		this.world = world;
	}

	/**
	 * Creates a dynamic square or rectangle, that will be affected by 
	 * the forces of the world and by the interaction with other bodies.
	 * @param density Density of the body (determines the weight)
	 * @param friction Friction of the object
	 * @param restitution Restitution of the object (determines the rebound)
	 * @param width	Width of the body
	 * @param height Height of the body	
	 * @param x	Position on the x axis
	 * @param y	Position on the y axis
	 * @param fixedRotation	Indicates whether the object can rotate or not
	 * @return An initialized Body object with the indicated parameters is returned
	 */
	public Body createDynamicSquare(float density, float friction,
			float restitution, float width, float height, float x, float y, boolean fixedRotation){
		PolygonShape square = new PolygonShape();
		square.setAsBox(width, height);
		Body body = createDynamicBody(square, density, friction, restitution, x, y, fixedRotation);
		square.dispose();
		return body;
	}
	
	/**
	 * Creates a static square or rectangle, it will not be 
	 * affected by gravity, forces or impulses, but other dynamic bodies will be 
	 * affected by an interaction with it.
	 * @param width	Width of the body
	 * @param height Height of the body
	 * @param x	Position on the x axis
	 * @param y	Position on the y axis
	 * @return An initialized Body object with the indicated parameters is returned
	 */
	public Body createStaticSquare(float width, float height,
			float x, float y){
		PolygonShape square = new PolygonShape();
		square.setAsBox(width, height);
		Body body = createStaticBody(square, x, y);
		square.dispose();
		return body;
	}
	
	/**
	 * Creates a kinematic square or rectangle, a body that will 
	 * not be affected by gravity, forces or impulses, but will move linearly or angularly 
	 * and other dynamic objects will be affected by an interaction with it.
	 * @param width	Width of the body
	 * @param height Height of the body
	 * @param x	Position on the x axis
	 * @param y	Position on the y axis
	 * @param linearVel Linear velocity of the body
	 * @param omega Angular velocity of the body
	 * @return An initialized Body object with the indicated parameters is returned
	 */
	public Body createKinematicSquare(float width, float height,
			float x, float y, Vector2 linearVel, float omega){
		PolygonShape square = new PolygonShape();  
		square.setAsBox(width, height);
		Body body = createKinematicBody(square, x, y, linearVel, omega);
		square.dispose();
		return body;
	}
	
	/**
	 * Creates a dynamic circle, it will be affected by the 
	 * forces of the world and by the interaction with other bodies.
	 * @param density Density of the body (determines the weight)
	 * @param friction Friction of the object
	 * @param restitution Restitution of the object (determines the rebound)
	 * @param radius Radius of the circular figure
	 * @param x Position on the x axis
	 * @param y Position on the y axis
	 * @param fixedRotation Indicates whether the object can rotate or not
	 * @return An initialized Body object with the indicated parameters is returned
	 */
	public Body createDynamicCircle(float density, float friction,
			float restitution, float radius, float x, float y, boolean fixedRotation){
		CircleShape circle = new CircleShape();
		circle.setRadius(radius);
		Body body = createDynamicBody(circle, density, friction, restitution,
				x, y, fixedRotation);
		circle.dispose();
		return body;
	}
	
	/**
	 * Creates a static circle, it will not be affected by gravity, 
	 * forces or impulses, but other dynamic bodies will be afected
	 * @param radius Radius of the circular figure
	 * @param x Position on the x axis
	 * @param y Position on the y axis
	 * @return An initialized Body object with the indicated parameters is returned
	 */
	public Body createStaticCircle(float radius, float x, float y){
		
		CircleShape circle = new CircleShape();
		circle.setRadius(radius);
		Body body = createStaticBody(circle, x, y);
		circle.dispose();
		return body;
	}
	
	/**
	 * Creates a kinematic circle,  a body that will not be 
	 * affected by gravity, forces or impulses, but will move linearly or angularly 
	 * and other dynamic objects will be affected by the interaction with it.
	 * @param radius Radius of the circular figure
	 * @param x Position on the x axis
	 * @param y Position on the y axis
	 * @param linearVel Linear velocity of the body
	 * @param omega Angular velocity of the body
	 * @return An initialized Body object with the indicated parameters is returned
	 */
	public Body createKinematicCircle(float radius, float x, float y,
			Vector2 linearVel, float omega){
		
		CircleShape circle = new CircleShape();
		circle.setRadius(radius);
		Body body = createKinematicBody(circle, x, y, linearVel, omega);
		circle.dispose();
		return body;
	}
	
	/**
	 * Creates a body from a definition and a figure that will determine the shape.
	 * @param shape Figure that will determine the shape of the body (square, circle, polygon).
	 * @param bodyDef Definition of the body (position, type, allow rotation, etc.).
	 * @param density Density of the body (determines the weight)
	 * @param friction Friction of the object
	 * @param restitution Restitution of the object (determines the rebound)
	 * @return An initialized Body object with the indicated parameters is returned
	 */
	public Body createBody(Shape shape, BodyDef bodyDef, float density,
			float friction, float restitution){
		// Create the body in the world using the facilitated definition
		Body body = world.createBody(bodyDef);

		// Create a fixture that will be applied to the body
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = density; 
		fixtureDef.friction = friction;
		fixtureDef.restitution = restitution;

		body.createFixture(fixtureDef);
		return body;
	}
	
	/**
	 * Auxiliary method to create dynamic bodies.
	 * @param shape Figure with the shape that the body will have (square, circle, polygon)
	 * @param density Density of the body (determines the weight)
	 * @param friction Friction of the object
	 * @param restitution Restitution of the object (determines the rebound)
	 * @param x Position on the x axis
	 * @param y Position on the y axis
	 * @param fixedRotation Indicates whether the object can rotate or not
	 * @return An initialized Body object with the indicated parameters is returned
	 */
	private Body createDynamicBody(Shape shape, float density, float friction, float restitution,
			float x, float y, boolean fixedRotation) {
		// Body definition
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(x, y);
		bodyDef.fixedRotation = fixedRotation;
		Body body = world.createBody(bodyDef);

		// Create a fixture that will be applied to the body
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = density; 
		fixtureDef.friction = friction;
		fixtureDef.restitution = restitution;

		body.createFixture(fixtureDef);
		return body;
	}
	
	/**
	 * Auxiliary method to create static bodies.
	 * @param shape Figure with the shape that the body will have (square, circle, polygon)
	 * @param x Position on the x axis
	 * @param y Position on the y axis
	 * @return An initialized Body object with the indicated parameters is returned
	 */
	private Body createStaticBody(Shape shape, float x, float y) {
		BodyDef bodyDef = new BodyDef();  
		bodyDef.position.set(new Vector2(x, y));  
		Body body = world.createBody(bodyDef);  
		body.createFixture(shape, 0.0f);
		return body;
	}
	
	/**
	 * Auxiliary method to create cinematic bodies.
	 * @param shape Figure with the shape that the body will have (square, circle, polygon)
	 * @param x Position on the x axis
	 * @param y Position on the y axis
	 * @param linearVel Linear velocity of the body
	 * @param omega Angular velocity of the body
	 * @return An initialized Body object with the indicated parameters is returned
	 */
	private Body createKinematicBody(Shape shape, float x, float y, 
			Vector2 linearVel, Float omega) {
		BodyDef bodyDef = new BodyDef();  
		bodyDef.type = BodyType.KinematicBody;
		bodyDef.position.set(new Vector2(x, y));
		
		Body body = world.createBody(bodyDef);
		if(linearVel != null)
			body.setLinearVelocity(linearVel);
		if(omega != null)
			body.setAngularVelocity(omega);
		
		body.createFixture(shape, 0.0f);
		return body;
	}
	
	/**
	 * Creates a circular sensor, a body that is not affected by forces but 
	 * that detects collisions with other bodies.
	 * @param radius Determine the radius of the circular sensor
	 * @param x Position on the x axis
	 * @param y Position on the y axis
	 * @return An initialized Body object with the indicated parameters is returned
	 */
	public Body createCircleSensor(float radius, float x, float y){
		CircleShape circle = new CircleShape();
		circle.setRadius(radius);
		
		BodyDef bodyDef = new BodyDef();
		//bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(x, y);
		Body body = world.createBody(bodyDef);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;
		fixtureDef.isSensor = true;
		
		body.createFixture(fixtureDef);
		circle.dispose();
		
		return body;
	}
	
	/**
	 * Creates a square or rectangular sensor, a body that is not affected by 
	 * forces but that detects collisions with other bodies.
	 * @param width Width of the sensor
	 * @param height Height of the sensor.
	 * @param x Position on the x axis
	 * @param y Position on the y axis
	 * @return An initialized Body object with the indicated parameters is returned
	 */
	public Body createSquareSensor(float width, float height, float x, float y){
		PolygonShape square = new PolygonShape();
		square.setAsBox(width, height);
		
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(x, y);
		Body body = world.createBody(bodyDef);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = square;
		fixtureDef.isSensor = true;
		
		body.createFixture(fixtureDef);
		square.dispose();
		
		return body;
	}
	
}
