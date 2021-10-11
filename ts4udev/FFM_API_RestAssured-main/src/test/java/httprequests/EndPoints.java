package httprequests;

import java.util.HashMap;

public class EndPoints {

    public static HashMap<String, String> endPoints = new HashMap<>();

    public static String registerFFMEndpoint(){
        return endPoints.get("baseURI") + endPoints.get("registerFFMMember");
    }

    public static String registerCompanyEndpoint(){
        return endPoints.get("baseURI") + endPoints.get("createCompany");
    }

    public static String registerCompanyManagerEndpoint(){
        return endPoints.get("baseURI") + endPoints.get("registerManager");
    }

    public static String loginEndpoint(){
        return endPoints.get("baseURI") + endPoints.get("login");
    }

    public static String authorizationEndpoint(){
        return endPoints.get("baseURI") + endPoints.get("authenticate");
    }

    public static String logoutEndpoint(){
        return endPoints.get("baseURI") + endPoints.get("logout");
    }

    public static String refreshTokenEndpoint(){
        return endPoints.get("baseURI") + endPoints.get("refreshToken");
    }

    public static String deleteCompanyEndpoint(String companyId){
        return endPoints.get("baseURIDelete") + String.format(endPoints.get("deleteCompany"), companyId);
    }

    public static String deleteCompanyManagerEndpoint(String managerId){
        return endPoints.get("baseURIDelete") + String.format(endPoints.get("deleteCompanyManager"), managerId);
    }

    public static String deleteMemberEndpoint(String memberId){
        return endPoints.get("baseURIDelete") + String.format(endPoints.get("deleteMember"), memberId);
    }
}
