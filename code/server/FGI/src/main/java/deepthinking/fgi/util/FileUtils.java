/********************************************
 * 文件操作工具类
 *
 * @author zwq
 * @create 2018-06-12
 *********************************************/

package deepthinking.fgi.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static javax.xml.transform.OutputKeys.ENCODING;

public class FileUtils {
	public static String readGzipFile(String targzFile) {

		Logger logger = LoggerFactory.getLogger(FileUtils.class);

		FileInputStream fis = null;
		ArchiveInputStream in = null;
		BufferedInputStream bufferedInputStream = null;
		StringBuffer line = new StringBuffer();

		try {
			fis = new FileInputStream(targzFile);
			BZip2CompressorInputStream is = new BZip2CompressorInputStream(new BufferedInputStream(fis));
			in = new ArchiveStreamFactory().createArchiveInputStream("tar", is);
			bufferedInputStream = new BufferedInputStream(in);

			TarArchiveEntry entry = null;
			LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
			final int maxsize = 10;
			ExecutorService executor = new ThreadPoolExecutor(maxsize, maxsize, 0l, TimeUnit.MILLISECONDS, queue);
			entry = (TarArchiveEntry) in.getNextEntry();
			while (entry != null) {
				long size = 0;
				String name = entry.getName();
				if (!name.endsWith("md5.txt")) {
					entry = (TarArchiveEntry) in.getNextEntry();
					continue;
				}
				String[] names = name.split("/");
				String fileName = targzFile;

				for (int i = 0; i < names.length; i++) {
					String str = names[i];
					fileName = fileName + File.separator + str;
				}
				if (!fileName.endsWith("/")) {
					size = entry.getSize();

				}
				if (size > 0) {
					byte[] b = new byte[(int) entry.getSize()];
					int len = 0;
					while ((len = in.read(b)) != -1) {
						line.append(new String(b, 0, len, "utf-8"));
					}
				} else {
				}
				entry = (TarArchiveEntry) in.getNextEntry();
			}
			in.close();
			fis.close();
			bufferedInputStream.close();

			executor.shutdown();
		} catch (Exception e1) {
			logger.error(e1.getMessage());
		}

		return line.toString();

	}

	/**
	 * 读文件
	 *
	 * @param filePath 读取文件路径
	 * @return 返回字符串
	 * @author 王若山
	 */
	public static String readTxtFile(String filePath) {
		File file = new File(filePath);
		if (!file.exists()) {
			return null;
		}
		StringBuffer stringBuffer = null;
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));
			stringBuffer = new StringBuffer();
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				if ("\r".equals(line)) {
					continue;
				}
				stringBuffer.append(line).append("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			closeBufferedReader(bufferedReader);
		}
		return stringBuffer.toString();
	}

	/**
	 * 关闭文件流
	 *
	 * @author Lius
	 * @date 2018/10/26 16:32
	 */
	public static void closeBufferedReader(BufferedReader bufferedReader) {
		if (bufferedReader != null) {
			try {
				bufferedReader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 写文件
	 * @param fileName
	 * @param content
	 * @author 王若山
	 * @return
	 */
	public static int writeFile(String fileName, byte[] content) {
		File file = new File(fileName);
		File fileparent = file.getParentFile();
		if (!fileparent.exists()) {
			System.out.println("文件夹不存在！");
//			fileparent.mkdirs();
		}
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(fileName);
			os.write(content);
			os.flush();

		} catch (Exception e) {
			System.out.println("写入文件异常:" + e.getMessage());
			return -1;
		} finally {
			try {
				if (null != os)
					os.close();
			} catch (IOException e) {
			}
		}
		os = null;
		return 0;
	}

	/**
	 * 获取windows/linux的项目根目录
	 * @author 王若山
	 * @return
	 */
	public static String getConTextPath(){
		String fileUrl = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		if("usr".equals(fileUrl.substring(1,4))){
			fileUrl = (fileUrl.substring(0,fileUrl.length()-16));//linux
		}else{
			fileUrl = (fileUrl.substring(1,fileUrl.length()-16));//windows
		}
		return fileUrl;
	}
	
	/*************
	 * 删除Linux文件
	 * @param path
	 * @return
	 */
	public static boolean delFile(String path) {
	    boolean flag = false;
	    File file = new File(path);
	    if (!file.exists()) {
	        return flag;
	    }
	    try{
	        flag = file.delete();
	    }catch (Exception e){
	        e.printStackTrace();
	    }
	    return flag;
	}

}
