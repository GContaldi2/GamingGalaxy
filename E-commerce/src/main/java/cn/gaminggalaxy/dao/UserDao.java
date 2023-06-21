package cn.gaminggalaxy.dao;

import java.sql.*;

import javax.servlet.http.HttpServletRequest;

import cn.gaminggalaxy.model.*;

public class UserDao {
	private Connection con;

	private String query;
    private PreparedStatement pst;
    private ResultSet rs;
    
   

	public UserDao(Connection con) {
		this.con = con;
	}
	
	public User userLogin(String email, String password) {
		User user = null;
        try {
            query = "select * from users where email=? and password=?";
            pst = this.con.prepareStatement(query);
            pst.setString(1, email);
            pst.setString(2, password);
            rs = pst.executeQuery();
            if(rs.next()){
            	user = new User();
            	user.setId(rs.getInt("id"));
            	user.setName(rs.getString("name"));
            	user.setCognome(rs.getString("cognome"));
            	user.setEmail(rs.getString("email"));
            	user.setUserType(rs.getString("userType"));
            	user.setAddress(rs.getString("address"));
            	user.setCity(rs.getString("city"));
            	user.setProvincia(rs.getString("provincia"));
            	user.setZipCode(rs.getString("zipCode"));
            	user.setCartNumber(rs.getString("cartNumber"));
            	user.setMese(rs.getString("mese"));
            	
            }
        } catch (SQLException e) {
            	//e.printStackTrace();
            }
         	return user;
    }
  
	
	public boolean registerUser(User user) {
		try {
	        String query = "INSERT INTO users (email, password, name, cognome, address, city, provincia, zipCode, cartNumber,mese,cvv) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        PreparedStatement pst = con.prepareStatement(query);
	        pst.setString(1, user.getEmail());
	        pst.setString(2, user.getPassword());
	        pst.setString(3, user.getName());
	        pst.setString(4, user.getCognome());
	        pst.setString(5, user.getAddress());
	        pst.setString(6, user.getCity());
	        pst.setString(7, user.getProvincia());
	        pst.setString(8, user.getZipCode());
	        pst.setString(9, user.getCartNumber());
	        pst.setString(10, user.getMese());
	        pst.setString(11, user.getCvv());
	        
	        int rowsAffected = pst.executeUpdate();
	        
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        //e.printStackTrace();
	    }
	    
	    return false;
	}
	
	public String getPassword(int userId) throws SQLException {
	    String password = null;

	    // Create a SELECT query to get the password column value for the row with the matching user ID
	    String query = "SELECT password FROM users WHERE id = ?";
	    PreparedStatement preparedStatement = con.prepareStatement(query);
	    preparedStatement.setInt(1, userId);

	    // Execute the query and retrieve the result
	    ResultSet resultSet = preparedStatement.executeQuery();
	    if (resultSet.next()) {
	        password = resultSet.getString("password");
	    }

	    return password;
	}
	
