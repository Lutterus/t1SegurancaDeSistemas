package t1SegurancaDeSistemas;

public class Alphabet {
	// index da letra mais comum do idioma
	private int mostComumLetter = 4;

	public Alphabet() {
		// TODO Auto-generated constructor stub
	}

	public char calculateDistance(int i, int j) {
		// i = letra atual a ser mudada
		// j = letra que mais apareceu naquela substring

		int deslocation = calculateMoveToBeMade(j);
		char toBeReturned = doTheMove(deslocation, i);

		return toBeReturned;
	}

	private char doTheMove(int deslocation, int i) {
		int aux = i - deslocation;
		if (aux < 0) {
			aux = aux * (-1);
			aux = 26 - aux;
		}
		return getLetterOfIndex(aux);
	}

	private int calculateMoveToBeMade(int j) {
		int toBeReturned = j - mostComumLetter;
		if (toBeReturned < 0) {
			toBeReturned = toBeReturned * (-1);
		}
		return toBeReturned;

	}

	public int getIndexOfLetter(char chars) {
		switch (chars) {
		case 'a':
			return 0;
		case 'b':
			return 1;
		case 'c':
			return 2;
		case 'd':
			return 3;
		case 'e':
			return 4;
		case 'f':
			return 5;
		case 'g':
			return 6;
		case 'h':
			return 7;
		case 'i':
			return 8;
		case 'j':
			return 9;
		case 'k':
			return 10;
		case 'l':
			return 11;
		case 'm':
			return 12;
		case 'n':
			return 13;
		case 'o':
			return 14;
		case 'p':
			return 15;
		case 'q':
			return 16;
		case 'r':
			return 17;
		case 's':
			return 18;
		case 't':
			return 19;
		case 'u':
			return 20;
		case 'v':
			return 21;
		case 'w':
			return 22;
		case 'x':
			return 23;
		case 'y':
			return 24;
		case 'z':
			return 25;
		default:
			return -1;
		}
	}

	public char getLetterOfIndex(int i) {
		switch (i) {
		case 0:
			return 'a';
		case 1:
			return 'b';
		case 2:
			return 'c';
		case 3:
			return 'd';
		case 4:
			return 'e';
		case 5:
			return 'f';
		case 6:
			return 'g';
		case 7:
			return 'h';
		case 8:
			return 'i';
		case 9:
			return 'j';
		case 10:
			return 'k';
		case 11:
			return 'l';
		case 12:
			return 'm';
		case 13:
			return 'n';
		case 14:
			return 'o';
		case 15:
			return 'p';
		case 16:
			return 'q';
		case 17:
			return 'r';
		case 18:
			return 's';
		case 19:
			return 't';
		case 20:
			return 'u';
		case 21:
			return 'v';
		case 22:
			return 'w';
		case 23:
			return 'x';
		case 24:
			return 'y';
		case 25:
			return 'z';
		default:
			return '?';
		}
	}

}
