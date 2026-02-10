package com.learning.ThymeleafFoundations;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.FileWriter;
import java.io.IOException;

public class ResumeGenerator {

    public static void main(String[] args) throws IOException {

        // 1. Initialize the Template Resolver
        // This tells Thymeleaf WHERE to look for HTML files (in the resources folder)
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("/"); // Look in root of classpath/resources
        resolver.setSuffix(".html"); // Append .html to filenames
        resolver.setTemplateMode("HTML");

        // 2. Initialize the Template Engine
        // This is the core 'brain' that processes the HTML
        TemplateEngine engine = new TemplateEngine();
        engine.setTemplateResolver(resolver);

        System.out.println("Thymeleaf Engine Initialized...");
        // 3. Create the Context (The Data)
        // Think of this as the "Model" in MVC. We are putting data into a map.
        Context context = new Context();

        // We set variables that match the ${...} keys in our HTML
        context.setVariable("name", "Jane Smith");
        context.setVariable("role", "Thymeleaf Developer");
        context.setVariable("summary",
                "I am a dynamic variable injected by the Java engine! I replaced the static text.");

        System.out.println("Data Context Created for: " + context.getVariable("name"));
        // 4. Process the Template
        // "resume" refers to resume.html (because of the suffix we set earlier)
        String renderedHtml = engine.process("resume", context);

        // 5. Output the result
        System.out.println("\n--- FINAL GENERATED HTML ---\n");
        System.out.println(renderedHtml);

        // Optional: Save to a file to view the result in a browser
        try (FileWriter writer = new FileWriter("dynamic-resume.html")) {
            writer.write(renderedHtml);
            System.out.println("\nSuccessfully saved to 'dynamic-resume.html' in project root.");
        }
    }
}
