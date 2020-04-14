import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String SPACE_PATTERN = "\\s+";
    public static final String DELIMITER = "\n";
    public static final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String sentence) {
        try {
            List<WordInfo> wordInfoList = getWordInfoList(sentence);

            return joinWordInfoListIntoResultString(wordInfoList);

        } catch (Exception exception) {
            return CALCULATE_ERROR;
        }
    }

    private List<WordInfo> getWordInfoList(String sentence) {
        List<String> wordList = Arrays.asList(sentence.split(SPACE_PATTERN));
        return wordList.stream()
                .distinct()
                .map(word -> new WordInfo(word, Collections.frequency(wordList, word)))
                .sorted(Comparator.comparing(wordInfo -> wordInfo.getWordCount(), Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    private String joinWordInfoListIntoResultString(List<WordInfo> wordInfoList) {
        return  wordInfoList.stream()
                .map(word -> String.format("%s %d", word.getWord(), word.getWordCount()))
                .collect(Collectors.joining(DELIMITER));
    }
}
