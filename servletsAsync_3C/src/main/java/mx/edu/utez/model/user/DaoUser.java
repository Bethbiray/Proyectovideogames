package mx.edu.utez.model.user;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import mx.edu.utez.service.ConnectionMySQL;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoUser {
    private Connection con;
    private CallableStatement cstm;
    private ResultSet rs;

    final private Logger CONSOLE = LoggerFactory.getLogger(DaoUser.class);

            public boolean createSession(String email, String password){
                boolean flag = false;
                try{
                    con = ConnectionMySQL.getConnection();
                    cstm = con.prepareCall( "selec * from user  where  email = ?  and password = ?");
                    cstm.setString(1, email);
                    cstm.setString(2, password);
                    rs = cstm.executeQuery();

                    if(rs.next()) flag = true;




                } catch (SQLException e){
                    CONSOLE.error("Ha ocurrido un error: " + e.getMessage());

                } finally {
                    ConnectionMySQL.closeConnections(con, cstm, rs);

                }
                return flag;

            }


}
