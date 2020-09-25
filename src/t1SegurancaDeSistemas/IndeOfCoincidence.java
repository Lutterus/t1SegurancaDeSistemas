package t1SegurancaDeSistemas;

public class IndeOfCoincidence {
	private double indexOfCoincidenceForPortuguese;
	private Alphabet alphabet;

	public IndeOfCoincidence(double indexOfCoincidence, Alphabet alphabet) {
		this.setIndexOfCoincidencePortuguese(indexOfCoincidence);
		this.alphabet = alphabet;
	}

	public double getIndexOfCoincidencePortuguese() {
		return indexOfCoincidenceForPortuguese;
	}

	public void setIndexOfCoincidencePortuguese(double indexOfCoincidence) {
		this.indexOfCoincidenceForPortuguese = indexOfCoincidence;
	}

	public double[] calculateIndeOfCoincidence(char[] chars, double txtLength, int keySize) {
		double vectorOfresults[] = new double[keySize];
		if (keySize == 1) {
			return simpleCalculation(vectorOfresults, 0, chars, txtLength);
		} else {
			return complexCalculation(vectorOfresults, chars, txtLength, keySize);
		}

	}

	private double[] complexCalculation(double[] vectorOfresults, char[] chars, double txtLength, int keySize) {
		// cria vetor para distribuir os caracteres do texto entre N substrings, sendo N
		// o tamanho da chave
		String vectorOfSubStrings[] = new String[keySize];

		// limpando o vetor (em java concaternar um vazio adiciona 'null' no inicio)
		for (int i = 0; i < vectorOfSubStrings.length; i++) {
			vectorOfSubStrings[i] = "";
		}

		// distribui o texto
		vectorOfSubStrings = distributeTextAmongLists(vectorOfSubStrings, chars, keySize);

		// para cada substring
		for (int i = 0; i < vectorOfSubStrings.length; i++) {
			// transforma a substring em um vetor de char
			char[] newChars = vectorOfSubStrings[i].toCharArray();

			// usa o metodo de calculo simples para calcular o IC daquele array de char
			vectorOfresults = simpleCalculation(vectorOfresults, i, newChars, vectorOfSubStrings[i].length());

		}

		return vectorOfresults;

	}

	public String[] distributeTextAmongLists(String[] vectorOfSubStrings, char[] chars, int keySize) {
		int maxIndex = keySize - 1;
		// variavel que controla para qual subS;tring ira o caracter
		int currentIndex = 0;

		// percorrendo todo o texto
		for (int i = 0; i < chars.length; i++) {
			// concatena substring com o char da posicao I da lista completa de chars
			vectorOfSubStrings[currentIndex] = vectorOfSubStrings[currentIndex] + chars[i];

			// comparando com a quantidade de substrings (mesmo que tamanho da chave)
			if (currentIndex == maxIndex) {
				// volta a forncecer o char para a primeira substring quando todas foram
				// igualmente alimentadas
				currentIndex = 0;
			} else {
				// se nao, incrementa para alimentar a proxa
				currentIndex++;
			}

		}

		return vectorOfSubStrings;

	}

	private double[] simpleCalculation(double[] vectorOfresults, int indexOfResult, char[] chars, double txtLength) {
		int vectorToCount[] = new int[26];
		for (int i = 0; i < chars.length; i++) {
			int index = alphabet.getIndexOfLetter(chars[i]);
			vectorToCount[index]++;
		}

		double toBeDevidedBy = txtLength * (txtLength - 1);
		double probabilityOfOccurrence[] = new double[26];
		for (int i = 0; i < vectorToCount.length; i++) {
			probabilityOfOccurrence[i] = vectorToCount[i] * (vectorToCount[i] - 1);
			probabilityOfOccurrence[i] = probabilityOfOccurrence[i] / toBeDevidedBy;
		}

		double IC = 0;
		for (int i = 0; i < probabilityOfOccurrence.length; i++) {
			IC = IC + probabilityOfOccurrence[i];
		}

		vectorOfresults[indexOfResult] = IC;
		return vectorOfresults;

	}

	public boolean isResultGoodEnough(double[] vectorOfResults, double language, double shouldStopAt) {
		double aux = 0;
		double toBeReturned = 100;
		for (int i = 0; i < vectorOfResults.length; i++) {
			aux = language - vectorOfResults[i];
			if (aux < 0) {
				aux = aux * (-1);
			}
			if (aux < toBeReturned) {
				toBeReturned = aux;
			}
		}

		if (toBeReturned <= shouldStopAt) {
			return true;
		}
		return false;
	}

	public int[] calculateDistance(String[] vectorOfSubStrings) {
		// vetor para guardar os resultados
		int[] results = new int[vectorOfSubStrings.length];

		for (int i = 0; i < vectorOfSubStrings.length; i++) {
			// vetor para representar o alfabeto
			int[] vector = new int[26];

			// para cada substring, transforma em um array de char
			char[] subString = vectorOfSubStrings[i].toCharArray();
			// para cada letra da substring
			for (int j = 0; j < subString.length; j++) {
				// soma cada letra
				vector[alphabet.getIndexOfLetter(subString[j])]++;
			}
			// com a lista de letras da subsstring contadas no vetor "vector"
			// verifica qual a letra que mais aparece

			// variavel para marcar a posicao da letra que mais aparece
			int index = 0;
			// variavel para marcar a quantidade de vezes que ela parece
			int result = 0;
			// procura a letra que mais aparece
			for (int j = 0; j < vector.length; j++) {
				if (vector[j] > result) {
					index = j;
					result = vector[j];
				}
			}
			// atribui o indice da letra que mais aparece para o vetor "results"
			results[i] = index;

		}
		// ao fim da iteracao, retorna o vetor de inteiro "results", com o index das
		// letras que mais aparecem
		return results;
	}

	public String convert(char[] chars, int[] moviments) {
		// variavel com o texto final
		String finalString = "";

		int index = 0;

		// continua enquanto nao percorrer todo o vetor de chars
		while ((index + moviments.length) < chars.length) {
			for (int i = 0; i < moviments.length; i++) {
				// altera a letra
				char letter = alphabet.calculateDistance(alphabet.getIndexOfLetter(chars[index]), moviments[i]);

				// e adiciona ela na string final
				finalString = finalString + letter;

				index++;
			}
		}
		return finalString;
	}
}
