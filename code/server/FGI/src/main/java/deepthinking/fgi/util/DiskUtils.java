/********************************************
 * 磁盘操作工具类
 *
 * @author zwq
 * @create 2018-07-10
 *********************************************/

package deepthinking.fgi.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiskUtils {
	private static Logger logger = LoggerFactory.getLogger(DiskUtils.class);
	/**********************
	 * 获取磁盘总空间、空闲空间、已用空间
	 * @return
	 * @throws ParseException 
	 * @throws NumberFormatException 
	 */
	public static String getSpace() throws NumberFormatException, ParseException{
		String spaceStr = "";
//		File[] roots = File.listRoots();
//		long ltotleSpace = 0l;
//		long lusableSpace = 0l;
//		for (File _file : roots) {
//			ltotleSpace+=_file.getTotalSpace();
//			lusableSpace+=_file.getUsableSpace();
//		}
//		return spaceStr
//				+df.format((ltotleSpace/(1024*1024))/1024.0)+"G,"
//				+df.format(((ltotleSpace-lusableSpace)/(1024*1024))/1024.0)+"G,"
//				+df.format((lusableSpace/(1024*1024))/1024.0)+"G,"
//				+bfb(ltotleSpace-lusableSpace,ltotleSpace)+","
//				+bfb(lusableSpace,ltotleSpace);		
		Desk space = getDeskUsage();
		DecimalFormat df = new DecimalFormat("#0.00");
		
		return spaceStr
		+df.format(space.getTotal())+"G,"
		+df.format(space.getAvail())+"G,"
		+df.format(space.getUsed())+"G,"
		+bfb(space.getTotal()-space.getUsed(),space.getTotal())+","
		+bfb(space.getUsed(),space.getTotal());	
	}
	
	/**********************
	 * 计算百分比
	 * @return
	 */
	public static String bfb(Object num1, Object num2) {
        double val1 = Double.valueOf(num1.toString());
        double val2 = Double.valueOf(num2.toString());
        if (val2 == 0) {
            return "0.0%";
        } else {
            DecimalFormat df = new DecimalFormat("#0.00");
            return df.format(val1 / val2 * 100) + "%";
        }
    }
	
	
    public static class Desk {
        private double total =0.00;
        private double used=0.00;
        private double avail=0.00;
        
 
        public double getTotal() {
            return total;
        }
 
        public void setTotal(double total) {
            this.total += total;
        }
 
        public double getUsed() {
            return used;
        }
 
        public void setUsed(double used) {
            this.used += used;
        }
 
//        public long getUse_rate() {
//            return use_rate;
//        }
// 
//        public void setUse_rate(long use_rate) {
//            this.use_rate += use_rate;
//        }

		public double getAvail() {
			return avail;
		}

		public void setAvail(double avail) {
			this.avail += avail;
		}
        
 
    }
	
    public static Desk getDeskUsage() {
        Desk desk = new Desk();
        try {
            Runtime rt = Runtime.getRuntime();
            Process p = rt.exec("df -hl");// df -hl 查看硬盘空间
            BufferedReader in = null;
            try {
                in = new BufferedReader(new InputStreamReader(
                        p.getInputStream()));
                String str = null;
                String[] strArray = null;
                int line = 0;
                while ((str = in.readLine()) != null) {
                    line++;
                    
                    if (line < 2) {
                        continue;
                    }
                    int m = 0;
                    strArray = str.split(" ");
                    for (String para : strArray) {
                        if (para.trim().length() == 0)
                            continue;
                        if(m==0 && !para.trim().contains("/dev/"))
                        	continue;
                        ++m;
                        if (para.endsWith("G") || para.endsWith("M")) {
                        	Double spaceData = Double.parseDouble(para.substring(0, para.length()-1));
                            // 目前的服务器
                            if (m == 2) {
                            	desk.setTotal(para.endsWith("M")?spaceData/1024.00:spaceData);
                            }
                            if (m == 3) {
                            	desk.setUsed(para.endsWith("M")?spaceData/1024.00:spaceData);
                            }
                            if (m == 4) {
                            	desk.setAvail(para.endsWith("M")?spaceData/1024.00:spaceData);
                            }
                        }
//                        if (para.endsWith("%")) {
//                            if (m == 5) {
//                                desk.setUse_rate(Long.parseLong(para.substring(0, para.length()-1)));
//                            }
//                        }
                    }
                }
            } catch (Exception e) {
            	logger.error(e.getMessage());
//                e.printStackTrace();
            } finally {
                in.close();
            }
        } catch (Exception e) {
//            e.printStackTrace();
        	logger.error(e.getMessage());
        }
        return desk;
    }

}
