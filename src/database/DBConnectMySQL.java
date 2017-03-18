/*This class is used to handle all connections to the MySQL instance. Every time a query needs to be run
 * against MySQL, appropriate methods in this class are called to run 
 * the appropriate queries. Each method gets a connection from the pool and returns it after use.
 */

package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Prospect;
import model.SolarCell;


public class DBConnectMySQL {

	//MySQL Queries
	private static final String QUERY_ADD_PROSPECT = "insert into prospect (last_name, first_name, age, email, phone_number, address_street, address_city, address_state, address_zip, installation, reasonInterested) values(?,?,?,?,?,?,?,?,?,?,?);";
	private static final String QUERY_LIST_SOLARCELLS = "select solarcell_type, solarcell_type_cd, price_per_watt from solarcell where price_per_watt<=? order by solarcell_type;";

	//Get SQL connection object from Connection Pool
	private static Connection getMySqlConnection(){
		return HikariDBPool.getConnection();
	}

	//End SQL connection by returning connection to pool
	public static void endMySqlConnection(Connection conn){
		HikariDBPool.returnConnection(conn);
	}

	/*
	 * Store SolarCity Prospect Details in DB
	 * @param Prospect
	 * @return boolean
	 */
	public static boolean saveProspect(Prospect p){
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result=0;
		if(p==null){
			return false;
		}else {
			try{
				conn = getMySqlConnection();
				pstmt = conn.prepareStatement(QUERY_ADD_PROSPECT);
				pstmt.setString(1,p.getLastName());
				pstmt.setString(2,p.getFirstName());
				pstmt.setInt(3, p.getAge());
				pstmt.setString(4, p.getEmail());
				pstmt.setString(5, p.getPhoneNumber());
				pstmt.setString(6, p.getAddressStreet());
				pstmt.setString(7, p.getAddressCity());
				pstmt.setString(8, p.getAddressState());
				pstmt.setInt(9, p.getAddressZip());
				pstmt.setBoolean(10, p.isInstallation());
				pstmt.setString(11, p.getReasonInterested());
				result = pstmt.executeUpdate();	
			}catch (SQLException e) {
				e.printStackTrace();
			} catch (NullPointerException e){
				e.printStackTrace();
			}finally {
				if (pstmt != null) {
					try {
						pstmt.close();
						endMySqlConnection(conn);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}

		return (result==0)?false:true;
	}

	/*
	 * Get list of solarcells within price limit from DB
	 * @param int (price)
	 * @return ArrayList<solarcells>
	 */
	public static ArrayList<SolarCell> listSolarCells(int priceLimit){
		ArrayList<SolarCell> listSC = new ArrayList<SolarCell>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		if(priceLimit<=0){
			return null;
		}else {
			try{
				conn = getMySqlConnection();
				pstmt = conn.prepareStatement(QUERY_LIST_SOLARCELLS);
				pstmt.setInt(1, priceLimit);
				rs = pstmt.executeQuery();
				while(rs.next()){
					listSC.add(new SolarCell(rs.getString("solarcell_type"),rs.getString("solarcell_type_cd"), rs.getInt("price_per_watt")));
				}
			}catch (SQLException e) {
				e.printStackTrace();
			} catch (NullPointerException e){
				e.printStackTrace();
			}finally {
				if (pstmt != null) {
					try {
						if(rs!=null){
							rs.close();
						}
						pstmt.close();
						endMySqlConnection(conn);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}

		return listSC;
	}

}


