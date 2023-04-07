package test;

import java.io.File;

import utilities.MyFile;

public class TestMyFile {
	public static void main(String[] args) {
		File file = new File("/home/doananhkiet/Downloads/kiet.txt");
		MyFile.saveToFile(file, "doan anh kiet");
	}
}
