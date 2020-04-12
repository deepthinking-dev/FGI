package deepthinking.fgi.util;


import java.io.*;
import org.apache.commons.io.IOUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author jlm
 * @ClassName:
 * @Description: 文件上传工具类
 * @date 2018/11/14
 */
public class FileUploadUtil {

    public static String uploadFile(String filepath, MultipartFile file, String suffix)
            throws IOException {

        File dir = new File(filepath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File serverFile = new File(
                dir.getAbsolutePath() + "/" + String.valueOf(System.currentTimeMillis()) + "." + suffix);
        BufferedOutputStream stream = null;
        try {
            stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
        return serverFile.getPath();
    }

    /**
     * MultipartFile 转 File
     * @param file
     * @throws Exception
     */
    public static File multipartFileToFile(MultipartFile file) throws Exception {
        File toFile = null;
        InputStream ins = null;
        ins = file.getInputStream();
        toFile = new File(file.getOriginalFilename());
        inputStreamToFile(ins, toFile);
        ins.close();
        return toFile;
    }

    /**
     * File 转 MultipartFile
     * @param file
     * @throws Exception
     */
    public static void fileToMultipartFile( File file ) throws Exception {
        FileInputStream fileInput = new FileInputStream(file);
        MultipartFile toMultipartFile = new MockMultipartFile("file",file.getName(),"text/plain", IOUtils.toByteArray(fileInput));
        toMultipartFile.getInputStream();

    }


    public static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
