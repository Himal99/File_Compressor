package com.file.compressor;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
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

        PDImageXObject image = PDImageXObject.createFromFile("C:\\Users\\Himal Rai\\Desktop\\Phewa_lake,_Pokhara.jpg",document);
        PDPageContentStream contentStream = new PDPageContentStream(document,page);
        float imageWidth = image.getWidth();
        float imageHeight = image.getHeight();

        // Set the PDF page size to match the image size
        page.setMediaBox(new PDRectangle(imageWidth, imageHeight));

        contentStream.drawImage(image,0,0,imageWidth,imageHeight);
        contentStream.close();
        document.save("C:\\Users\\Himal Rai\\Desktop\\Phewa_lake,_Pokhara.pdf");
       document.close();;
    }
}
