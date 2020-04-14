import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {

    public static final String SPACE_PATTERN = "\\s+";
    public static final String DELIMITER = "\n";

    public String getResult(String sentence) {
        try {
            List<WordInfo> wordInfoList = splitSentenceToWordList(sentence);

            wordInfoList = calculateWordCount(wordInfoList);

            wordInfoList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());

            return joinWordInfoListIntoResultString(wordInfoList);

        } catch (Exception exception) {
            return "Calculate Error";
        }
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
        for (WordInfo w : wordInfoList) {
            String wordWithCount = w.getWord() + " " + w.getWordCount();
            joiner.add(wordWithCount);
        }
        return joiner.toString();
    }

    private List<WordInfo> splitSentenceToWordList(String sentence) {
        String[] words = sentence.split(SPACE_PATTERN);

        List<WordInfo> wordInfoList = new ArrayList<>();
        for (String word : words) {
            WordInfo wordInfo = new WordInfo(word, 1);
            wordInfoList.add(wordInfo);
        }
        return wordInfoList;
    }

}
