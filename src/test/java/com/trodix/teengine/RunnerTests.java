package com.trodix.teengine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.trodix.teengine.core.Runner;
import com.trodix.teengine.core.config.ProcessorConfiguration;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RunnerTests {

	static Runner runner;

	@BeforeAll
	public static void setup() {
		runner = new Runner(new ProcessorConfiguration());
	}

	@Test
	void placeholderProcessor_processTest() {
		String rawTemplate = "{cm:name}_{cpage-eco:nature-document}_{@date(created_at, dd-MM-yyyy)}";
		Map<String, Serializable> keyValueAssoc = new HashMap<>();
		keyValueAssoc.put("cm:name", "toto.pdf");
		keyValueAssoc.put("cpage-eco:nature-document", "FAC");
		keyValueAssoc.put("created_at", "2020-12-15");

		String expected = "toto.pdf_FAC_15-12-2020";
		String actual = runner.process(rawTemplate, keyValueAssoc);

		assertEquals(expected, actual);
	}

}
