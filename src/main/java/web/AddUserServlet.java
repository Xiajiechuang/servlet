package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DBUtils;

public class AddUserServlet extends HttpServlet{
	public void service(HttpServletRequest request,HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("gbk");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String mail = request.getParameter("e-mail");
		//将用户信息插入数据库
		response.setContentType("text/html;charset=gbk");
		PrintWriter out = response.getWriter();
		Connection conn = null;
		PreparedStatement stat = null;
		try {
			conn = DBUtils.getConn();
			stat = conn.prepareStatement("insert into t_user values(null,?,?,?)");
			stat.setString(1, name);
			stat.setString(2, password);
			stat.setString(3, mail);
			stat.executeUpdate();
			out.println();
		} catch (Exception e) {
			//step1.记日志
			//将异常的所有信息记录下来，一般记录到文件里。
			e.printStackTrace();
			/*
			 * step2.看异常能否恢复，如果不能（比如，数据库服务暂停，网络中断等等，一般把这样的异常称为系统异常）
			 * 则提示用户稍后在试）若果能恢复则立即恢复。
			 */
			out.println("系统繁忙，稍后重试");
		}finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//如果没有调用out.close方法，则容器会自动关闭该方法。
		out.close();
	}
}
