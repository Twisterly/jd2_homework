import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class PictureCountingServlet extends HttpServlet {

    private static int counter;
    private static File file;

    public void init() {
        file = new File("D:\\counterSaver2.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileReader fileReader = new FileReader(file);
                 BufferedReader bfreader =
                         new BufferedReader(fileReader)) {
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
        try (BufferedWriter bfwriter =
                     new BufferedWriter(new FileWriter(file))) {
            bfwriter.write("" + counter);
            bfwriter.flush();
        } catch (IOException e) {
            e.getMessage();
        }
        resp.setContentType("image/jpeg");
        BufferedImage image = new BufferedImage(500, 200,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setFont(new Font("Serif", Font.ITALIC, 48));
        graphics.setColor(Color.pink);
        graphics.drawString("Welcome to my page", 50, 50);
        graphics.drawString("Visit number:" + counter, 80, 100);
        ServletOutputStream out = resp.getOutputStream();
        ImageIO.write(image, "jpeg", out);
    }


}
