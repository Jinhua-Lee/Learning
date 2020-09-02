package com.myio;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * 统计长的单词
 * @author Jinhua
 */
public class CountLongWords {
	public static void main(String[] args) throws IOException {
		String dir = "E:/IOTest/";
		String filename = "chars.txt";
		String contents = new String(Files.readAllBytes(Paths.get(dir + filename)), StandardCharsets.UTF_8);
		List<String> words = Arrays.asList(contents.split("\\PL+"));

		long count = 0;
		for (String s : words) {
			if(s.length() > 4) {
				count++;
			}
		}
		System.out.println(count);

		count = words.stream().filter(s -> s.length() > 4).count();
		System.out.println(count);

		count = words.parallelStream().filter(s -> s.length() > 4).count();
		System.out.println(count);
	}
}
