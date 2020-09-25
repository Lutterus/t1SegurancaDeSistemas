package t1SegurancaDeSistemas;

public class Main {
	public static void main(String[] args) {
		// o nome do arquivo a ser lido (deve estar abaixo de SRC, dentro de assets
		String nome = "ingles.txt";

		// instancia classe do leitor de arquivos
		TextReader textreader = new TextReader();

		// tenta ler o arquivo, retornando o tamanho do texto
		int size = textreader.tryToRead(nome);
		System.out.println("o texto tem " + size + " caracteres");

		// le o arquivo de texto, retornando um vetor de caracteres
		char[] chars = new char[size];
		chars = textreader.readText(nome);

		// para printar o texto inteiro
		// String fileContent = new String(chars);
		// System.out.println(fileContent);

		System.out.println("//////////////////////////////////////////");

		// classe que lida com movimentaco no alfabeto
		Alphabet alphabet = new Alphabet();

		// indice de coincidencia e suas operacoes
		IndeOfCoincidence indexOfCoincidence = new IndeOfCoincidence(0.0667, alphabet);

		// verificado se a chave foi encontrada
		boolean hasKeyBeenFound = false;

		// variavel que guarda o tamanho da chave
		int keySize = 0;

		// variavel que avisa se deve parar
		boolean stop = false;

		// variavel com a diferenca aceitavel parar ser considerado como correto a chave
		// sendo testada
		double shouldStopAt = 0.0009;

		// calcula o index de coincidencia do texto
		while (stop == false) {
			keySize++;

			stop = indexOfCoincidence.isResultGoodEnough(
					indexOfCoincidence.calculateIndeOfCoincidence(chars, size, keySize),
					indexOfCoincidence.getIndexOfCoincidencePortuguese(), shouldStopAt);
		}

		System.out.println("melhor chave: " + keySize);

		// separa em substrings
		String[] vectorOfSubStrings = new String[keySize];
		for (int i = 0; i < vectorOfSubStrings.length; i++) {
			vectorOfSubStrings[i] = "";
		}
		vectorOfSubStrings = indexOfCoincidence.distributeTextAmongLists(vectorOfSubStrings, chars, keySize);

		// calcula qual letra mais aparece para cada subString
		// e retorna a distancia para ela
		int[] moviments = new int[keySize];
		moviments = indexOfCoincidence.calculateDistance(vectorOfSubStrings);

		// calcula distancia para a letra que mais aparece para cada substring, converte
		// e retorna a string final
		String finalString = indexOfCoincidence.convert(chars, moviments);

		// printa a saida com o resultado
		System.out.println(finalString);
	}
}
