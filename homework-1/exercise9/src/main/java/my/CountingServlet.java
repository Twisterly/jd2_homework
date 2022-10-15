package my;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


@WebServlet(name = "CountingServlet", urlPatterns = "/count.do")
public class CountingServlet extends HttpServlet {

    private static int counter;
    private static File file;

    public void init() {
        file = new File("D:\\counterSaver.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileReader fileReader = new FileReader(file);
            BufferedReader bfreader =
                    new BufferedReader(fileReader)){
            String line = null;
            if ((line = bfreader.readLine()) != null) {
                counter = Integer.parseInt(line);
            } else if ((bfreader.readLine()) == null) {
                counter = 0;
            }

        } catch (IOException e) {
            e.getMessage();
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        counter++;
        resp.setContentType("text/html");
        try (BufferedWriter bfwriter =
                     new BufferedWriter(new FileWriter(file))) {
            bfwriter.write("" + counter);
            bfwriter.flush();
        } catch (IOException e) {
            e.getMessage();
        }
        PrintWriter writer = resp.getWriter();
        writer.println("<h1>Welcome to my page</h1>");
        writer.println("<h2>Visit number: " + counter + "</h2" +
                ">");

    }


}

