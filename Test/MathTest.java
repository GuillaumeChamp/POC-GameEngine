public class MathTest {

    public static void main(String[] args) {
        for (int i=0;i<100;i++){
            int rand = (int) (Math.ceil((Math.random()*100-1)/25));
            System.out.println(rand);
        }
    }
}
