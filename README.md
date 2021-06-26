# Teengine - A Java template engine

## How to use?

```java

String template = "{filename}_{author}_{@date(created_at, MM-dd-yyyy)}";
Map<String, Serializable> keyValueAssoc = new HashMap<>();
keyValueAssoc.put("filename", "toto.pdf");
keyValueAssoc.put("author", "John");
keyValueAssoc.put("created_at", "2020-12-15");

Runner runner = new Runner(new ProcessorConfiguration());
String result = runner.process(template, keyValueAssoc);

// result: todo.pdf_John_12-15-2020

```

## Add new functionnality

```java

public class CustomProcessor extends AbstractFunctionProcessor {

    @Override
    public String process(String rawTemplate, Map<String, Serializable> keyValueAssoc) {
        // do something
    }

    @Override
    public String getStartDelimiter() {
        // do something
    }

    @Override
    public String getEndDelimiter() {
        // do something
    }

    @Override
    public String getArgsDelimiter() {
        // do something
    }

}


ProcessorConfiguration config = new ProcessorConfiguration();
config.addRegisteredProcessors(new CustomProcessor());

Runner runner = new Runner(config);
...

```
