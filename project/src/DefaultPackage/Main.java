package DefaultPackage;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.*;
import java.lang.*;


public class Main {
    public static void main (String[] args) {
        NotaListener mainListener = new NotaListener();
        NotaBuilder notaBuilder = new NotaBuilder();
        notaBuilder.addListener(mainListener);
        try {
            File pdfFile = new File("C:\\Users\\Terminal\\Desktop\\UFSM\\2semestre\\project\\src\\DefaultPackage\\sample.pdf");
            PDDocument pdDocument = Loader.loadPDF(pdfFile);
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String text = pdfStripper.getText(pdDocument);
            System.out.println(text);
            notaBuilder.notaParser(text);
        } catch (Exception e) {
            e.printStackTrace();
        }


        InVoice inVoice = mainListener.build();
        int i = 0;
        System.out.printf("CPF: %s%nCNPJ: %s%nACCESSKEY: %s%nDATA: %s%nTIME: %s%nName: %s%n", inVoice.getCpf(), inVoice.getCnpj(), inVoice.getAccessKey(), inVoice.getDate(), inVoice.getTime(), inVoice.getName());
        for (Product product: inVoice.productList) {
            System.out.println("PRODUCT NUMBER " + i);
            System.out.println("CODE: " + product.getCode());
            System.out.println("NAME: " + product.getName());
            System.out.println("PRICE: " + product.getPrice());
            System.out.println("QUANTITY: " + product.getQuantity());
            System.out.println("MEASURE: " + product.getMeasure());
            System.out.println();
            System.out.println();
            i++;
        }

        sqlCon.alimentar(inVoice);
    }
}