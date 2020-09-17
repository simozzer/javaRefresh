package je3.classes;

import java.io.IOException;

public class CharSequenceTokenizer extends AbstractTokenizer {
    char [ ] buffer;

    public CharSequenceTokenizer(CharSequence sequence) {
        buffer = sequence.toString().toCharArray();
    }

    protected void createBuffer(int bufferSize) {
        assert text == null;
        text = buffer;
        numChars = buffer.length;
    }

    protected boolean fillBuffer() { return false;}

    public static class Test {
        public static void main(String[] args) {
            StringBuilder text = new StringBuilder();
            for (String arg : args) {
                text.append(arg).append(" ");
            }
            try {
                CharSequenceTokenizer t = new CharSequenceTokenizer((text.toString()));
                t.tokenizeWords(true).quotes("'&", "';").skipSpaces(true);
                while (t.next() != Tokenizer.EOF)
                    System.out.println(t.tokenText());
            }
            catch (IOException e) {
                System.out.println("io exception");
            }
        }
    }


}
