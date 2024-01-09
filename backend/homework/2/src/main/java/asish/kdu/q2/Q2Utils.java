package asish.kdu.q2;

public class Q2Utils {
    private Q2Utils() {

    }

    /**
     * We define Opinion enum to re-use the same function for different scenarios
     */
    public enum Opinion {
        POSITIVE,
        NEGATIVE
    }
    public static int wasPhraseOpinionLookup(String sentence, String[] words, String feature, Opinion opinion) {
        String pattern = feature + " was ";

        for(String word: words) {
            if(sentence.contains(pattern.concat(word)))
                return opinion == Opinion.POSITIVE ? 1 : -1;
        }

        return 0;
    }

    public static int firstPatternOpinionLookup(String sentence, String[] words, String feature, Opinion opinion) {
        for(String word: words) {
            if(sentence.contains(word + " " + feature))
                return opinion == Opinion.NEGATIVE ? -1 : 1;
        }

        return 0;
    }
}
