package com.example.zakupy;

import java.io.*;
import java.util.HashSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public static final String SessionID = "SHOPPING_LIST";

    public void init() {
        message = "Hello World from Servlet!";
    }

    private void processRequest(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("zakupy.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Object collecton = request.getSession().getAttribute(SessionID);

        if(collecton == null){
            request.getSession().setMaxInactiveInterval(10);
            request.getSession().setAttribute(SessionID, new HashSet<ShoppingModel>());
        }
        processRequest(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Object collecton = request.getSession().getAttribute(SessionID);

        HashSet<ShoppingModel> shoppingList;

        if(collecton == null){
            request.getSession().setMaxInactiveInterval(10);
            shoppingList = new HashSet<>();
        }
        else{
            shoppingList = (HashSet<ShoppingModel>) collecton;
        }

        String name =  request.getParameter("ProductName");
        String type =  request.getParameter("Type");
        String submitButton =  request.getParameter("SubmitButton");


        if(name != null && type != null && submitButton != null ){
            ShoppingModel m = new ShoppingModel();
            m.setType(type);
            m.setName(name);
            if(submitButton.equals("submit") && !shoppingList.contains(m)){
                shoppingList.add(m);
            }
            else if(submitButton.equals("delete")){
                shoppingList.remove(m);
            }
        }

        request.getSession().setAttribute(SessionID, shoppingList);

        processRequest(request, response);
    }

    public void destroy() {
    }
}