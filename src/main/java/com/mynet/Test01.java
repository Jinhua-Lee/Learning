package com.mynet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 模拟Tomcat服务器
 *
 * @author Jinhua
 */
public class Test01 {

    public static void main(String[] args) throws IOException {
        while (true) {
            ServerSocket ss = new ServerSocket(8081);
            Socket s = ss.accept();
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String string = "";
            while ((string = br.readLine()) != null && string.length() > 0) {
                sb.append(string).append(System.lineSeparator());
            }
            System.out.println(sb);
            PrintWriter pw = new PrintWriter(s.getOutputStream());
            pw.println("HTTP/1.1 200 OK");
            pw.println("Content-Type:text/html; charset=UTF-8;");
            pw.println();
            pw.println("<title>子期</title>");
            pw.println("<h1 style=\"color:green; text-align:center; font-family:STLiti\">Nice Meeting You ~!</h1>");
            pw.flush();

            br.close();
            s.close();
            ss.close();
        }
    }
}
