package idwall.desafio.string;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Rodrigo Catão Araujo on 06/02/2018.
 */
public class IdwallFormatter extends StringFormatter {

    private static final Integer DEFAULT_NUMBER_OF_CHARS = 40;
    private static final Boolean DEFAULT_JUSTIFY = true;

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
                formattedText.append(processLines(linhaASerFormatada));
            }

        } else {
            return text;
        }

        return formattedText.toString();
    }

    private StringBuilder processLines(String linhaASerFormatada) {
        StringBuilder formattedLines = new StringBuilder();
        String[] splittedWords = linhaASerFormatada.split(" ");
        StringBuilder aux = new StringBuilder();

        for (String pacoca : splittedWords) {
            if ((aux + StringUtils.SPACE + pacoca).length() > DEFAULT_NUMBER_OF_CHARS) {
                formattedLines.append(aux).append(StringUtils.LF);
                aux = new StringBuilder(pacoca);
            } else if ((aux + pacoca).length() <= 40) {
                aux.append(pacoca).append(StringUtils.SPACE);
            }
        }
        return formattedLines;
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
