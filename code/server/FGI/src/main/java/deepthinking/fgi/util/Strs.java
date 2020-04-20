package deepthinking.fgi.util;

import com.google.common.collect.Sets;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author jagoLyu
 * @Description:
 * @data 2020/3/1 10:39
 *
 *
 * 字符串通用操作
 */
public class Strs {
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static String regexVars = "\\W|\\d|sin|cos|tan|cot|log|ln|sqrt|_|arcsin|arccos|alpha|beta|gamma|delta|epsilon|varepsilon" +
            "|zeta|eta|theta|vartheta|iota|kappa|lambda|mu|nu|xi|omicron|pi|rho|sigma|phi|varphi|psi|chi|omega|overline|overleftarrow|overbrace" +
            "|frac|times|dim|pmod|nabla|partial|forall|approx|Delta|dot|ggg|gg|>|<|ge|geqq|leq|leqq|equiv|ne|propto|pm|\\$|vert|\\{|\\}|\\/|\\[|\\]|int";// 需要剔除,剩下变量

    /**
     * 判断是否是空值或者纯空格
     *
     * @param str
     * @return
     */
    public static boolean isNullOrEmpty(String str) {
        return (str == null) || (str.trim().isEmpty());
    }

    public static boolean not_emptynull(String str) {
        return !isNullOrEmpty(str);
    }

    /**
     * 判断是否是数字(不包含科学计数)
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        Objects.requireNonNull(str);
        return str.trim().matches("-?\\d+(.\\d+)?");
    }

    /**
     * 判断是否是地理纬度
     *
     * @param str
     * @return
     */
    public static boolean isLatitude(String str) {
        Objects.requireNonNull(str);
        if (isNumber(str)) {
            double val = Double.valueOf(str);
            return val >= -90.0 && val <= 90.0;
        }
        return str.trim().matches("\\d{1,2}°(\\d{1,2}′(\\d{1,2}″)?)?[N|S]");
    }

    /**
     * 判断是否是地理经度
     *
     * @param str
     * @return
     */
    public static boolean isLongitude(String str) {
        Objects.requireNonNull(str);
        if (isNumber(str)) {
            double val = Double.valueOf(str);
            return val >= -180.0 && val <= 180.0;
        }
        return str.trim().matches("\\d{1,3}°(\\d{1,2}′(\\d{1,2}″)?)?[E|W]");
    }

    /**
     * 地理纬度转数字
     *
     * @param str
     *            数字或者度分秒格式纬度
     * @return 成功返回数字，失败返回null
     */
    public static Double toLatVal(String str) {
        Objects.requireNonNull(str);
        if (isNumber(str)) {
            double val = Double.valueOf(str);
            return (val >= -90.0 && val <= 90.0) ? val : null;
        }
        String var0 = str.trim().toUpperCase();
        if (!var0.matches("\\d{1,2}°(\\d{1,2}′(\\d{1,2}″)?)?[N|S]"))
            return null;
        String var1 = var0.replaceAll("[°|′|″|N|S]", ".");
        String[] var2 = var1.split("\\.");
        double d = (var2.length <= 0) ? 0 : (isNullOrEmpty(var2[0]) ? 0 : Double.valueOf(var2[0]));
        double f = (var2.length <= 1) ? 0 : (isNullOrEmpty(var2[1]) ? 0 : Double.valueOf(var2[1]));
        double m = (var2.length <= 2) ? 0 : (isNullOrEmpty(var2[2]) ? 0 : Double.valueOf(var2[2]));
        if (d > 90.0 || d < -90.0 || f < 0 || f > 60 || m < 0 || m > 60)
            return null;
        return (d + f / 60 + m / 3600) * (var0.endsWith("S") ? -1 : 1);
    }

    /**
     * 地理经度转数字
     *
     * @param str
     *            数字或者度分秒格式经度
     * @return 成功返回数字，失败返回null
     */
    public static Double toLonVal(String str) {
        Objects.requireNonNull(str);
        if (isNumber(str)) {
            double val = Double.valueOf(str);
            return (val >= -180.0 && val <= 180.0) ? val : null;
        }
        String var0 = str.trim().toUpperCase();
        if (!var0.matches("\\d{1,3}°(\\d{1,2}′(\\d{1,2}″)?)?[W|E]"))
            return null;
        String var1 = var0.replaceAll("[°|′|″|W|E]", ".");
        String[] var2 = var1.split("\\.");
        double d = (var2.length <= 0) ? 0 : (isNullOrEmpty(var2[0]) ? 0 : Double.valueOf(var2[0]));
        double f = (var2.length <= 1) ? 0 : (isNullOrEmpty(var2[1]) ? 0 : Double.valueOf(var2[1]));
        double m = (var2.length <= 2) ? 0 : (isNullOrEmpty(var2[2]) ? 0 : Double.valueOf(var2[2]));
        if (d > 180.0 || d < -180.0 || f < 0 || f > 60 || m < 0 || m > 60)
            return null;
        return (d + f / 60 + m / 3600) * (var0.endsWith("W") ? -1 : 1);
    }

