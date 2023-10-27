package aed.recursion;

import es.upm.aedlib.Position;
import es.upm.aedlib.positionlist.PositionList;
import es.upm.aedlib.positionlist.NodePositionList;

public class Crypto {

	public static PositionList<Integer> encrypt(PositionList<Character> key, PositionList<Character> text) {
		PositionList<Integer> cifrado = new NodePositionList<Integer>();
		return encryptRecursivo(key, text, cifrado, key.first(), text.first());
	}

	private static PositionList<Integer> encryptRecursivo(PositionList<Character> key, PositionList<Character> text,
			PositionList<Integer> cifrado, Position<Character> keyCursor, Position<Character> textCursor) {
		Integer calculoCifrado = 0;
		calculoCifrado = getPos(key, keyCursor, textCursor.element(), calculoCifrado);
		cifrado.addLast(calculoCifrado);
		keyCursor = getCharacter(key, keyCursor, calculoCifrado);
		textCursor = text.next(textCursor);
		if (textCursor != null)
			encryptRecursivo(key, text, cifrado, keyCursor, textCursor);
		return cifrado;
	}

	private static Integer getPos(PositionList<Character> key, Position<Character> keyCursor, Character element,
			Integer calculoCifrado) {
		Integer result = 0;
		Character letra = keyCursor.element();
		if (element.equals(letra) && keyCursor != null) {
			result = calculoCifrado;
		}
		else if (element < letra && key.prev(keyCursor) != null) {
			calculoCifrado--;
			result = getPos(key, key.prev(keyCursor), element, calculoCifrado);
		}
		else if (element > letra && key.next(keyCursor) != null) {
			calculoCifrado++;
			result = getPos(key, key.next(keyCursor), element, calculoCifrado);
		}
		return result;
	}

	private static Position<Character> getCharacter(PositionList<Character> key, Position<Character> keyCursor,
			Integer calculoCifrado) {
		if (calculoCifrado == 0)
			return keyCursor;
		else if (calculoCifrado > 0 && key.next(keyCursor) != null) {
			calculoCifrado--;
			return getCharacter(key, key.next(keyCursor), calculoCifrado);
		}
		else if (calculoCifrado < 0 && key.prev(keyCursor) != null) {
			calculoCifrado++;
			return getCharacter(key, key.prev(keyCursor), calculoCifrado);
		}
		return keyCursor;
	}
	
	public static PositionList<Character> decrypt(PositionList<Character> key, PositionList<Integer> encodedText) {
		PositionList<Character> result = new NodePositionList<Character>();
		return decryptRecursivo(key, encodedText, result, key.first(), encodedText.first());
	}

	private static PositionList<Character> decryptRecursivo(PositionList<Character> key,
			PositionList<Integer> encodedText, PositionList<Character> deciphered, Position<Character> keyCursor,
			Position<Integer> textCursor) {
		keyCursor = getCharacter(key, keyCursor, textCursor.element());
		textCursor = encodedText.next(textCursor);
		deciphered.addLast(keyCursor.element());
		if (textCursor != null)
			decryptRecursivo(key, encodedText, deciphered, keyCursor, textCursor);
		return deciphered;
	}

}