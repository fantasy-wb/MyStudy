package fantasy.Annotation;

import java.io.*;

public class IOTest {
 
	public static void main(String[] args) throws IOException {
		// 流式读取文件
		DataInputStream dis = null;
		try {
			dis = new DataInputStream(new BufferedInputStream(new FileInputStream("test.txt")));
			// 读取文件内容
			byte[] bs = new byte[dis.available()];
			dis.read(bs);
			String content = new String(bs);
			System.out.println(content);
		} finally {
			dis.close();
		}
	}
}