package com.mynet;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


/**
 * 模拟网络
 * @author Jinhua
 */
public class SocketDemo {
	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(8081);
			Socket s = ss.accept();
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();
			Scanner sc = new Scanner(is, "UTF-8");
			PrintWriter pw = new PrintWriter(new OutputStreamWriter
					(os, "UTF-8"), true);
			pw.print("Hello~ Enter bye to EXIT");

			boolean done = false;
			while (!done && sc.hasNextLine()) {
				String line = sc.nextLine();
				pw.print("输出：" + line);
				if (line.trim().equalsIgnoreCase("bye"))
					done = true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
