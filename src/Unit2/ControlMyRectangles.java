package Unit2;

public class ControlMyRectangles {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyRectangle one = new MyRectangle(1,2,4,5);
		MyRectangle two = new MyRectangle(4,5,6,10);
		
		System.out.println(one.getWidth());
		System.out.println(one.getHeight());
		System.out.println(one);
		
		one.setWidth(6); one.setHeight(7);
		System.out.println(one.getWidth());
		System.out.println(one.getHeight());
		System.out.println(one);
		
		System.out.println(one.area());
		System.out.println(two.area());
		
		System.out.println(one.compareTo(two));
		System.out.println(two.compareTo(one));
		System.out.println(one.compareTo(one));
		
		System.out.println(MyRectangle.getNumRectangles());
	}

}
