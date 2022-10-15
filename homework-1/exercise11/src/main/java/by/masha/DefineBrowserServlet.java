package by.masha;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DefineBrowserServlet extends HttpServlet {

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException,
            IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String browserDetails = request.getHeader("User-Agent");
        String userAgent = browserDetails;
        String user = userAgent.toLowerCase();
        String browser = "";
        PrintWriter out = response.getWriter();

        if (user.contains("msie")) {
            browser = "Internet Explorer";
        } else if (user.contains("safari") && user.contains("version")) {
            browser = "Safari";
        } else if (user.contains("opr") || user.contains("opera")) {
            browser = "Opera";
        } else if (user.contains("chrome") && !(user.contains("edg"))
                && !(user.contains("yabrowser"))) {
            browser = "Chrome";
        } else if (user.contains("navigator")) {
            browser = "Netscape Navigator";
        } else if (user.contains("firefox")) {
            browser = "Firefox";
        } else if (user.contains("safari") && user.contains("edg")) {
            browser = "Microsoft Edge";
        } else if (user.contains("yabrowser")) {
            browser = "Yandex";
        } else {
            browser = "UnKnown: " + userAgent;
        }
        out.write("Приветствую пользователя " + browser);
    }

}
