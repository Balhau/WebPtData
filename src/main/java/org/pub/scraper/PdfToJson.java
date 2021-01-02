package org.pub.scraper;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

/**
 * PDF is a document format which is very good for humans very bad for machines.
 * Many of the data is represented in PDF format which is a shame in some contexts.
 * One of the sad context is when we need to scrape the data to further analysis.
 * So this class aims to convert the pdf content into a structured JSON for further
 * analysis
 */
public class PdfToJson {
    private static final String FILE_NAME = "/Users/vitorfernandes/Documents/corona.pdf";

    public static class PdfLine {
        public String line;

        public PdfLine(String line) {
            this.line = line;
        }
    }

    public static class PdfContent {
        public List<PdfLine> lines;

        public PdfContent(List<PdfLine> lines) {
            this.lines = lines;
        }
    }


    public static void main(String[] args) throws Exception {

        PDDocument document = PDDocument.load(new File(FILE_NAME));

        PDFTextStripperByArea stripper = new PDFTextStripperByArea();
        stripper.setSortByPosition(true);

        PDFTextStripper tStripper = new PDFTextStripper();

        String pdfFileInText = tStripper.getText(document);
        //System.out.println("Text:" + st);

        // split by whitespace
        String lines[] = pdfFileInText.split("\\r?\\n");
        PdfContent content = new PdfContent(
                List.of(lines)
                        .stream()
                        .map(l -> new PdfLine(l)).collect(Collectors.toList())
        );

        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(content));

    }
}
