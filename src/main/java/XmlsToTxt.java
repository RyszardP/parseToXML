import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class XmlsToTxt {

    private final String[] xmlFiles;
    private final String resultFile;

    public XmlsToTxt(String[] xmlFiles, String resultFile) {
        this.xmlFiles = xmlFiles;
        this.resultFile = resultFile;
    }

    public void copyContent() {
        byte[] buffer = new byte[64 * 1024];
        OutputStream os = null;
        try {
            File result = new File(resultFile);
            os = new FileOutputStream(result);
            //Пишем каждый файл источника в результирующий
            for (String s : xmlFiles) {
                File source = new File(s);
                InputStream is = new FileInputStream(source);
                int readed;
                while ((readed = is.read(buffer)) > 0) {
                    os.write(buffer, 0, readed);
                }
                is.close();
            }
            os.close();
        } catch (FileNotFoundException ex) {
            System.err.println(ex.toString());
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }
    }

    public static void main(String[] args) {
        //Файлы источники
        String[] xmls = new String[]{
                "D://pars/09301.xml",};
        //Файл результата
        String txt = "D://pars/result.txt";
        XmlsToTxt parce = new XmlsToTxt(xmls, txt);
        parce.copyContent();
    }
}
 