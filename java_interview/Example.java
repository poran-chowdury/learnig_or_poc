/**
 * @author TOWFIQUL ISLAM
 * @email towfiq.106@gmail.com
 * @since 4/4/22 3:15 PM
 */

public class Example {

    public static void main(String[] args) {

    }
}

class SingletonExample {
    private static SingletonExample object;

    public static void getOutput(){
        System.out.println("This is singleton class");
    }

    synchronized public static SingletonExample getObject(){
        if(object == null){
            object = new SingletonExample();
        }
        return object;
    }
}
