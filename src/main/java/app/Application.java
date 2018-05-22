package app;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.port;

public class Application {
    public static void main(String[] args) {
        // Configure Spark
        port(4567);
        // staticFiles.location("/public");
        // staticFiles.expireTime(600L);
        // enableDebugScreen();

        DBHelper db = new DBHelper();

        // Set up our routes
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new VelocityTemplateEngine().render(
                new ModelAndView(model, "/templates/index.html")
            );
        });

        get("/finduser", (request, response) -> {
            Map<String, Object> model = new HashMap<>();

            String sql = String.format("select * from users where user = '%s'", request.queryParams("user"));
            System.out.println(sql.toString());
            List<User> usersList = db.select(sql);
            System.out.println(String.format("[*] Found %d entries", usersList.size()));
            model.put("users", usersList);

            return new VelocityTemplateEngine().render(new ModelAndView(model, "/templates/index.html"));
        });
    }

    // declare this in a util-class
    public static String render(Map<String, Object> model, String templatePath) {
        return new VelocityTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
