package com.trodix.teengine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.trodix.teengine.core.interfaces.Processor;
import com.trodix.teengine.core.processor.PlaceholderProcessor;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PlaceholderProcessorTests {

	static Processor placeholderProcessor;

	@BeforeAll
	public static void setup() {
		placeholderProcessor = new PlaceholderProcessor();
	}

	@Test
	void placeholderProcessor_replaceValueTest() {
		String rawTemplate = "{cm:name}_{cpage-eco:nature-document}";

		String expected = "toto.pdf_{cpage-eco:nature-document}";
		String actual = placeholderProcessor.replaceValue(rawTemplate, "cm:name", "toto.pdf");

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
		String actual = placeholderProcessor.process(rawTemplate, keyValueAssoc);

		assertEquals(expected, actual);
	}

}
