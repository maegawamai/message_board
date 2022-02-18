package controllers;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Message;
import utils.DBUtil;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();//DBの神誕生（DAOみたいなもの）

        List<Message> messages = em.createNamedQuery("getAllMessages", Message.class).getResultList();
        /*getAllMessageをcreateNamedQueryメソッドの引数に指定することで、
         * DBへの問い合わせを実行。問い合わせ結果をgetResultList()をつかって
         * リスト形式で取得する。DBに保存されたデータはhibernateによって
         * 自動でMessageクラスのオブジェクトになってこのリストの中に格納される*/

        em.close();
        request.setAttribute("messages", messages);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/messages/index.jsp");
        rd.forward(request, response);
 }

}
