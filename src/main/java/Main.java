import Student.Student;

import freemarker.template.Configuration;
import freemarker.template.Version;
import spark.template.freemarker.FreeMarkerEngine;
import spark.ModelAndView;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

public class Main {
    private static ArrayList<Student> students = new ArrayList<>();


    public static void main(String[] args) {
        staticFiles.location("/Templates");
        Configuration configuration = new Configuration(new Version(2, 3, 0));
        configuration.setClassForTemplateLoading(Main.class, "/Templates");
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(configuration);


        get("/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "Inicio");
            if (students.size() > 5)
                attributes.put("students",students.subList(0,5));
            else
                attributes.put("students",students);
            return new ModelAndView(attributes, "inicio.ftl");
        }, freeMarkerEngine);

        get("/addStudent", (request, response) -> configuration.getTemplate("form.ftl"));


        post("/add",(request, response) -> {
            StringWriter escritor = new StringWriter();
            try {
                String matricula = request.queryParams("matricula");
                String nombre = request.queryParams("nombre");
                String apellido = request.queryParams("apellido");
                String telefono = request.queryParams("telefono");
                String matriculaParseada = matricula.replace(",", "");
                Students.add(new Estudent(Integer.parseInt(matriculaParseada), nombre, apellido, telefono));
                response.redirect("/");
            }catch (Exception error){
                System.out.println("Hubo un error agregando un estudiante " + error.toString());
                response.redirect("/addStudent");
            }
            return escritor;
        });
    }
}
