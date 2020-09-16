package logic;

import logic.dao.EventDAO;
import logic.entities.Event;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/getReport")
public class ReportServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EventDAO.setDriverId(req.getParameter("driverId"));
        EventDAO.setDateFrom(req.getParameter("dateFrom"));
        EventDAO.setDateTo(req.getParameter("dateTo"));
        resp.setHeader("Content-Disposition", "attachment;" +
                " filename=\"Report_Driver_"+EventDAO.getDriverId()+"_"+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date())+".pdf\"");
        resp.setHeader("Expires", "0");
        resp.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        resp.setHeader("Pragma", "public");
        resp.setContentType("application/pdf");
        List<Event> events = null;
        try {
            events = EventDAO.getEvents();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ErrorsLog.createReportFile(EventDAO.getDriverId(), events.size());
        new Analyzer(events).toAnalyzeEvent();
        ServletOutputStream outputStream = resp.getOutputStream();
        ErrorsLog.writeResultsToFile();
        ByteArrayOutputStream byteArrayOutputStream = ErrorsLog.getBout();
        resp.setContentLength(byteArrayOutputStream.size());
        byteArrayOutputStream.writeTo(outputStream);
        outputStream.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InputStreamReader reader = new InputStreamReader(req.getInputStream());

    }
}
