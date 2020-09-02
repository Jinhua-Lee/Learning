package com.myio;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyFileOperation {

	@Test
	public void imagetest(){
		/*
			Image， ImageIO， BufferedImage， Icon， ImageIcon
		 */
		// 得到图片缓冲区
		BufferedImage bi = new BufferedImage(700, 300,BufferedImage.TYPE_INT_RGB);
		// 得到绘制环境（画笔）
		Graphics2D g2 = (Graphics2D) bi.getGraphics();
		g2.setColor(Color.WHITE);
		// 填充整张图片(设置背景色)
		g2.fillRect(0, 0, 700, 300);
		g2.setFont(new Font("微软雅黑", Font.BOLD, 25));
		g2.setColor(Color.BLACK);
		// 图片写入字符串
		g2.drawString("Hello World", 60, 80);
		try {
			ImageIO.write(bi, "JPEG", new FileOutputStream("E:\\IOTest\\new.jpeg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 文件复制
	 * @param src 源文件路径
	 * @param des 目标文件路径
	 * @throws IOException 输入输出异常
	 */
	public void myFileCopy(File src, File des) throws IOException {
		FileInputStream fis = new FileInputStream(src);
		FileOutputStream fos = new FileOutputStream(des, false);
		byte[] buff = new byte[1024];
		while(fis.read(buff) != -1) {
			fos.write(buff);
			fos.flush();
		}
		fis.close();
		fos.close();
	}

	/**
	 * 递归删除文件夹下的所有目录和文件
	 * @param file 待删除的文件夹
	 */
	public void recursiveDelete(File file) {
		if(file.exists()) {
			File[] files = file.listFiles();
			for (File f : files) {
				if(f.isFile()) {
					System.out.println("正在删除文件 " + f.getName() + "----结果： " + f.delete());
				}
				else {
					recursiveDelete(f);
				}
			}
			System.out.println("正在删除文件夹 " + file.getName() + "----结果： " + file.delete());
		}
		else {
			System.out.println("删除完成");
		}
	}

	/**
	 * 创建文件
	 * @param dir 路径
	 * @param fn 文件名
	 * @return 是否成功
	 */
	public boolean myFileCreate(String dir, String fn) {
		boolean b = false;
		File f = new File(dir, fn);
		if (f.exists()) {
			b = false;
			System.out.println("文件已存在！");
		}
		else {
			try {
				b = f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return b;
	}

	/**
	 * 写入指定内容
	 * @param dir 路径
	 * @param fn 文件名
	 * @param str 内容
	 * @param b 是否追加
	 */
	public void myFileWriter(String dir, String fn, String str, boolean b){
		OutputStream out = null;
		File f1 = new File(dir);
		File f2 = new File(dir, fn);
		byte[] buff;
		try {
			if (!f1.exists()) {
				f1.mkdirs();
			}
			if(!f2.exists()) {
				f2.createNewFile();
			}
			buff = str.getBytes("UTF-8");
			out = new FileOutputStream(f2, b);
			out.write(buff);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 读取文件内容: Scanner
	 * @param dir 路径
	 * @param fn 文件名
	 * @return 读取结果
	 */
	public boolean myFileScannerReader(String dir, String fn) {
		boolean b = false;
		List<String> list = new ArrayList<>();
		try {
			Scanner sc = new Scanner(new File(dir, fn), "UTF-8");

			while (sc.hasNextLine()) {
				list.add(sc.nextLine());
			}
			sc.close();

			for (String string : list) {
				System.out.println(string);
			}
			b = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * 读取文件内容： BufferedReader
	 * @param dir 路径
	 * @param fn 文件名
	 * @return 读取完成
	 */
	public boolean myFileBufferedReader(String dir, String fn) {
		boolean b = false;
		File f = new File(dir, fn);
		List<String> lines = new ArrayList<>();
		if(f.isFile()) {
			try {
				InputStream in = new FileInputStream(f);
				InputStreamReader is = new InputStreamReader(in, StandardCharsets.UTF_8);
				BufferedReader br = new BufferedReader(is);
				String temp;
				while((temp = br.readLine()) != null) {
					System.out.println(temp);
				}
				b = true;
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		for (String s : lines) {
			System.out.println(s);
		}
		return b;
	}

	/**
	 * 是否包含后缀文件
	 * @param dir 路径
	 * @param endwiths 后缀
	 * @return 是否包含后缀
	 */
	public boolean myContainsFile(String dir, String endwiths) {
		boolean b = true;
		File f = new File(dir);
		if(!f.exists()) {
			b = false;
		}
		String[] files = null;
		if(f.isDirectory()) {
			files = f.list();
			assert files != null;
			for (String s : files) {
				if(s.endsWith(endwiths)) {
					System.out.println(s);
				}
			}
		}
		else {
			b = false;
		}
		return b;
	}

	/**
	 * 获得文件列表
	 * @param dir 文件路径
	 * @return 读取结果
	 */
	public boolean myFileList(String dir) {
		boolean b = false;
		File f = new File(dir);
		if (f.isDirectory()) {
			String[] files = f.list();
			if(files != null) {
				b = true;
			}
			for (String s : files) {
				System.out.println(s);
			}
		}
		return b;
	}
}
