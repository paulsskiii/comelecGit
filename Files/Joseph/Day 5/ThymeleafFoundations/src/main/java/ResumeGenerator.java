import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import java.io.FileWriter;
import java.io.IOException;

public class ResumeGenerator {
    public static void main(String[] args) throws IOException {
        // Setup Resolver
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML");

        // Setup Engine
        TemplateEngine engine = new TemplateEngine();
        engine.setTemplateResolver(resolver);

        // Setup Data
        Context context = new Context();
        context.setVariable("name", "Jane Smith");
        context.setVariable("role", "Thymeleaf Developer");
        context.setVariable("summary", "I am a dynamic variable injected by the Java engine! I replaced the static text.");

        // Render
        String renderedHtml = engine.process("resume", context);

        // Output
        System.out.println(renderedHtml);
        
        try (FileWriter writer = new FileWriter("dynamic-resume.html")) {
            writer.write(renderedHtml);
        }
    }
}
