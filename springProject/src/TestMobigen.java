import java.math.BigDecimal;

public class TestMobigen {

    public static void main(String args[]) {
        BigDecimal mul = new BigDecimal(1);
        int start = 1;
        BigDecimal result= recursive(mul, start);
        System.out.println("result : " + String.valueOf(result));
    }

    public static BigDecimal recursive(BigDecimal mul, int start) {

        if (start != 101) {
            // System.out.println(start);
        	mul = mul.multiply(new BigDecimal(String.valueOf(start)));
            // System.out.println(multiplication);
            start++;

            return recursive(mul, start);
        }else{
            return mul;
        }
    }
    
}