	public boolean changeEmail(User user, HttpServletRequest request) {
		User auth = (User) request.getSession().getAttribute("auth");
	    try {
	        String query = "UPDATE users SET email=? WHERE id=?";
	        PreparedStatement pst = con.prepareStatement(query);
	        pst.setString(1, user.getEmail());
	        pst.setInt(2, user.getId());

	        int rowsAffected = pst.executeUpdate();

	        if (rowsAffected > 0) {
	            String getUserQuery = "SELECT * FROM users WHERE id=?";
	            PreparedStatement getUserPst = con.prepareStatement(getUserQuery);
	            getUserPst.setInt(1, user.getId());

	            ResultSet resultSet = getUserPst.executeQuery();

	            if (resultSet.next()) {
	                String email = resultSet.getString("email");
	                // Recupera tutti gli altri dati necessari dall'oggetto ResultSet

	                User updatedUser = new User(user.getId(), email);
	                // Aggiorna l'oggetto User auth con la nuova email
	                auth.setEmail(email);
	                // Salva l'oggetto User auth nella sessione
	                request.getSession().setAttribute("auth", auth);

	                return true;
	            }
	        }
	    } catch (SQLException e) {
	        //e.printStackTrace();
	    }

	    return false;
	}
	
	
	public boolean changePassword(User user, HttpServletRequest request) {
		User auth = (User) request.getSession().getAttribute("auth");
	    try {
	        String query = "UPDATE users SET password=? WHERE id=?";
	        PreparedStatement pst = con.prepareStatement(query);
	        pst.setString(1, user.getPassword());
	        pst.setInt(2, user.getId());

	        int rowsAffected = pst.executeUpdate();

	        if (rowsAffected > 0) {
	            String getUserQuery = "SELECT * FROM users WHERE id=?";
	            PreparedStatement getUserPst = con.prepareStatement(getUserQuery);
	            getUserPst.setInt(1, user.getId());

	            ResultSet resultSet = getUserPst.executeQuery();

	            if (resultSet.next()) {
	                String password = resultSet.getString("password");
	                // Recupera tutti gli altri dati necessari dall'oggetto ResultSet

	                User updatedUser = new User(user.getId(), password);
	                // Aggiorna l'oggetto User auth con la nuova password
	                auth.setPassword(password);
	                // Salva l'oggetto User auth nella sessione
	                request.getSession().setAttribute("auth", auth);

	                return true;
	            }
	        }
	    } catch (SQLException e) {
	        //e.printStackTrace();
	    }

	    return false;
	}

	
	public boolean changeAddress(User user, HttpServletRequest request) {
		User auth = (User) request.getSession().getAttribute("auth");
	    try {
	        String query = "UPDATE users SET address=? WHERE id=?";
	        PreparedStatement pst = con.prepareStatement(query);
	        pst.setString(1, user.getAddress());
	        pst.setInt(2, user.getId());

	        int rowsAffected = pst.executeUpdate();

	        if (rowsAffected > 0) {
	            String getUserQuery = "SELECT * FROM users WHERE id=?";
	            PreparedStatement getUserPst = con.prepareStatement(getUserQuery);
	            getUserPst.setInt(1, user.getId());

	            ResultSet resultSet = getUserPst.executeQuery();

	            if (resultSet.next()) {
	                String address = resultSet.getString("address");
	                // Recupera tutti gli altri dati necessari dall'oggetto ResultSet

	                User updatedUser = new User(user.getId(), address);
	                // Aggiorna l'oggetto User auth con il nuovo indirizzo
	                auth.setAddress(address);
	                // Salva l'oggetto User auth nella sessione
	                request.getSession().setAttribute("auth", auth);

	                return true;
	            }
	        }
	    } catch (SQLException e) {
	        //e.printStackTrace();
	    }

	    return false;
	}
	
	
	public boolean changeCitta(User user, HttpServletRequest request) {
		User auth = (User) request.getSession().getAttribute("auth");
	    try {
	        String query = "UPDATE users SET city=? WHERE id=?";
	        PreparedStatement pst = con.prepareStatement(query);
	        pst.setString(1, user.getCity());
	        pst.setInt(2, user.getId());

	        int rowsAffected = pst.executeUpdate();

	        if (rowsAffected > 0) {
	            String getUserQuery = "SELECT * FROM users WHERE id=?";
	            PreparedStatement getUserPst = con.prepareStatement(getUserQuery);
	            getUserPst.setInt(1, user.getId());

	            ResultSet resultSet = getUserPst.executeQuery();

	            if (resultSet.next()) {
	                String city = resultSet.getString("city");
	                // Recupera tutti gli altri dati necessari dall'oggetto ResultSet

	                User updatedUser = new User(user.getId(), city);
	                // Aggiorna l'oggetto User auth con la nuova città
	                auth.setCity(city);
	                // Salva l'oggetto User auth nella sessione
	                request.getSession().setAttribute("auth", auth);

	                return true;
	            }
	        }
	    } catch (SQLException e) {
	        //e.printStackTrace();
	    }

	    return false;
	}

	public boolean changeCap(User user, HttpServletRequest request) {
		User auth = (User) request.getSession().getAttribute("auth");
	    try {
	        String query = "UPDATE users SET zipCode=? WHERE id=?";
	        PreparedStatement pst = con.prepareStatement(query);
	        pst.setString(1, user.getZipCode());
	        pst.setInt(2, user.getId());

	        int rowsAffected = pst.executeUpdate();

	        if (rowsAffected > 0) {
	            String getUserQuery = "SELECT * FROM users WHERE id=?";
	            PreparedStatement getUserPst = con.prepareStatement(getUserQuery);
	            getUserPst.setInt(1, user.getId());

	            ResultSet resultSet = getUserPst.executeQuery();

	            if (resultSet.next()) {
	                String zipCode = resultSet.getString("zipCode");
	                // Recupera tutti gli altri dati necessari dall'oggetto ResultSet

	                User updatedUser = new User(user.getId(), zipCode);
	                // Aggiorna l'oggetto User auth con il nuovo CAP
	                auth.setZipCode(zipCode);
	                // Salva l'oggetto User auth nella sessione
	                request.getSession().setAttribute("auth", auth);

	                return true;
	            }
	        }
	    } catch (SQLException e) {
	        //e.printStackTrace();
	    }

	    return false;
	}
	
