package httprequests;

import enums.HeadersEnum;

import java.util.HashMap;

public class Headers {

    private static final HashMap<String, String> headersMap= new HashMap<>();

    //Add headers
    public static void addAHeader(String headerName, String headerValue){
        if (headersMap.containsKey(headerName)){
            headersMap.replace(headerName,headerValue);
        }else{
            headersMap.put(headerName,headerValue);
        }
    }

    //Return headers values
    public static HashMap<String,String> getHeaders(){
        headersMap.put(HeadersEnum.APPLICATION_JSON.getHeaderKey(), HeadersEnum.APPLICATION_JSON.getHeaderValue());
        return headersMap;


    }
}
