package com.example.loadingpagetest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.util.Log;

public class StreamTool {
        /**
         * ´ÓÊäÈëÁ÷ÀïÃæµÃµ½·µ»ØÎª¶þ½øÖÆµÄÊý¾Ý * @param inStream ÊäÈëÁ÷
         * 
         * @return byte[] ¶þ½øÖÆÊý¾Ý
         * @throws Exception
         */
        public static byte[] read(InputStream inStream) throws IOException {
                // ¹¹ÔìÒ»¸öByteArrayOutputStream
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                // ÉèÖÃÒ»¸ö»º³åÇø
                byte[] buffer = new byte[1024];
                int len = 0;
                // ÅÐ¶ÏÊäÈëÁ÷³¤¶ÈÊÇ·ñµÈÓÚ-1£¬¼´·Ç¿Õ
                while ((len = inStream.read(buffer)) != -1) {
                        // °Ñ»º³åÇøµÄÄÚÈÝÐ´Èëµ½Êä³öÁ÷ÖÐ£¬´Ó0¿ªÊ¼¶ÁÈ¡£¬³¤¶ÈÎªlen
                        outStream.write(buffer, 0, len);
                }
                // ¹Ø±ÕÊäÈëÁ÷
                inStream.close();
                return outStream.toByteArray();
        }
        
        
        public static byte[] getData(String url){
//                Log.d("StreamTool.getDate","from url "+url);
                 try {
                        URL u = new URL(url);
                        HttpURLConnection conn=(HttpURLConnection)u.openConnection();
                        conn.setConnectTimeout(10);
                        conn.setRequestMethod("GET");
                        InputStream in=conn.getInputStream();
                        
                        byte[] bts=read(in);
                        return bts;
                } catch (MalformedURLException e) {
                        e.printStackTrace();
                } catch (IOException e) {
                        e.printStackTrace();
                } catch (Exception e) {
                        e.printStackTrace();
                }
                 return null;
                 

        }
}