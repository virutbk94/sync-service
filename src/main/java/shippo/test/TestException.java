package shippo.test;

import java.io.PrintWriter;
import java.io.StringWriter;

public class TestException {
    public static void main(String[] args) {
        try{
            int a =7/0;
        }catch (Exception e){
            System.out.println(e + "haaha");
            StringWriter writer = new StringWriter();
            e.printStackTrace(new PrintWriter(writer));
            System.out.println(writer);
        }
    }
}
