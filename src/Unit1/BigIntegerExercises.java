package Unit1;
import java.math.*;
public class BigIntegerExercises {

	public static BigInteger fact(int n) {
		BigInteger res = new BigInteger("1");
		for (int i = 2; i <= n; ++i) res = res.multiply(BigInteger.valueOf(i));
		return res;
	}
	public static void main(String[] args) {
		System.out.println(fact(50000));
	}

}
