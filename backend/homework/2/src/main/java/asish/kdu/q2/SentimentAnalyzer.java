package asish.kdu.q2;

import asish.kdu.logging.CustomLogger;

import java.util.Arrays;
public class SentimentAnalyzer {

    /**
     * This is the function where all other utility functions are called. We iterate over the featureSet
     * find the Opinion value and break out once we have a non-zero opinion.
     */
    public static int[] detectProsAndCons(String review, String[][] featureSet, String[] posOpinionWords, String[] negOpinionWords) {
        int[] featureOpinions = new int[featureSet.length];

        for (int i = 0; i < featureSet.length; i++) {
            int opinionValue = 0;
            for (int j = 0; j < featureSet[i].length; j++) {
                opinionValue = getOpinionOnFeature(review, featureSet[i][j], posOpinionWords, negOpinionWords);

                if (opinionValue != 0)
                    break;
            }

            featureOpinions[i] = opinionValue;
        }
        return featureOpinions;
    }

    private static int getOpinionOnFeature(String review, String feature, String[] posOpinionWords, String[] negOpinionWords) {
        int opinionValue = checkForWasPhrasePattern(review, feature, posOpinionWords, negOpinionWords);

        if (opinionValue != 0) return opinionValue;

        opinionValue = checkForOpinionFirstPattern(review, feature, posOpinionWords, negOpinionWords);
        return opinionValue;
    }

    /**
     * @Q2Utils are defined to reduce redundant code to iterate and compare Negative and Positive comments
     */
    private static int checkForWasPhrasePattern(String review, String feature, String[] posOpinionWords, String[] negOpinionWords) {
        int opinion = Q2Utils.wasPhraseOpinionLookup(review, posOpinionWords, feature, Q2Utils.Opinion.POSITIVE);

        if (opinion == 0)
            return Q2Utils.wasPhraseOpinionLookup(review, negOpinionWords, feature, Q2Utils.Opinion.NEGATIVE);

        return opinion;
    }

    private static int checkForOpinionFirstPattern(String review, String feature, String[] posOpinionWords, String[] negOpinionWords) {
        String[] sentences = review.split("\\.");

        for (String sentence : sentences) {
            int opinion = Q2Utils.firstPatternOpinionLookup(sentence, posOpinionWords, feature, Q2Utils.Opinion.POSITIVE);
            if (opinion == 0)
                opinion = Q2Utils.firstPatternOpinionLookup(sentence, negOpinionWords, feature, Q2Utils.Opinion.NEGATIVE);

            if (opinion != 0) return opinion;
        }

        return 0;
    }

    public static void main(String[] args) {
        String review = "Haven't been here in years! Fantastic service and the food was delicious! Definetly will be a frequent flyer! Francisco was very attentive";
        String[][] featureSet = {
                {"ambiance", "ambience", "atmosphere", "decor"},
                {"dessert", "ice cream", "desert"},
                {"food"},
                {"soup"},
                {"service", "management", "waiter", "waitress", "bartender", "staff", "server"}
        };
        String[] posOpinionWords = {"good", "fantastic", "friendly", "great", "excellent", "amazing", "awesome", "delicious"};
        String[] negOpinionWords = {"slow", "bad", "horrible", "awful", "unprofessional", "poor"};

        int[] featureOpinions = detectProsAndCons(review.toLowerCase(), featureSet, posOpinionWords, negOpinionWords);

        CustomLogger.printLogger("Opinions on features - ");
        CustomLogger.printLogger(Arrays.toString(featureOpinions));
    }
}