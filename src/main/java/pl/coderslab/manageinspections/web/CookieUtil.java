package pl.coderslab.manageinspections.web;

import org.springframework.context.annotation.Bean;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
public class CookieUtil {
    public CookieUtil() {
    }

    public Long getSiteIdCookieValue(HttpServletRequest request) {
        String siteIdString = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("siteId")) {
                siteIdString = cookie.getValue();
                Long siteId = Long.parseLong(siteIdString);
                return siteId;
            }
        }
        return null;
    }
}
