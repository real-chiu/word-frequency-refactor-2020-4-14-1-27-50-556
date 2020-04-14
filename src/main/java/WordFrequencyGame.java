import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String SPACE_PATTERN = "\\s+";
    public static final String DELIMITER = "\n";
    public static final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String sentence) {
        try {
            List<WordInfo> wordInfoList = getWordInfoList(sentence);

            wordInfoList = calculateWordCount(wordInfoList);

            wordInfoList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());

            return joinWordInfoListIntoResultString(wordInfoList);

        } catch (Exception exception) {
            return CALCULATE_ERROR;
        }
    }

    private List<WordInfo> getWordInfoList(String sentence) {
        List<String> wordList = Arrays.asList(sentence.split(SPACE_PATTERN));
        return wordList.stream()
                .map(word -> new WordInfo(word, 1))
                .collect(Collectors.toList());
    }

    private List<WordInfo> calculateWordCount(List<WordInfo> wordInfoList) {
        Map<String, List<WordInfo>> wordInfoMap = new HashMap<>();
        for (WordInfo wordInfo : wordInfoList) {
            if (!wordInfoMap.containsKey(wordInfo.getWord())) {
                ArrayList<WordInfo> wordInfoArray = new ArrayList<>();
                wordInfoArray.add(wordInfo);
                wordInfoMap.put(wordInfo.getWord(), wordInfoArray);
            } else {
                wordInfoMap.get(wordInfo.getWord()).add(wordInfo);
            }
        }

        List<WordInfo> list = new ArrayList<>();
        for (Map.Entry<String, List<WordInfo>> entry : wordInfoMap.entrySet()) {
            WordInfo wordInfo = new WordInfo(entry.getKey(), entry.getValue().size());
            list.add(wordInfo);
        }

        return list;
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
