package idwall.desafio.string;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Rodrigo Catão Araujo on 06/02/2018.
 */
public class IdwallFormatter extends StringFormatter {

    private static final Integer DEFAULT_NUMBER_OF_CHARS = 40;
    private static final Boolean DEFAULT_JUSTIFY = true;

    private int limit = DEFAULT_NUMBER_OF_CHARS;
    private boolean justified = DEFAULT_JUSTIFY;

    /**
     * Should format as described in the challenge
     *
     * @param text
     * @return
     */
    @Override
    public String format(String text) {
        StringBuilder formattedText = new StringBuilder();
        if (text.length() > 40) {
            String[] linhas = text.split(StringUtils.LF);
            //TODO MUDAR NOME DAS VARIAVEIS PARA INGLÊS

            for (String linhaASerFormatada : linhas) {
                if (linhaASerFormatada.isEmpty()) {
                    formattedText.append(StringUtils.LF);
                } else {
                    formattedText.append(processLines(linhaASerFormatada)).append(StringUtils.LF);
                }
            }

        } else {
            return text;
        }

        return formattedText.toString();
    }

    private StringBuilder processLines(String linhaASerFormatada) {
        StringBuilder formattedLines = new StringBuilder();
        List<String> splittedWords = Arrays.asList(linhaASerFormatada.split(" "));
        StringBuilder aux = new StringBuilder();

        for (String word : splittedWords) {

            int tamanho = (aux + word).length();
            if (splittedWords.get(splittedWords.size() - 1).equals(word)) {
//                formattedLines.append(aux).append(word);
                formattedLines.append(justificado(aux.toString()+word));
                justificado(aux.toString());
                aux = new StringBuilder();
            } else if (tamanho == DEFAULT_NUMBER_OF_CHARS) {
//                formattedLines.append(aux).append(word).append(StringUtils.LF);
                formattedLines.append(justificado(aux.toString()+word)).append(StringUtils.LF);
                System.out.println("Entrei aqui" + aux);
                justificado(aux.toString());
                aux = new StringBuilder();
            } else if (tamanho > DEFAULT_NUMBER_OF_CHARS) {
                System.out.println("Entrei aqui : ->>>>" + aux);
                justificado(aux.toString());
//                formattedLines.append(aux).append(StringUtils.LF);
                formattedLines.append(justificado(aux.toString())).append(StringUtils.LF);
                aux = new StringBuilder(word).append(StringUtils.SPACE);
            } else if (tamanho < 40) {
                aux.append(word).append(StringUtils.SPACE);
            }
        }
        return formattedLines;
    }

    public String justificado(String line) {
        if (justified) {
            if (line.length() == 40) {
            } else {
                int diferencaEntreTamanhoDaFraseEDoJustificado = limit - line.length();
                for (int i = line.indexOf(' '); i < line.length(); i++) {
                    if (line.charAt(i) == ' ' && diferencaEntreTamanhoDaFraseEDoJustificado > 0) {
                        line = line.replace(" ", "  ");
                        break;
                    }
                    if (i < line.length()-1 && diferencaEntreTamanhoDaFraseEDoJustificado > 0) {
                        i = line.indexOf(' ');
                    }
                }
                System.out.println(StringUtils.countMatches(line, " ") + "------------>" + diferencaEntreTamanhoDaFraseEDoJustificado);
            }
        }
        System.out.println(line);
        return line;
    }

    @Override
    public String format(String text, Integer limit) {
        return null;
    }

    @Override
    public String format(String text, Integer limit, Boolean justify) {
        return null;
    }
}
