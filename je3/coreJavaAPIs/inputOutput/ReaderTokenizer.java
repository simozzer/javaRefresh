package je3.coreJavaAPIs.inputOutput;

import je3.classes.Tokenizer;
import je3.classes.AbstractTokenizer;
import java.io.*;

public class ReaderTokenizer extends AbstractTokenizer{

    Reader in;

    public ReaderTokenizer(Reader in) { this(in,16*1024); }

    public ReaderTokenizer(Reader in, int buffsize) {
        this.in = in;
        maximumTokenLength(buffsize);
    }

    protected void createBuffer(int buffsize) {
        assert  text == null;
        this.text = new char[buffsize];
        this.numChars = 0;
    }

    protected boolean fillBuffer() throws IOException {
        assert text!=null && 0 <=tokenStart && tokenStart <= tokenEnd && tokenEnd <= p && p <= numChars && numChars <= text.length;

        if (tokenStart > 0) {
            System.arraycopy(text,tokenStart, text,0,numChars-tokenStart);
            tokenEnd -=tokenStart;
            p -= tokenStart;
            numChars -= tokenStart;
            tokenStart = 0;
        }

        int numread = in.read(text, numChars, text.length-numChars);
        if(numread == -1) return false;
        numChars += numread;
        return true;
    }

    public static class Test {
        public static void main(String[] args) throws IOException {
            Reader in = new FileReader(args[0]);
            PrintWriter out = new PrintWriter(new FileWriter(args[0] + ".copy"));
            ReaderTokenizer t = new ReaderTokenizer(in);
            t.tokenizeWords(true).tokenizeNumbers(true).tokenizeSpaces(true);
            while(t.next() != Tokenizer.EOF) {
                switch (t.tokenType()) {
                    case Tokenizer.EOF:
                        System.out.println("EOF");
                        break;
                    case Tokenizer.WORD:
                        System.out.println("WORD: " + t.tokenText());
                        break;
                    case Tokenizer.NUMBER:
                        System.out.println("NUMBER: " + t.tokenText());
                        break;
                    case Tokenizer.SPACE:
                        System.out.println("SPACE");
                        break;
                    default:
                        System.out.println((char)t.tokenType());
                }
                out.print(t.tokenText());
            }
            out.close();
        }
    }
}
