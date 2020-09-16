package logic;

import logic.csv.CsvReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;

@WebServlet("/getCsvReport")
public class CsvReportServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InputStreamReader reader = new InputStreamReader(req.getInputStream());
        CsvReader csvReader = new CsvReader(reader);
        csvReader.parseEldHeader();
    }
}
