package com.ensta.librarymanager.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.service.EmpruntService;
import com.ensta.librarymanager.service.EmpruntServiceImpl;
import com.ensta.librarymanager.service.LivreService;
import com.ensta.librarymanager.service.LivreServiceImpl;
import com.ensta.librarymanager.service.MembreService;
import com.ensta.librarymanager.service.MembreServiceImpl;

public class DashboardServlet extends HttpServlet {

  /**
   *
   */
  private static final long serialVersionUID = 2106100894903979468L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getServletPath();
    switch (action) {
    case "/dashboard":
      showDashboard(request, response);
      break;

    default:
      break;
    }
  }

  private void showDashboard(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    EmpruntService empruntService = EmpruntServiceImpl.getInstance();
    LivreService livreService = LivreServiceImpl.getInstance();
    MembreService membreService = MembreServiceImpl.getInstance();
    try {
      request.setAttribute("nbLivres", livreService.count());
      request.setAttribute("nbMembres", membreService.count());
      request.setAttribute("nbEmprunts", empruntService.count());
      request.setAttribute("listeEmprunts", empruntService.getListCurrent());
    } catch (Exception e) {
      e.printStackTrace();
    }

    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/dashboard.jsp");
    dispatcher.forward(request, response);
  }
}
