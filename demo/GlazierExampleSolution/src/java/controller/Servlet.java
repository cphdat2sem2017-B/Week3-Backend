package controller;


import model.Calculator;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet")
public class Servlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public Servlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Calculator calc = null;
        try {
            int height = Integer.parseInt(request.getParameter("height"));
            int width = Integer.parseInt(request.getParameter("width"));
            String frametype_str = request.getParameter("frametype");
            Calculator.FRAMETYPE frametype;
            switch (frametype_str) {
                case "type1":
                    frametype = Calculator.FRAMETYPE.TYPE1;
                    break;
                case "type2":
                    frametype = Calculator.FRAMETYPE.TYPE2;
                    break;
                case "type3":
                    frametype = Calculator.FRAMETYPE.TYPE3;
                    break;
                default:
                    frametype = Calculator.FRAMETYPE.TYPE1;
                    break;
            }
            calc = new Calculator(height, width, frametype);
        } catch (Exception e) {
            response.sendRedirect("error.html");
            return;
        }

        ServletOutputStream out = response.getOutputStream();

        String str = "<!DOCTYPE html>"
                + "<html>"
                + "    <head>"
                + "        <meta charset=\"ISO-8859-1\">"
                + "        <title>Insert title here</title>"
                + "    </head>"
                + "    <body>"
                + "        <h2>Result page</h2>"
                + "        <p>"
                + "            <table>"
                + "                <tr>"
                + "                    <td>Glass price: </td>"
                + "                    <td></td>"
                + "                    <td>kr. " + String.format("%.2f", calc.getGlassPrice()) + "</td>"
                + "                </tr>"
                + "                <tr>"
                + "                    <td>Frame price: </td>"
                + "                    <td>+</td>"
                + "                    <td>kr. " + String.format("%.2f", calc.getFramePrice()) + "</td>"
                + "                </tr>"
                + "                <tr>"
                + "                    <td>Total price: </td>"
                + "                    <td>=</td>"
                + "                    <td>kr. " + String.format("%.2f", calc.getTotalPrice()) + "</td>"
                + "                </tr>"
                + "            </table>"
                + "        </p>"
                + "    </body>"
                + "</html>";

        out.println(str);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
