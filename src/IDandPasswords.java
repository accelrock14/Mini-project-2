import java.util.HashMap;

public class IDandPasswords {

    HashMap<String, String> logininfo = new HashMap<String, String>();

    IDandPasswords() {

        logininfo.put("admin", "secure");
        logininfo.put("user", "PASSWORD");
        logininfo.put("kevin", "abc123");
    }

    // public HashMap getLoginInfo() {
    // return logininfo;
    // }
}
