package xyz.hhjian.lib;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import xyz.hhjian.lib.entity.enums.BookStatusEnum;
import xyz.hhjian.lib.entity.enums.RoleEnum;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>TODO</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.10.21
 */
@RunWith(JUnit4.class)
public class DemoTest {

    @Test
    public void testEnum() {
        System.out.println(RoleEnum.USER.toString());
        System.out.println(RoleEnum.USER.getValue());
        System.out.println(RoleEnum.USER.getValue().toString());
        Arrays.stream(BookStatusEnum.values()).forEach(System.out::print);
    }

    @Test
    public void testCsv() {
        Pattern pattern = Pattern.compile("\\d+");
        String tar = "sfd/sd/123214/dsfa";
        Matcher matcher = pattern.matcher(tar);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

//    @Test
//    public void testJython() {
//        PythonInterpreter interpreter = new PythonInterpreter();
//        Properties properties = new Properties();
//        String pythonProjectPath = new File(".").getAbsoluteFile().getParentFile().getParent() + "/libdata";
////        String pythonMainPath = pythonProjectPath + "/libdata/test/test.py";
//        String pythonMainPath = pythonProjectPath + "/libdata/main/main.py";
//        interpreter.execfile(pythonMainPath);
//    }
//
//    @Test
//    public void testShell() {
//        String pythonProjectPath = new File(".").getAbsoluteFile().getParentFile().getParent() + "/libdata";
////        String pythonMainPath = pythonProjectPath + "/libdata/test/test.py";
//        String pythonMainPath = pythonProjectPath + "/libdata/main/main.py";
//        try {
//            Process process3 = Runtime.getRuntime().exec("/bin/bash");
//            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(process3.getOutputStream())), true);
//            out.print("cd ../libdata/libdata/main");
////            Process process = Runtime.getRuntime().exec("/usr/bin/python3 " + pythonMainPath);
//            Process process = Runtime.getRuntime().exec("/usr/bin/python3 main.py");
//            InputStream inputStream = process.getInputStream();
//            byte[] buf = new byte[1024];
//            int length = 0;
//            while ((length = inputStream.read(buf)) != -1) {
//                System.out.println(new String(buf, 0, length));
//            }
//            process.destroy();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
