package idwall.desafio.string;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Rodrigo Catão Araujo on 06/02/2018.
 */
public class IdwallFormatter extends StringFormatter {

    private StringBuilder formattedText = new StringBuilder();

    /**
     * Should format as described in the challenge
     *
     * @param text
     * @return
     */
    @Override
    public String format(String text) {
        return format(text, limit, justify);
    }

    private void processLines(String lineToBeProcessed) {
        StringBuilder formattedLines = new StringBuilder();
        List<String> splittedWords = Arrays.asList(lineToBeProcessed.split(" "));
        StringBuilder provisionalPhrase = new StringBuilder();

        for (String word : splittedWords) {
            int newLength = (provisionalPhrase + word).length();
            if (isLastParagraphPhrase(splittedWords, word)) {
                getLastLineAndAddToFormattedText(provisionalPhrase, word);
            } else if (newLength == limit) {
                formattedText.append(provisionalPhrase).append(word).append(StringUtils.LF);
                provisionalPhrase = new StringBuilder();
            } else if (newLength > limit) {
                provisionalPhrase = addTextToFormattedLines(provisionalPhrase, word);
            } else if (newLength < 40) {
                provisionalPhrase.append(word).append(StringUtils.SPACE);
            }
        }
    }

    private StringBuilder addTextToFormattedLines(StringBuilder provisionalPhrase, String word) {
        formattedText.append(justifyTextIfNecessary(provisionalPhrase.toString().trim()).append(StringUtils.LF));
        provisionalPhrase = new StringBuilder(word).append(StringUtils.SPACE);
        return provisionalPhrase;
    }

    private void getLastLineAndAddToFormattedText(StringBuilder provisionalPhrase, String word) {
        formattedText.append(provisionalPhrase).append(word);
    }

    private boolean isLastParagraphPhrase(List<String> splittedWords, String word) {
        return splittedWords.get(splittedWords.size() - 1).equals(word);
    }

    public StringBuilder justifyTextIfNecessary(String lines) {
        StringBuilder line = new StringBuilder(lines);
        if (justify) {
            while (line.length() < limit) {
                int diferencaEntreTamanhoDaFraseEDoJustificado = limit - line.length();
                for (int i = line.indexOf(" "); i < line.length(); i++) {
                    if (Character.isWhitespace(line.charAt(i)) && !Character.isWhitespace(line.charAt(i + 1)) && diferencaEntreTamanhoDaFraseEDoJustificado > 0) {
                        line.insert(i, " ");
                        i++;
                        diferencaEntreTamanhoDaFraseEDoJustificado--;
                    }
                }
            }
        }
        return line;
    }

    @Override
    public String format(String text, Integer limit) {
        this.limit = limit;
        return format(text, limit, justify);
    }

    @Override
    public String format(String text, Integer limit, Boolean justify) {
        this.limit = limit;
        this.justify = justify;

        if (text.length() > 40) {
            String[] linhas = text.split(StringUtils.LF);

            for (String linhaASerFormatada : linhas) {
                if (!linhaASerFormatada.isEmpty()) {
                    processLines(linhaASerFormatada);
                }
                formattedText.append(StringUtils.LF);
            }

        } else {
            return text;
        }

        return formattedText.toString();
    }
}