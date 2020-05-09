package deepthinking.fgi.util;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jagoLyv
 * 储存当前大系统中（第三方系统）的用户信息
 */
public class UserData {
    private static Map<String,User>users=new HashMap<>();

    public synchronized static void saveUser(String userName,String userId,Cookie cookie){
        User user=new User(userName,userId,cookie);
        users.put(userId,user);
    }

    public static  User getUser(String userId){
        return users.get(userId);
    }

    public static void removeUser(String userId){
        users.remove(userId);
    }
    public static String getUserIdFromCookie(Cookie[] cookies){
        String userId=null;
        if(cookies.length>0){
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("fgi")){
                    userId=cookie.getValue();
                }
            }
        }
        return userId;
    }

    static class User{
        private String userName;
        private String userId;
        private Cookie cookie;

        public User(String userName, String userId, Cookie cookie) {
            this.userName = userName;
            this.userId = userId;
            this.cookie = cookie;
        }

        public String getUserName() {
            return userName;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public Cookie getCookie() {
            return cookie;
        }

        public void setCookie(Cookie cookie) {
            this.cookie = cookie;
        }
    }
}
