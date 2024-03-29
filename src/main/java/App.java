
import spark.ModelAndView;

import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        ProcessBuilder process = new ProcessBuilder();
           Integer port;
           if (process.environment().get("PORT") != null) {
               port = Integer.parseInt(process.environment().get("PORT"));
           } else {
               port = 4567;
           }
      
          setPort(port);
  
          staticFileLocation("/public");
          String layout = "templates/layout.vtl";
  

       //BasicConfigurator.configure();

        get("/",(req,res) -> {
            Map<String,Object> model = new HashMap<>();
            model.put("template","templates/welcome.vtl");
            return new VelocityTemplateEngine().render(new ModelAndView(model,layout)
            );
        });

        get("/login",(req,res) ->{
            Map<String, Object> model = new HashMap<>();
            model.put("template","templates/login.vtl");

            return new VelocityTemplateEngine().render(new ModelAndView(model,layout)
            );
        });

        post("/login",(req,res) ->{
            Map<String, Object> model = new HashMap<>();
            String email = req.queryParams("email");
            String password = req.queryParams("password");
            String username = req.queryParams("username");

            if(email.trim().isEmpty() || password.trim().isEmpty())
            {
                System.out.println("please fill in all the fields");
            }
            else {
                if (!(User.allEmails().contains(email))) {
                    model.put("template","templates/login.vtl");
                    System.out.println("Email does not exist");
                }
                else {
                    User user = new User(email, password,username);

                    if(user.getUserPassword().equals(password))
                    {
                        model.put("email", email);
                        model.put("template","templates/home.vtl");
                    }
                    else
                    {
                        model.put("template","templates/login.vtl");

                        System.out.println("wrong password");
                    }
                }
            }

            return new VelocityTemplateEngine().render(
                    new ModelAndView(model,layout)
            );
        });


        get("/signUp",(req,res) ->{
            Map<String, Object> model = new HashMap<>();
            model.put("template","templates/signup.vtl");

            return new VelocityTemplateEngine().render(
                    new ModelAndView(model,layout)
            );
        });


        post("/signUp",(req,res) ->
        {
            Map<String, Object> model = new HashMap<>();

            String email = req.queryParams("email");
            String username = req.queryParams("username");
            String password = req.queryParams("password");
            String confirm_password = req.queryParams("confirm_password");

            if(email.trim().isEmpty() || username.trim().isEmpty() || password.trim().isEmpty() || confirm_password.trim().isEmpty())
            {
                System.out.println("please fill in all the fields");
            }
            else
            {
                if (!(User.allEmails().contains(email)))
                {
                    if(password.equals(confirm_password))
                    {
                        User user = new User(email,password,username);
                        user.register();
                        model.put("email" ,user.getEmail());
                        model.put("username" ,user.getUsername());
                        model.put("template","templates/home.vtl");
                    }
                    else {
                        System.out.println("password doesnt match");
                    }

                }
                else {
                    System.out.println("email already exist, log in instead");
                    res.redirect("/login");
                }

            }
            return new VelocityTemplateEngine().render(new ModelAndView(model,layout)
            );
        });

    }
}
