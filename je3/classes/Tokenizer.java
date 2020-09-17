package je3.classes;

import java.io.IOException;

public interface Tokenizer {
    public static final int EOF=-1;
    public static final int SPACE=-2;
    public static final int NUMBER=-3;
    public static final int WORD=-4;
    public static final int KEYWORD=-5;
    public static final int TEXT=-6;
    public static final int BOF=-7;
    public static final int OVERFLOW=-8;

    public Tokenizer skipSpaces(boolean skip);
    public Tokenizer tokenizeSpaces(boolean tokenize);
    public Tokenizer tokenizeNumbers(boolean tokenize);
    public Tokenizer tokenizeWords(boolean tokenize);
    public Tokenizer wordRecognizer(WordRecognizer wordRecognizer);
    public Tokenizer keywords(String[] keywords);
    public Tokenizer trackPosition(boolean track);
    public Tokenizer quotes(String openquotes, String closequotes);
    public Tokenizer maximumTokenLength(int size);

    public static interface WordRecognizer {
        public boolean isWordStart(char c);
        public boolean isWordPart(char c, char firstChar);
        public int tokenType();
        public String tokenText();
        public int tokenKeyword();
        public int tokenLine();
        public int tokenColumn();
        public int next() throws IOException;
        public int nextChar() throws IOException;
        public int scan(char delimiter, boolean excludeCurrentToken, boolean includeDelimiter, boolean skipDelimiter) throws IOException;
        public int scan(char delimiter, boolean matchAll, boolean extendCurrentToken, boolean includeDelimiter, boolean skipDelimiter) throws IOException;



    }
}