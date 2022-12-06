package extrengthsupplements.extrength.Utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import extrengthsupplements.extrength.DTO.BillDTO;
import extrengthsupplements.extrength.Services.ProductServices;
import extrengthsupplements.extrength.models.Client;
import extrengthsupplements.extrength.models.Product;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PdfGenerator {



    public static void createBill (List<Long> ids, BillDTO billDTO, Client client, ProductServices productServices){
        Font titleFont = new Font(Font.FontFamily.COURIER,20);
        Font subFont = new Font(Font.FontFamily.HELVETICA,11);
        Font headersFont = new Font(Font.FontFamily.HELVETICA,15);

        try {
            Document document = new Document(PageSize.A4);

            PdfWriter.getInstance(document, new FileOutputStream("c:/temp/bill.pdf"));

            document.open();
            document.setMargins(1,1,1,1);





            Paragraph titleClient = new Paragraph( client.getFirstName()+" "+ client.getLastName(),titleFont);
            titleClient.setSpacingAfter(3);
            titleClient.setAlignment(Element.ALIGN_CENTER);
            titleClient.setSpacingBefore(-2);

            Paragraph subTitle = new Paragraph("Invoice number: " + billDTO.getNumber(),subFont);
            subTitle.setAlignment(Element.ALIGN_CENTER);
            subTitle.setSpacingAfter(1);

            Paragraph date = new Paragraph("Current date: " + LocalDate.now(), subFont);
            date.setSpacingAfter(6);
            date.setAlignment(Element.ALIGN_CENTER);




            Image img = Image.getInstance("src/main/resources/static/web/assets/images/logoPagina.png");
            img.scaleAbsoluteWidth(70);
            img.scaleAbsoluteHeight(70);
            img.setAlignment(Element.ALIGN_CENTER);

            PdfPTable pdfPTable = new PdfPTable(4);
            PdfPCell pdfPCell1 = new PdfPCell(new Paragraph("Name", headersFont));
            PdfPCell pdfPCell3 = new PdfPCell(new Paragraph("Price", headersFont));
            PdfPCell pdfPCell4 = new PdfPCell(new Paragraph("Quantity", headersFont));
            PdfPCell pdfPCell5 = new PdfPCell(new Paragraph("Total amount",headersFont));
            pdfPCell1.setBorder(0);
            pdfPCell3.setBorder(0);
            pdfPCell4.setBorder(0);
            pdfPCell5.setBorder(0);
            pdfPCell1.setPadding(1);
            pdfPCell3.setPadding(2);
            pdfPCell4.setPadding(2);
            pdfPCell5.setPadding(2);
            pdfPCell1.setBackgroundColor(new BaseColor(255, 192, 0));
            pdfPCell3.setBackgroundColor(new BaseColor(255, 192, 0));
            pdfPCell4.setBackgroundColor(new BaseColor(255, 192, 0));
            pdfPCell5.setBackgroundColor(new BaseColor(255, 192, 0));
            pdfPTable.addCell(pdfPCell1);
            pdfPTable.addCell(pdfPCell3);
            pdfPTable.addCell(pdfPCell4);
            pdfPTable.addCell(pdfPCell5);

            Set<Product> setProducts = new HashSet<>(productServices.findAllProductsById(ids));
            List<Product> products =   ids.stream().map(productServices::findProductById).collect(Collectors.toList());

            System.out.println(products);

            setProducts.forEach(product -> {
                int quantity = Collections.frequency(products.stream().map(Product::getName).collect(Collectors.toList()), product.getName());
                System.out.println(quantity);
                PdfPCell pdfPCell6 = new PdfPCell(new Paragraph(product.getName() , subFont));
                PdfPCell pdfPCell8 = new PdfPCell(new Paragraph("$" + String.valueOf(product.getPrice()), subFont));
                PdfPCell pdfPCell9 = new PdfPCell(new Paragraph(String.valueOf(quantity), subFont));
                PdfPCell pdfPCell10 = new PdfPCell(new Paragraph("$"+String.valueOf(product.getPrice()*quantity)));
                pdfPCell6.setBorder(1);
                pdfPCell8.setBorder(1);
                pdfPCell9.setBorder(1);
                pdfPCell10.setBorder(1);

                pdfPTable.addCell(pdfPCell6);
                pdfPTable.addCell(pdfPCell8);
                pdfPTable.addCell(pdfPCell9);
                pdfPTable.addCell(pdfPCell10);
            });

            document.add(img);

            document.add(titleClient);
            document.add(subTitle);
            document.add(date);
            document.add(pdfPTable);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
