package com.trodix.teengine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

import com.trodix.teengine.core.functions.DateProcessor;
import com.trodix.teengine.core.processor.PlaceholderProcessor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TeengineApplicationTests {

	@Autowired
	private PlaceholderProcessor placeholderProcessor;

	@Autowired
	private DateProcessor dateProcessor;

	@Test
	void placeholderProcessor_replaceValueTest() {
		String rawTemplate = "{cm:name}_{cpage-eco:nature-document}";

		String expected = "toto.pdf_{cpage-eco:nature-document}";
		String actual = this.placeholderProcessor.replaceValue(rawTemplate, "cm:name", "toto.pdf");

		assertEquals(expected, actual);
	}

	@Test
	void placeholderProcessor_processTest() {
		String rawTemplate = "{cm:name}_{cpage-eco:nature-document}_{@date(created_at, yyyy-dd-MM)}";
		Map<String, Serializable> keyValueAssoc = new HashMap<>();
		keyValueAssoc.put("cm:name", "toto.pdf");
		keyValueAssoc.put("cpage-eco:nature-document", "FAC");
		keyValueAssoc.put("created_at", "2020-12-15");

		String expected = "toto.pdf_FAC_{@date(created_at, yyyy-dd-MM)}";
		String actual = this.placeholderProcessor.process(rawTemplate, keyValueAssoc);

		assertEquals(expected, actual);
	}

	@Test
	void dateProcessor_regexTest() {
		String rawTemplate = "{@date(created_at, yyyy-dd-MM)}";
		Matcher matcher = this.dateProcessor.getTemplateByKey("created_at").matcher(rawTemplate);

		assertTrue(matcher.matches());
	}

	@Test
	void dateProcessor_regexAllTest() {
		String rawTemplate = "{cm:name}_{cpage-eco:nature-document}_{@date(created_at, yyyy-dd-MM)}";
		Matcher matcher = this.dateProcessor.getTemplateList().matcher(rawTemplate);

		assertTrue(matcher.matches());
	}

	@Test
	void dateProcessor_replaceValueTest() {
		String rawTemplate = "{cm:name}_{cpage-eco:nature-document}_{@date(created_at, yyyy-dd-MM)}";

		String expected = "{cm:name}_{cpage-eco:nature-document}_2020-12-15";
		String actual = this.dateProcessor.replaceValue(rawTemplate, "created_at", "2020-12-15");

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
		String actual = this.dateProcessor.process(rawTemplate, keyValueAssoc);

		assertEquals(expected, actual);
	}

}
