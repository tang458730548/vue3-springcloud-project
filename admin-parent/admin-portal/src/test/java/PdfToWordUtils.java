import com.aspose.pdf.Document;
import com.aspose.pdf.License;
import com.aspose.pdf.OutlineCollection;
import com.aspose.pdf.SaveFormat;
import com.aspose.words.LayoutCollector;
import com.aspose.words.Paragraph;
import com.aspose.words.Section;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PdfToWordUtils {

    private static final String LICENSE = "<License><Data><Products><Product>Aspose.Total for Java</Product><Product>Aspose.Words for Java</Product></Products><EditionType>Enterprise</EditionType><SubscriptionExpiry>20991231</SubscriptionExpiry><LicenseExpiry>20991231</LicenseExpiry><SerialNumber>8bfe198c-7f0c-4ef8-8ff0-acc3237bf0d7</SerialNumber></Data><Signature>sNLLKGMUdF0r8O1kKilWAGdgfs2BvJb/2Xp8p5iuDVfZXmhppo+d0Ran1P9TKdjV4ABwAgKXxJ3jcQTqE/2IRfqwnPf8itN8aFZlV3TJPYeD3yWE7IT55Gz6EijUpC7aKeoohTb4w2fpox58wWoF3SNp6sK6jDfiAUGEHYJ9pjU=</Signature></License>";

    public static File pdfToDoc(String pdfPath, String docPath) {
        File pdfFile = new File(docPath);
        try {
            ByteArrayInputStream is = new ByteArrayInputStream(LICENSE.getBytes());
            License license = new License();
            license.setLicense(is);
            Document document = new Document(pdfPath);
            FileOutputStream outputStream = new FileOutputStream(pdfFile);
            document.save(outputStream, SaveFormat.Doc);
            outputStream.close();
            is.close();
        } catch (Exception e) {
            System.out.println("转化失败");
            e.printStackTrace();
        }
        return pdfFile;
    }

    public static File pdfToDoc(InputStream pdfPathInputStream, String docPath) {
        File pdfFile = new File(docPath);
        try {
            ByteArrayInputStream is = new ByteArrayInputStream(LICENSE.getBytes());
            License license = new License();
            license.setLicense(is);
            Document document = new Document(pdfPathInputStream);
            FileOutputStream outputStream = new FileOutputStream(pdfFile);
            document.save(outputStream, SaveFormat.Doc);
            outputStream.close();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pdfFile;
    }

    private static com.aspose.words.Document createDocumentWithBookmarks(List<Map<String, Object>> catalogMapList,
                                                                         String fileName) throws Exception {
        com.aspose.pdf.Document pdf = new com.aspose.pdf.Document(fileName);

        com.aspose.words.Document doc = new com.aspose.words.Document(fileName);

        // 后面需要用这个对象去获取当前段落所在的页码
        LayoutCollector layoutCollector = new LayoutCollector(doc);
        // 需要获取所有的section，要不然部分word提取目录不完整
        Section[] sections = doc.getSections().toArray();
        // 获取所有的段落
        List<Paragraph> paragraphs = new ArrayList<>();
        for (Section s :
                sections) {
            paragraphs.addAll(Arrays.asList(s.getBody().getParagraphs().toArray()));
        }
//        // 获取标题的关键代码。标题对应的值为0-8，提取自己所需标题即可
//        p.getParagraphFormat().getOutlineLevel();
//        // 获取页码
//        layoutCollector.getEndPageIndex(p);
//        // 获取标题名称
//        p.getListLabel().getLabelString() + " " + p.getText();
        return doc;
    }

    public static void main(String[] args) throws Exception {
//        PdfToWordUtils.pdfToDoc("E://杭州职业技术学院教师发展诊改报告.pdf", "E://杭州职业技术学院教师发展诊改报告.doc");
//        FileOutputStream outputStream = new FileOutputStream("E://杭州职业技术学院教师发展诊改报告1.doc");

        Document pdf = new Document("E://杭州职业技术学院教师发展诊改报告.pdf");
        OutlineCollection outlines = pdf.getOutlines();


//        List<Map<String, Object>> catalogMapList = new ArrayList<>();
//        Map<String, Object> map = new HashMap<>();
//        map.put("name", "测试1");
//        catalogMapList.add(map);
//        Map<String, Object> map2 = new HashMap<>();
//        map2.put("name", "测试2");
//        catalogMapList.add(map2);
//        Map<String, Object> map3 = new HashMap<>();
//        map3.put("name", "测试3");
//        catalogMapList.add(map3);
//        com.aspose.words.Document document = createDocumentWithBookmarks(catalogMapList, "E://杭州职业技术学院教师发展诊改报告.doc");
//        document.save(outputStream, com.aspose.words.SaveFormat.DOC);

    }

}

