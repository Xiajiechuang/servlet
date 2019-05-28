package teste;

import util.DBUtils;

public class TesrCase {
	public static void main(String[] args) {
		try {
			System.out.println(DBUtils.getConn());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
