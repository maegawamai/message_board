package controllers;

//index(一覧表示)コントローラー
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
//＠を使ってルーティング
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
    //doGetが起動する
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //DBの神誕生 emはDAO
        EntityManager em = DBUtil.createEntityManager();
        // 開くページ数を取得（デフォルトは1ページ目）
        int page = 1;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
        }

        //最大件数と開始位置を指定してメッセージを取得

        /*getAllMessageをcreateNamedQueryメソッドの引数に指定することで、
         * DBへの問い合わせを実行。問い合わせ結果をgetResultList()をつかって
         * リスト形式で取得する。DBに保存されたデータはhibernateによって
         * 自動でMessageクラスのオブジェクトになってこのリストの中に格納される*/
        List<Message> messages = em.createNamedQuery("getAllMessages", Message.class)
                .setFirstResult(15 * (page - 1))
                .setMaxResults(15)
                .getResultList();

        //全件数を取得
        long messages_count = (long) em.createNamedQuery("getMessagesCount", Long.class)
                .getSingleResult();

        em.close();
        //JSPに入力データを送る
        request.setAttribute("messages", messages);
        request.setAttribute("messages_count", messages_count); // 全件数
        request.setAttribute("page", page); // ページ数
        // フラッシュメッセージがセッションスコープにセットされていたら
        // リクエストスコープに保存する（セッションスコープからは削除）
        if (request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }
        //ビューとなるJSPを指定して表示する
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/messages/index.jsp");
        rd.forward(request, response);
    }

}
