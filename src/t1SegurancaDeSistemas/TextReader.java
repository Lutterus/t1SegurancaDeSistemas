package t1SegurancaDeSistemas;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TextReader {
	public TextReader() {
		// TODO Auto-generated constructor stub
	}

	public int tryToRead(String string) {
		File file = new File("src/assets/" + string);
		return (int) file.length();
	}

	public char[] readText(String string) {
		// metodo de leitura encontrado no site
		// https://www.techiedelight.com/read-text-file-using-filereader-java/
		File file = new File("src/assets/" + string);
		try (FileReader fr = new FileReader(file)) {
			char[] chars = new char[(int) file.length()];
			fr.read(chars);
			return chars;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
