import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String SPACE_PATTERN = "\\s+";
    public static final String DELIMITER = "\n";
    public static final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String sentence) {
        try {
            List<WordInfo> wordInfoList = getWordInfoList(sentence);

            wordInfoList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());

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
                .collect(Collectors.toList());
    }

    private String joinWordInfoListIntoResultString(List<WordInfo> wordInfoList) {
        StringJoiner joiner = new StringJoiner(DELIMITER);
        for (WordInfo word : wordInfoList) {
            String wordWithCount = String.format("%s %d", word.getWord(), word.getWordCount());
            joiner.add(wordWithCount);
        }
        return joiner.toString();
    }



}
