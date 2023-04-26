import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfArray;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfName;
import com.itextpdf.kernel.pdf.PdfNumber;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.kernel.pdf.canvas.draw.DottedLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Tab;
import com.itextpdf.layout.element.TabStop;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.Property;
import com.itextpdf.layout.property.TabAlignment;
import com.itextpdf.layout.property.TextAlignment;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * 描述：
 *
 * @author tsc
 * @since 2023/4/25 10:07
 **/
public class TestTwo {


    /**
     * 生成一个带有目录(可跳转)的pdf文件(支持中文).
     * 如果目录两页,请为pdf多留出一页空白内容.
     * 此方法不能生成书签.
     *
     * @param sourceFile      源pdf文件
     * @param targetFile      带有目录(可跳转)的pdf文件
     * @param catalogs        目录数据map<标题,页码>
     * @param catalogTitle    目录上方第一行文字
     * @param catalogPageSize 目录占页大小(为锚点偏移做参数)
     * @return targetFile
     * @throws IOException
     */
    public static String createPdfWithCatalog(String sourceFile, String targetFile,

                                              String catalogTitle,
                                              Map<String, Integer> catalogs,
                                              int catalogPageSize) throws IOException {
        Map<String, Integer> catalogsTemp = new TreeMap<>();
        catalogsTemp.putAll(catalogs);
        //实际页码索引偏移量为 目录占页数-1
        int offset = catalogPageSize - 1;
        for (Map.Entry<String, Integer> entry : catalogsTemp.entrySet()) {
            entry.setValue(entry.getValue() + offset);
        }

        File file = new File(targetFile);
        file.getParentFile().mkdirs();
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(targetFile));
        Document document = new Document(pdfDoc);

        //    此方式会抛异常（com.itextpdf.io.IOException: The CMap com/itextpdf/io/font/cmap/UniGB-UTF16-H was not found.）所以采用本地字体
//    PdfFont font = PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H", false);
        PdfFont font = PdfFontFactory.createFont("C:\\Windows\\Fonts\\simsun.ttc,0", PdfEncodings.IDENTITY_H, false);

        if (catalogTitle == null) {
            catalogTitle = " ";
        }
        document.add(new Paragraph(new Text(catalogTitle))
                .setTextAlignment(com.itextpdf.layout.property.TextAlignment.CENTER).setFont(font));
        PdfDocument firstSourcePdf = new PdfDocument(new PdfReader(sourceFile));
        int numberOfPages = firstSourcePdf.getNumberOfPages();
        for (int i = 1; i <= numberOfPages; i++) {
            PdfPage page = firstSourcePdf.getPage(i).copyTo(pdfDoc);
            pdfDoc.addPage(page);
            for (Map.Entry<String, Integer> entry : catalogsTemp.entrySet()) {
                if (i == entry.getValue()) {
                    String destinationKey = "p" + (pdfDoc.getNumberOfPages() - 1);
                    PdfArray destinationArray = new PdfArray();
                    destinationArray.add(page.getPdfObject());
                    destinationArray.add(PdfName.XYZ);
                    destinationArray.add(new PdfNumber(0));
                    destinationArray.add(new PdfNumber(page.getMediaBox().getHeight()));
                    destinationArray.add(new PdfNumber(1));
                    try {
                        pdfDoc.addNamedDestination(destinationKey, destinationArray);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    Paragraph p = new Paragraph();
                    p.setFont(font);
                    p.addTabStops(new TabStop(540, TabAlignment.RIGHT, new DottedLine()));
                    p.add(entry.getKey());
                    p.add(new Tab());
                    p.add(String.valueOf(pdfDoc.getNumberOfPages() - 1));
                    p.setProperty(Property.ACTION, PdfAction.createGoTo(destinationKey));
                    document.add(p);
                }
            }
        }
        firstSourcePdf.close();
        document.close();
        return targetFile;
    }

    /**
     * 新改的方法 ,生成目录pdf ,只是为了方便计算页数.
     * 基于createPdfWithCatalog()代码改编,该方法进保留生成目录功能.
     *
     * @param sourceFile sourceFile
     * @param targetFile targetFile
     * @param catalogs   catalogs
     * @return 目录页数 page size
     * @throws IOException IOException
     */
    public static int createCatalogPdfOnly(String sourceFile, String targetFile, Map<String, Integer> catalogs) throws IOException {
        File file = new File(targetFile);
        file.getParentFile().mkdirs();
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(targetFile));
        Document document = new Document(pdfDoc);
        document.add(new Paragraph(new Text("The Revenant nominations list"))
                .setTextAlignment(TextAlignment.CENTER));
        PdfDocument firstSourcePdf = new PdfDocument(new PdfReader(sourceFile));
        for (Map.Entry<String, Integer> entry : catalogs.entrySet()) {
            Paragraph p = new Paragraph();
            p.addTabStops(new TabStop(540, TabAlignment.RIGHT, new DottedLine()));
            p.add(entry.getKey());
            p.add(new Tab());
            p.add(String.valueOf(pdfDoc.getNumberOfPages() - 1));
            document.add(p);
        }
        firstSourcePdf.close();
        int pages = pdfDoc.getNumberOfPages();
        document.close();
        return pages;
    }

    public static void main(String[] args) {

        /*Map<String, Integer> catalogs = new TreeMap<>();
        catalogs.put("封面", 1);
        catalogs.put("保单", 2);
        catalogs.put("现价", 3);
        catalogs.put("条款", 4);
        catalogs.put("投保资料-投保单", 12);
        catalogs.put("投保资料-合同审核函", 16);
        catalogs.put("投保资料-转账不成功通知函", 17);
        catalogs.put("投保资料-核保通知函", 18);
        catalogs.put("投保资料-体检报告中健康告知页", 19);
        catalogs.put("投保资料-首期保险费溢交通知书", 20);
        catalogs.put("投保资料-保险款项自动转账授权书", 21);
        catalogs.put("投保资料-未成年人投保特别告知书", 22);
        catalogs.put("收据与送达回执", 23);
        catalogs.put("封底", 24);


        try {
            int catalogPdfPageSize = createCatalogPdfOnly("E://blank.pdf", "E://bb.pdf", catalogs);
            createPdfWithCatalog("E://杭州职业技术学院教师发展诊改报告.pdf", "E://bb.pdf", "PICC电子保单", catalogs, catalogPdfPageSize);

        } catch (Exception e) {
            e.printStackTrace();
        }*/

        PdfToWordUtils.pdfToDoc("E://杭州职业技术学院教师发展诊改报告.pdf" , "E://杭州职业技术学院教师发展诊改报告.doc");

    }
}
