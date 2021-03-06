package logic;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import logic.entities.Event;

public class ErrorsLog {
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 14,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 14,
            Font.BOLD);
    public static ByteArrayOutputStream bout;

    public static ByteArrayOutputStream getBout() {
        return bout;
    }

    public static ArrayList<String> errorsLog = new ArrayList<String>();

    public static ArrayList<String> getErrorsLog() {
        return errorsLog;
    }

    public static Document document = null;
    private static Anchor anchor;
    private static Chapter catPart;

    public static Document getDocument() {
        return document;
    }

    public static void createReportFile(String driverId, int numOfEvents) {
        try {
            anchor = new Anchor("Found errors", catFont);
            catPart = new Chapter(new Paragraph(anchor), 1);
            document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("/home/evgeniy/IdeaProjects/te.data.analyzer/Reports/Analyze_driver_" + driverId + ".pdf"));
            bout = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, bout);
            Paragraph article = new Paragraph();
            document.open();
            article.add(new Paragraph("DATA CHECK REPORT", catFont));
            article.add(new Paragraph("Driver ID: " + driverId, smallBold));
            article.add(new Paragraph("Date: " + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()), smallBold));
            article.add(new Paragraph("Checked events: " + numOfEvents, smallBold));
            document.add(article);
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void writeErrorsFromCsvFile(ArrayList<String> errorsLog){
        List errorsList = new List(List.ORDERED);
        errorsLog.stream().map(error -> new ListItem(error, redFont)).forEach(errorsList::add);
        try {
            document.add(errorsList);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
    public static void writeErrorsFromEvent(ArrayList<String> errorsLog, Event event, Event previousEvent) {
        Paragraph subPara = new Paragraph("Event Sequence = " + event.getEventSequence(), subFont);
        List errorsList = new List(List.ORDERED);
        errorsLog.stream().map(error -> new ListItem(error, redFont)).forEach(errorsList::add);
        List eventsSubList = new List(true, false, 30);
        eventsSubList.setListSymbol(new Chunk("", FontFactory.getFont(FontFactory.HELVETICA, 8)));
        eventsSubList.add("Current event: " + event.toString());
        eventsSubList.add("Previous event: " + previousEvent.toString());
        errorsList.add(eventsSubList);
        try {
            document.add(subPara);
            document.add(errorsList);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public static void writeResultsToFile() {
        document.close();
    }

}
