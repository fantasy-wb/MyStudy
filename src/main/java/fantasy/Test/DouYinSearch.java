package fantasy.Test;

import org.jsoup.Jsoup;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DouYinSearch {

    public static void main(String[] args) throws Exception{

        //●抖音链接(使用手机分享功能,复制链接)
        String url = "http://v.douyin.com/JFJNLbk/";
        // 获取重定向地址
        String redirectUrl = getRedirectUrl(url);
        // 截取文件id
        String fileId = redirectUrl.substring(redirectUrl.indexOf("video") + 6, redirectUrl.indexOf("region") - 2);
        getVideoResource(fileId);

    }

    /**
     * 获取重定向地址
     * @param path
     * @return
     * @throws Exception
     */
    private static String getRedirectUrl(String path) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) new URL(path)
                .openConnection();
        //设置为不对http链接进行重定向处理
        conn.setInstanceFollowRedirects(false);

        conn.setConnectTimeout(5000);

        //得到请求头的所有属性和值
        Map<String, List<String>> map = conn.getHeaderFields();
        Set<String> stringSet = map.keySet();
        for (String str: stringSet){
            System.out.println(str + "------" + conn.getHeaderField(str));
        }
        //返回重定向的链接（父类UrlConnection的方法）
        return conn.getHeaderField("Location");
    }

    /**
     * 获取视频资源 下载
     * @param fileId
     * @return
     * @throws Exception
     */
    private static void getVideoResource(String fileId) throws Exception {
        // https://www.iesdouyin.com/web/api/v2/aweme/iteminfo/?item_ids=6848166102460058888
        String requestUrl = "https://www.iesdouyin.com/web/api/v2/aweme/iteminfo/?item_ids=%s";

        String finalUrl = String.format(requestUrl, fileId);

        //先调用下忽略https证书的再请求才可以
        HttpsUrlValidator.retrieveResponseFromServer(finalUrl);

        //1.利用Jsoup抓取抖音链接
        String responseData = Jsoup.connect(finalUrl).ignoreContentType(true).execute().body();


        // "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=v0200ffc0000bfil6o4mavffbmroeo80&line=0",
        //具体匹配内容格式：「https://aweme.snssdk.com/aweme/...line=0」
        Pattern patternCompile = Pattern.compile("(https?.*?)(?=http|$|<|>|\\s|,)");
        //利用Pattern.compile("正则条件").matcher("匹配的字符串对象")方法可以将需要匹配的字段进行匹配封装 返回一个封装了匹配的字符串Matcher对象

        //3.匹配后封装成Matcher对象
        Matcher m = patternCompile.matcher(responseData);

        //4.①利用Matcher中的group方法获取匹配的特定字符串 ②利用String的replace方法替换特定字符,得到抖音的去水印链接
        String matchUrl ="";
        while(m.find()) {
            if (m.group(0).contains("https://aweme.snssdk.com/aweme/v1/playwm/")) {
                matchUrl = m.group(0).substring(0, m.group(0).length() - 2);
            }
        }

        //5.将链接封装成流
        //注:由于抖音对请求头有限制,只能设置一个伪装手机浏览器请求头才可实现去水印下载
        Map<String, String> headers = new HashMap<>();
        headers.put("Connection", "keep-alive");
        headers.put("Host", "aweme.snssdk.com");
        headers.put("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 12_1_4 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/16D57 Version/12.0 Safari/604.1");

        //6.利用Joup获取视频对象,并作封装成一个输入流对象
        BufferedInputStream in = Jsoup.connect(matchUrl).headers(headers).timeout(10000).ignoreContentType(true).execute().bodyStream();

        Long timeStamp = new Date().getTime();
        String fileAddress = "d:/抖音视频/douyin_"+timeStamp+".mp4";

        //7.封装一个保存文件的路径对象
        File fileSavePath = new File(fileAddress);

        //注:如果保存文件夹不存在,那么则创建该文件夹
        File fileParent = fileSavePath.getParentFile();
        if(!fileParent.exists()){
            fileParent.mkdirs();
        }

        //8.新建一个输出流对象
        OutputStream out =
                new BufferedOutputStream(
                        new FileOutputStream(fileSavePath));

        //9.遍历输出文件
        int b;
        while((b = in.read()) != -1) {
            out.write(b);
        }

        out.close();//关闭输出流
        in.close(); //关闭输入流

        //注:打印获取的链接
        System.out.println("-----抖音去水印链接-----\n" + matchUrl);
        System.out.println("\n-----视频保存路径-----\n" + fileSavePath.getAbsolutePath());
    }
}