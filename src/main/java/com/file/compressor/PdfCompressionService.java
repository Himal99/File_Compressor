package com.file.compressor;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Himal Rai on 10/17/2023
 * Sb Solutions Nepal pvt.ltd
 * Project compressor.
 */
public class PdfCompressionService {
    public static void main(String[] args) {
        String inputFilePath = "C:\\Users\\Himal Rai\\Desktop\\cibLogo.png";
        String outputFilePath = "C:\\Users\\Himal Rai\\Desktop\\cibLogoCpmp.png";
        File inputFile = new File(inputFilePath);
        String fileExtension = getFileExtension(inputFile);

        if (fileExtension != null) {
            if (fileExtension.equalsIgnoreCase("jpg") || fileExtension.equalsIgnoreCase("jpeg")) {
                // Example compression level for JPEG images (adjust the quality)
                compressJPEG(inputFile, outputFilePath + ".jpg", 0.7f); // Adjust the quality (0.0f to 1.0f)
                System.out.println("JPEG image compression completed successfully.");
            } else if (fileExtension.equalsIgnoreCase("png")) {
                // Example compression level for PNG images
                compressPNG(inputFile, outputFilePath + ".png", 0.1f); // Adjust the compression level (0.0f to 1.0f)
                System.out.println("PNG image compression completed successfully.");
            } else if (fileExtension.equalsIgnoreCase("pdf")) {
                // Compress the PDF using Ghostscript
                String gsExecutable = "C:\\Program Files\\gs\\gs10.02.0\\bin\\gswin64c.exe"; // Update with the actual path
                String[] command = new String[]{
                        gsExecutable,
                        "-sDEVICE=pdfwrite",
                        "-dCompatibilityLevel=1.4",
                        "-dPDFSETTINGS=/ebook",
                        "-dNOPAUSE",
                        "-dQUIET",
                        "-dBATCH",
                        "-sOutputFile=" + outputFilePath + ".pdf",
                        inputFilePath
                };

                try {
                    // Execute the Ghostscript command
                    Process process = Runtime.getRuntime().exec(command);
                    process.waitFor();

                    int exitStatus = process.exitValue();
                    if (exitStatus == 0) {
                        System.out.println("PDF compression completed successfully.");
                    } else {
                        System.err.println("PDF compression failed with exit code: " + exitStatus);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Unsupported file type. No compression performed.");
            }
        } else {
            System.out.println("File extension not recognized. No compression performed.");
        }
    }

    private static void compressPNG(File inputFile, String outputFilePath, float compressionLevel) {
        try {
            BufferedImage image = ImageIO.read(inputFile);

            // Create an ImageWriter for PNG format
            ImageWriter imageWriter = ImageIO.getImageWritersByFormatName("png").next();

            // Specify the compression settings for PNG
            ImageWriteParam writeParam = imageWriter.getDefaultWriteParam();
            writeParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            writeParam.setCompressionType("Deflate"); // Use "Deflate" for better compression
            writeParam.setCompressionQuality(compressionLevel);

            // Write the image to the output file with the specified compression settings
            imageWriter.setOutput(ImageIO.createImageOutputStream(new File(outputFilePath)));
            imageWriter.write(null, new IIOImage(image, null, null), writeParam);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void compressJPEG(File inputFile, String outputFilePath, float compressionQuality) {
        try {
            BufferedImage image = ImageIO.read(inputFile);

            // Create an ImageWriter for JPEG format
            ImageWriter imageWriter = ImageIO.getImageWritersByFormatName("jpg").next();

            // Specify the compression quality for the output JPEG image
            // Adjust the quality (0.0f to 1.0f) to control the quality and size
            ImageWriteParam writeParam = imageWriter.getDefaultWriteParam();
            writeParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            writeParam.setCompressionQuality(compressionQuality); // Adjust the quality

            // Write the image to the output file with the specified compression settings
            imageWriter.setOutput(ImageIO.createImageOutputStream(new File(outputFilePath)));
            imageWriter.write(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0) {
            return fileName.substring(dotIndex + 1).toLowerCase();
        }
        return null;
    }

}
