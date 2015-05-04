package com.meigu.community.view.converter;

import org.springframework.core.convert.converter.*;
import org.springframework.stereotype.Component;

import com.meigu.community.util.common.DateUtil;

@Component
public class StringToLongConverter  implements Converter<String,Long>{

	@Override
	public Long convert(String source) {
		Long value = null;
		try {
			value = Long.parseLong(source);
		} catch (Exception e) {
			value = DateUtil.parseDate(source).getTime();
		}
		return value;
	}
}
