/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exe;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ashura
 */
public class DelKat extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        util.General.ajarAdmin(req, res);
        if(null!=req.getParameter("kode"))try {
            util.Db d=new util.Db();
            java.sql.PreparedStatement p1=d.getPrep("select kode from menu where kat=?"),p2,p3;
            p1.setInt(1, Integer.parseInt(req.getParameter("kode")));
            java.sql.ResultSet r=p1.executeQuery();
            while(r.next()){
                java.sql.PreparedStatement pa=d.getPrep("delete from bahan where menu=?"),pb;
                pb=d.getPrep("delete from item_pesanan where menu=?");
                pa.setString(1, r.getString("kode"));
                pb.setString(1, r.getString("kode"));
                pa.execute();
                pb.execute();
                pb.close();
                pa.close();
            }r.close();
            p1.close();
            p2=d.getPrep("delete from menu where kat=?");
            p3=d.getPrep("delete from kat_menu where kode=?");
            p2.setInt(1, Integer.parseInt(req.getParameter("kode")));
            p3.setInt(1, Integer.parseInt(req.getParameter("kode")));
            p2.execute();
            p3.execute();
            p2.close();
            p3.close();
            d.close();
        } catch (SQLException ex) {
            util.Db.hindar(ex, req.getRemoteAddr());
        }res.sendRedirect("dash.php");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
