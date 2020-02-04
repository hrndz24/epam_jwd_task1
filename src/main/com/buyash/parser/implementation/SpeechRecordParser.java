package com.buyash.parser.implementation;

import com.buyash.constants.IntegerConstant;
import com.buyash.constants.StringConstant;
import com.buyash.entity.AudioRecord;
import com.buyash.entity.SpeechRecord;
import com.buyash.exception.ParserException;
import com.buyash.parser.AudioRecordParser;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class SpeechRecordParser implements AudioRecordParser {

    private static final String SPEECH_RECORD_REGEX =
            "([0-9]+)\\|(.[^|]+)\\|([0-9]+\\.[0-9]{1,2})\\|(.[^|]+)";

    @Override
    public AudioRecord parseLineToRecord(String line) throws ParserException {
        if (!Pattern.matches(SPEECH_RECORD_REGEX, line)) {
            //todo log
            throw new ParserException("Impossible to parse line into speech record");
        }
        String[] speechRecordInfo = line.split(StringConstant.DELIMITER.getValue());

        int length = Integer.parseInt(speechRecordInfo[IntegerConstant.LENGTH_POSITION.getValue()]);
        String title = speechRecordInfo[IntegerConstant.TITLE_POSITION.getValue()];
        BigDecimal size = BigDecimal.valueOf(Double.parseDouble(speechRecordInfo[IntegerConstant.SIZE_POSITION.getValue()]));
        String narrator = speechRecordInfo[IntegerConstant.NARRATOR_POSITION.getValue()];

        return new SpeechRecord(length, title, size, narrator);
    }
}