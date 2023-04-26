/**
 * 描述：
 *
 * @author tsc
 * @since 2023/4/12 17:20
 **/
public class TestOne {

    public static void main(String[] args) {
        String s1 = "E:\\2020-2021年度专业建设质量诊断与改进报告.pdf";

        String s2 = "/home/jxcp/2020-2021年度专业建设质量诊断与改进报告.pdf";

        String replace = s1.replaceAll("\\\\", "/");
        System.out.println(replace);


        int i = s1.lastIndexOf("/");


        String substring = s1.substring(i+1);
        System.out.println(substring);

    }
}