	public boolean changeProvincia(User user, HttpServletRequest request) {
		User auth = (User) request.getSession().getAttribute("auth");
	    try {
	        String query = "UPDATE users SET provincia=? WHERE id=?";
	        PreparedStatement pst = con.prepareStatement(query);
	        pst.setString(1, user.getProvincia());
	        pst.setInt(2, user.getId());

	        int rowsAffected = pst.executeUpdate();

	        if (rowsAffected > 0) {
	            String getUserQuery = "SELECT * FROM users WHERE id=?";
	            PreparedStatement getUserPst = con.prepareStatement(getUserQuery);
	            getUserPst.setInt(1, user.getId());

	            ResultSet resultSet = getUserPst.executeQuery();

	            if (resultSet.next()) {
	                String provincia= resultSet.getString("provincia");
	                // Recupera tutti gli altri dati necessari dall'oggetto ResultSet

	                User updatedUser = new User(user.getId(), provincia);
	                // Aggiorna l'oggetto User auth con la nuova provincia
	                auth.setProvincia(provincia);
	                // Salva l'oggetto User auth nella sessione
	                request.getSession().setAttribute("auth", auth);

	                return true;
	            }
	        }
	    } catch (SQLException e) {
	        //e.printStackTrace();
	    }

	    return false;
	}
	
	
	public boolean changeCarta(User user, HttpServletRequest request) {
		User auth = (User) request.getSession().getAttribute("auth");
	    try {
	        String query = "UPDATE users SET cartNumber=? WHERE id=?";
	        PreparedStatement pst = con.prepareStatement(query);
	        pst.setString(1, user.getCartNumber());
	        pst.setInt(2, user.getId());

	        int rowsAffected = pst.executeUpdate();

	        if (rowsAffected > 0) {
	            String getUserQuery = "SELECT * FROM users WHERE id=?";
	            PreparedStatement getUserPst = con.prepareStatement(getUserQuery);
	            getUserPst.setInt(1, user.getId());

	            ResultSet resultSet = getUserPst.executeQuery();

	            if (resultSet.next()) {
	                String cartNumber = resultSet.getString("cartNumber");
	                // Recupera tutti gli altri dati necessari dall'oggetto ResultSet

	                User updatedUser = new User(user.getId(), cartNumber);
	                // Aggiorna l'oggetto User auth con la nuova carta
	                auth.setCartNumber(cartNumber);
	                // Salva l'oggetto User auth nella sessione
	                request.getSession().setAttribute("auth", auth);

	                return true;
	            }
	        }
	    } catch (SQLException e) {
	        //e.printStackTrace();
	    }

	    return false;
	}
	
	
	
	public boolean changeMese(User user, HttpServletRequest request) {
		User auth = (User) request.getSession().getAttribute("auth");
	    try {
	        String query = "UPDATE users SET mese=? WHERE id=?";
	        PreparedStatement pst = con.prepareStatement(query);
	        pst.setString(1, user.getMese());
	        pst.setInt(2, user.getId());

	        int rowsAffected = pst.executeUpdate();

	        if (rowsAffected > 0) {
	            String getUserQuery = "SELECT * FROM users WHERE id=?";
	            PreparedStatement getUserPst = con.prepareStatement(getUserQuery);
	            getUserPst.setInt(1, user.getId());

	            ResultSet resultSet = getUserPst.executeQuery();

	            if (resultSet.next()) {
	                String mese = resultSet.getString("mese");
	                // Recupera tutti gli altri dati necessari dall'oggetto ResultSet

	                User updatedUser = new User(user.getId(), mese);
	                // Aggiorna l'oggetto User auth con la nuova scadenza
	                auth.setMese(mese);
	                // Salva l'oggetto User auth nella sessione
	                request.getSession().setAttribute("auth", auth);

	                return true;
	            }
	        }
	    } catch (SQLException e) {
	        //e.printStackTrace();
	    }

	    return false;
	}
	
	public boolean changeCvv(User user, HttpServletRequest request) {
		User auth = (User) request.getSession().getAttribute("auth");
	    try {
	        String query = "UPDATE users SET cvv=? WHERE id=?";
	        PreparedStatement pst = con.prepareStatement(query);
	        pst.setString(1, user.getCvv());
	        pst.setInt(2, user.getId());

	        int rowsAffected = pst.executeUpdate();

	        if (rowsAffected > 0) {
	            String getUserQuery = "SELECT * FROM users WHERE id=?";
	            PreparedStatement getUserPst = con.prepareStatement(getUserQuery);
	            getUserPst.setInt(1, user.getId());

	            ResultSet resultSet = getUserPst.executeQuery();

	            if (resultSet.next()) {
	                String cvv= resultSet.getString("cvv");
	                // Recupera tutti gli altri dati necessari dall'oggetto ResultSet

	                User updatedUser = new User(user.getId(), cvv);
	                // Aggiorna l'oggetto User auth con il nuovo CVV
	                auth.setCvv(cvv);
	                // Salva l'oggetto User auth nella sessione
	                request.getSession().setAttribute("auth", auth);

	                return true;
	            }
	        }
	    } catch (SQLException e) {
	        //e.printStackTrace();
	    }

	    return false;
	}
}