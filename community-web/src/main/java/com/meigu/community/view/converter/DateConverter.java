package com.meigu.community.view.converter;

import java.io.IOException;
import java.text.*;
import java.util.*;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.meigu.community.util.common.DateUtil;

@Component
public class DateConverter extends SimpleDateFormat implements Converter<String, Date>{

	private static final long serialVersionUID = 1L;

	
	public StringBuffer format(Date date, StringBuffer toAppendTo,FieldPosition pos) {
		return new StringBuffer(DateUtil.format(date, "yyyy-MM-dd"));
	}

	@Override
	public Date parse(String text, ParsePosition pos) {
		return DateUtil.parseDate(text);
	}

	@Override
	public Date parse(String source) throws ParseException {
		return DateUtil.parseDate(source);
	}

	public Date deserialize(JsonParser parser, DeserializationContext context)throws IOException, JsonProcessingException {
		return DateUtil.parseDate(parser.getText());
	}

	@Override
	public Date convert(String source) {
		return DateUtil.parseDate(source);
	}
}
