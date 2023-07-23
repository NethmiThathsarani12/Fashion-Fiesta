package lk.ijse.fashionfiesta.dao.custom.impl.utill;
import lk.ijse.fashionfiesta.db.DBConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLUtill {
    public static <T>T crudUtil(String sql,Object...arg) throws SQLException, ClassNotFoundException {

        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);

        for (int i = 0; i < arg.length; i++) {
            statement.setObject((i+1),arg[i]);
        }
        if (sql.startsWith("SELECT")){
            return (T) statement.executeQuery();
        }else {
            return(T)(Boolean)(statement.executeUpdate()>0);
        }
    }
}


