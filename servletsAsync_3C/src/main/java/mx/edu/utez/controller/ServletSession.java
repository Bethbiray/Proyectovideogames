package mx.edu.utez.controller;

import mx.edu.utez.model.user.BeanUser;
import mx.edu.utez.model.user.DaoUser;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletSession", urlPatterns = {"/login", "/logout"})
public class ServletSession extends HttpServlet {
    /**
     * Cierre de session de la aplicacion.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session= request.getSession();
        session.setAttribute("sesion",null);
        session.invalidate();

        request.getRequestDispatcher("/index").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        BeanUser beanUser= new BeanUser();
        DaoUser daoUser= new DaoUser();

        beanUser.setEmail(request.getParameter("email"));
        beanUser.setPassword(request.getParameter("password"));

        boolean res= daoUser.createSession(
                beanUser.getEmail(),
                beanUser.getPassword()

        );
        if (res==true){
            session.setAttribute("session",beanUser);
            request.getRequestDispatcher("views/inicio.jsp").forward(request,response);
        }else{
            request.getRequestDispatcher("/").forward(request,response);
        }

    }
}
