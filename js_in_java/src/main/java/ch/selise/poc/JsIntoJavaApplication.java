package ch.selise.poc;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.net.URISyntaxException;

public class JsIntoJavaApplication {

    public static void main(String[] args) throws ScriptException, URISyntaxException {
        ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("Nashorn");
//        URL resource = JsIntoJavaApplication.class.getClassLoader().getResource("js/test.js");
        String script = "var greeting='hello world';" +
                "print(greeting);" +
                "greeting";
//        scriptEngine.eval(String.valueOf(new File(resource.toURI())));
        scriptEngine.eval(script);
    }
}
