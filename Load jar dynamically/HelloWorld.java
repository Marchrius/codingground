import java.net.URL;
import java.net.URLClassLoader;
import java.io.File;
import java.lang.reflect.Constructor;

public class HelloWorld {

     public static void main(String []args){
        EnumType type1 = EnumType.TEXT;
        EnumType type2 = EnumType.fromString("TdEXT");
        EnumType type3 = EnumType.valueOf("TEXT");
        
        System.out.println("Hello World " + type1 + " " + type2 + " " + type3);
        
        
        File f = new File("libs" + File.separator + "org.json.jar");
        URLClassLoader urlCl;
        try {
            urlCl = new URLClassLoader(new URL[] { f.toURI().toURL()}, System.class.getClassLoader());
            Class orgJsonJSONObject = urlCl.loadClass("org.json.JSONObject");
            Constructor constr = orgJsonJSONObject.getConstructor(String.class);
            Object obj = constr.newInstance("{\"key\":\"value\"}");
            System.out.println(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
     }
     
     enum EnumType {
         UNKNOWN("UNKNOWN"), TEXT("TEXT");
         
         private String type;
         
         EnumType(String type) {
            this.type = type;
         }
         
         static EnumType fromString(String possibleType) {
             try {
                 return EnumType.valueOf(possibleType);
             } catch (java.lang.IllegalArgumentException e) {
                 return EnumType.getDefault();
             }
         }
         
         static EnumType getDefault() {
             return EnumType.UNKNOWN;
         }
     }
}