    /**
     * 格式化地理纬度
     *
     * @param lat
     *            纬度数值
     * @return 度分秒表示法
     */
    public static String formatLat(double lat) {
        StringBuilder sb = new StringBuilder();
        double val = Math.abs(lat);
        int d = (int) Math.floor(val);
        int f = (int) Math.floor((val - d) * 60);
        int m = (int) Math.floor(((val - d) * 60 - f) * 60);
        sb.append(String.valueOf(Math.abs(d))).append("°");
        sb.append(String.valueOf(f)).append("′");
        sb.append(String.valueOf(m)).append("″");
        sb.append(lat >= 0 ? 'N' : 'S');
        return sb.toString();
    }

    /**
     * 格式化地理经度
     *
     * @param lon
     *            经度数值
     * @return 度分秒表示法
     */
    public static String formatLon(Double lon) {
        StringBuilder sb = new StringBuilder();
        double val = Math.abs(lon);
        int d = (int) Math.floor(val);
        int f = (int) Math.floor((val - d) * 60);
        int m = (int) Math.floor(((val - d) * 60 - f) * 60);
        sb.append(String.valueOf(Math.abs(d))).append("°");
        sb.append(String.valueOf(f)).append("′");
        sb.append(String.valueOf(m)).append("″");
        sb.append(lon >= 0 ? 'E' : 'W');
        return sb.toString();
    }

    /**
     * 【1584112W】格式的字符串转【小数】格式经纬度
     *
     * @param LonLat
     * @param isLat
     * @return
     */
    public static double ConvertStr2LonLat(String LonLat,boolean isLat){

        if(null == LonLat || LonLat.equals("")){
            return 0.0;
        }

        String reg = "[\\+\\-EWSN]";
        String strLonLat = LonLat.replaceAll(reg, "");

        String str_sFormat = strLonLat.substring(strLonLat.length()-2, strLonLat.length());
        String temp_mFormat = strLonLat.substring(strLonLat.length()-4, strLonLat.length());
        String str_mFormat = temp_mFormat.substring(0, 2);
        String str_dFormat = strLonLat.replace(temp_mFormat, "");

        double degree = Double.parseDouble(str_dFormat);
        double minite = Double.parseDouble(str_mFormat);
        double second = Double.parseDouble(str_sFormat);

        double result = degree+minite/60+second/3600;

        if(LonLat.contains("W")||LonLat.contains("S")){
            result = result*-1;
        }

        return result;
    }


    /**
     * 字符串分割
     *
     * @param src
     * @param splitter
     * @return
     */
    public static ArrayList<String> split(String src, char splitter) {
        ArrayList<String> result = new ArrayList<>();
        if (src == null)
            return result;
        char[] chars = src.toCharArray();
        int before = 0;
        int size = chars.length;
        for (int i = 0; i < size; i++) {
            if (chars[i] == splitter) {
                if (before < i) {
                    result.add(src.substring(before, i));
                }
                before = i + 1;
            }
        }
        if (before < size) {
            result.add(src.substring(before));
        }
        return result;
    }

    private static class StringSplitter {
        public int Begin;
        public int End;

        public StringSplitter(int begin, int end) {
            this.Begin = begin;
            this.End = end;
        }
    }

    public static String[] split(String src, String splitter) {
        if (src == null)
            return null;
        int begin = 0, end = -1;
        LinkedList<StringSplitter> list = new LinkedList<>();
        while ((end = src.indexOf(splitter, begin)) != -1) {
            if (begin < end) {
                list.add(new StringSplitter(begin, end));
            }
            begin = end + 1;
        }
        if (begin < src.length() - 1)
            list.add(new StringSplitter(begin, src.length() - 1));
        String[] result = new String[list.size()];
        for (StringSplitter item : list) {
            result[++end] = src.substring(item.Begin, item.End);
        }
        return result;
    }

    public static String dateToString(Date date) {
        return simpleDateFormat.format(date);
    }

    public static Date toDateTime(String date) throws ParseException {
        return simpleDateFormat.parse(date);
    }

    /**
     * str中出现了几次sub
     */
    public static int countOccurrencesOf(String str, String sub) {
        if (isNullOrEmpty(str) || isNullOrEmpty(sub))
            return 0;
        int count = 0;
        int pos = 0;
        int idx;
        while ((idx = str.indexOf(sub, pos)) != -1) {
            ++count;
            pos = idx + sub.length();
        }
        return count;
    }

    /**
     * str中包含的sub是否为偶数次
     */
    public static boolean countIsEven(String str, String sub) {
        return countOccurrencesOf(str, sub) % 2 == 0;
    }

    public static String toLike(String str) {
        return str == null ? "%%" : "%" + str + "%";
    }

    public static Set<String> takeVar(String target) {
        return takeVar(regexVars, target);
    }

    public static Set<String> takeVar(String regex, String target) {
        String[] split = target.split(regex);
        Set<String> vars = Sets.newLinkedHashSet();
        for (String var : split)
            if (not_emptynull(var))
                vars.add(var);
        return vars;
    }

}
