package csol.restapi1.demo.util;

public class NumberExtension {
    public static Integer intNullToZero(String string){
        try{
            return Integer.parseInt(string);
        } catch(Exception e){
            return 0;
        }
    }

    public static Integer intNullToNull(String string){
        try{
            return Integer.parseInt(string);
        } catch(Exception e){
            return null;
        }
    }
}
