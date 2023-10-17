package com.file.compressor;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

/**
 * @author Himal Rai on 10/17/2023
 * Sb Solutions Nepal pvt.ltd
 * Project compressor.
 */
public class ImageToPdf {
    public static void main(String[] args) throws Exception{
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDImageXObject image = PDImageXObject.createFromFile("C:\\Users\\Himal Rai\\Desktop\\NMB-LAGHUBITTA.jpg",document);
        PDPageContentStream contentStream = new PDPageContentStream(document,page);
        contentStream.drawImage(image,100,110,450,400);
        contentStream.close();
        document.save("C:\\Users\\Himal Rai\\Desktop\\NMB-LAGHUBITTAPDF.pdf");
       document.close();;
    }
}
