package com.trodix.teengine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

import com.trodix.teengine.core.functions.DateProcessor;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DateProcessorTests {

	static DateProcessor dateProcessor;

	@BeforeAll
	public static void setup() {
		dateProcessor = new DateProcessor();
	}

	@Test
	void dateProcessor_regexTest() {
		String rawTemplate = "{@date(created_at, yyyy-dd-MM)}";
		Matcher matcher = dateProcessor.getTemplateByKey("created_at").matcher(rawTemplate);

		assertTrue(matcher.matches());
	}

	@Test
	void dateProcessor_regexAllTest() {
		String rawTemplate = "{cm:name}_{cpage-eco:nature-document}_{@date(created_at, yyyy-dd-MM)}";
		Matcher matcher = dateProcessor.getTemplateList().matcher(rawTemplate);

		assertTrue(matcher.matches());
	}

	@Test
	void dateProcessor_replaceValueTest() {
		String rawTemplate = "{cm:name}_{cpage-eco:nature-document}_{@date(created_at, yyyy-dd-MM)}";

		String expected = "{cm:name}_{cpage-eco:nature-document}_2020-12-15";
		String actual = dateProcessor.replaceValue(rawTemplate, "created_at", "2020-12-15");

		assertEquals(expected, actual);
	}

	@Test
	void dateProcessor_processTest() {
		String rawTemplate = "{cm:name}_{cpage-eco:nature-document}_{@date(created_at, MM-dd-yyyy)}";
		Map<String, Serializable> keyValueAssoc = new HashMap<>();
		keyValueAssoc.put("cm:name", "toto.pdf");
		keyValueAssoc.put("cpage-eco:nature-document", "FAC");
		keyValueAssoc.put("created_at", "2020-12-15");

		String expected = "{cm:name}_{cpage-eco:nature-document}_12-15-2020";
		String actual = dateProcessor.process(rawTemplate, keyValueAssoc);

		assertEquals(expected, actual);
	}

}
