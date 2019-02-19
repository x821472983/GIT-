package com.base.pojo;



import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class UploaderFileUtil {


    /**
     * @param fileName
     * @throws IOException
     * @Description 下载文件
     * @author Enzo
     * @date 2017年5月2日
     */
    public static void download(HttpServletResponse response, InputStream inputStream, String fileName) throws Exception {
        // 设置response参数，可以打开下载页面
        fileName = fileName.replace(" ", "%20");
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + new String(fileName.getBytes(), "iso-8859-1"));
        ServletOutputStream out = response.getOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(inputStream);
            bos = new BufferedOutputStream(out);
            ProgressBarThread pbt = new ProgressBarThread(inputStream.available());// 创建进度条
            new Thread(pbt).start();// 开启线程，刷新进度条
            byte[] buff = new byte[2048];
            int size = 0;
            while ((size = bis.read(buff)) > -1) {//// 循环读取
                bos.write(buff, 0, size);
                pbt.updateProgress(size);// 写完一次，更新进度条
            }
            pbt.finish();// 文件读取完成，关闭进度条
        } catch (final Exception e) {
            throw e;
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    /**
     * <h3>下载文件</h3>
     *
     * @param file
     * @param fileName
     * @throws IOException
     * @author Enzo
     * @date 2018年1月25日 上午9:05:10
     */
    public static void download(HttpServletResponse response, File file, String fileName) throws Exception {
        InputStream in = new FileInputStream(file);
        download(response, in, fileName);
    }

    /**
     * @return void
     * @title:<h3> 根据绝对路径下载文件下载文件<h3>
     * @author: Enzo
     * @date: 2018-8-28 10:27
     * @params [response, filePath, fileName]
     **/
    public static void download(HttpServletResponse response, String filePath, String fileName) throws Exception {
        InputStream in = new FileInputStream(filePath);
        download(response, in, fileName);
    }

    /**
     * @param res
     * @param filePath
     * @return
     * @Description 将String转成File
     * @author lhj
     * @date 2017年7月20日
     */
    public static File string2File(String res, String filePath) {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        File distFile = new File(filePath);
        try {
            if (!distFile.getParentFile().exists())
                distFile.getParentFile().mkdirs();
            bufferedReader = new BufferedReader(new StringReader(res));
            bufferedWriter = new BufferedWriter(new FileWriter(distFile));
            char buf[] = new char[1024]; // 字符缓冲区
            int len;
            while ((len = bufferedReader.read(buf)) != -1) {
                bufferedWriter.write(buf, 0, len);
            }
            bufferedWriter.flush();
            bufferedReader.close();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            return distFile;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return distFile;
    }

    // 图片转化成base64字符串

    /**
     * @return
     * @Description 图片转base64字符
     * @author Enzo
     * @date 2017年7月19日
     */



    /**
     * @param str
     * @param filePath
     * @Description 字符串写入到文本文件中
     * @author Enzo
     * @date 2017年7月19日
     */
    public static void writeStringToTxt(String str, String filePath) {
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            fos.write(str.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * @param filePath
     * @return
     * @Description 读取某个txt文件的内容
     * @author lhj
     * @date 2017年7月27日
     */
    public static String readTxtFile(String filePath) {
        String result = "";
        try {
            String encoding = "GBK";
            File file = new File(filePath);
            if (file.isFile() && file.exists()) { // 判断文件是否存在
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    result += lineTxt + "\n";
                }
                read.close();
            } else {
                result = "找不到指定的文件";
            }
        } catch (Exception e) {
            result = "读取文件内容出错";
            e.printStackTrace();
        }
        return result;

    }


}
