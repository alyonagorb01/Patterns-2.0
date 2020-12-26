package ua.nure.st.kpp.example.demo.MyDAO;



public class DAOFactory {
	private static IDAO dao = null;

	public static IDAO getDAOInstance(TypeDAO type) {
		if (dao == null) {
			dao = new MySQLDAO();
		}
		return dao;
	}
}

