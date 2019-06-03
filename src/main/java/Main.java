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
    private static Student editStudent;

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
            StringWriter writer = new StringWriter();
            try {
                String matricula = request.queryParams("matricula");
                String nombre = request.queryParams("nombre");
                String apellido = request.queryParams("apellido");
                String telefono = request.queryParams("telefono");
                String matriculaParseada = matricula.replace(",", "");
                students.add(new Student(Integer.parseInt(matriculaParseada), nombre, apellido, telefono));
                response.redirect("/");
            }catch (Exception error){
                System.out.println("Hubo un error agregando un estudiante " + error.toString());
                response.redirect("/addStudent");
            }
            return writer;
        });


        get("/viewStudents", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "Inicio");
            attributes.put("countStudents", students.size());
            attributes.put("students",students);
            return new ModelAndView(attributes, "viewStudents.ftl");
        }, freeMarkerEngine);

        get("/generate", (request, response) -> {

            students.add(new Student(20102155, "Gabriela", "Rodriguez", "8095137521"));
            students.add(new Student(20102156, "Alex", "Guzman", "8095137522"));
            students.add(new Student(20102157, "Jose", "Reyes", "8095137523"));
            students.add(new Student(20102158, "Ana", "Diaz", "8095137524"));

            response.redirect("/viewStudents");
            return "";
        });

        get("/delete/:posicion",(request, response) -> {

            String posicion = request.params("posicion");

            students.remove(Integer.parseInt(posicion));


            response.redirect("/");
            return "";
        });

        get("/edit/:posicion", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            String posicion = request.params("posicion");
            Student student = students.get(Integer.parseInt(posicion));

            editStudent = student;
            attributes.put("titulo", "Editar estudiante");
            attributes.put("students", student);

            return new ModelAndView(attributes, "edit.ftl");
        }, freeMarkerEngine);

        post("/editarP",(request, response) -> {
            StringWriter writer = new StringWriter();
            try {
                String matricula = request.queryParams("matricula");
                String nombre = request.queryParams("nombre");
                String apellido = request.queryParams("apellido");
                String telefono = request.queryParams("telefono");

                int pos = posicionEstudiante(editStudent);

                editStudent.setMatricula(Integer.parseInt(matricula));
                editStudent.setNombre(nombre);
                editStudent.setApellido(apellido);
                editStudent.setTelefono(telefono);

                if (pos != -1){

                    students.set(pos, editStudent);
                    editStudent = null;
                }

                response.redirect("/");
            }catch (Exception error){
                System.out.println("Hubo un error editando un estudiante " + error.toString());

            }
            return writer;
        });

        get("/view/:posicion", (request, response) -> {

            String posicion = request.params("posicion");
            Student student = students.get(Integer.parseInt(posicion));

            Map<String, Object> attributes = new HashMap<>();
            attributes.put("student", student);

            return new ModelAndView(attributes, "view.ftl");
        }, freeMarkerEngine);


    }


    private static int posicionEstudiante(Student student){

        for (int i =0; i < students.size(); i++){

            if (students.get(i) == student)
            {
                return i;
            }
        }

        return  -1;
    }

}

