package me.homeplguin.Sql;

import me.homeplguin.Utlis;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.sql.*;
import java.util.UUID;

public class Database {
    static Connection connection;

    public void checkConnection(){
        try {
            openConnection();
            Statement statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addPlayer(UUID uuid){
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `" + uuid + "`(" +
                    "`HomeName` varchar(32) NOT NULL," +
                    "`x` int(11) NOT NULL," +
                    "`y` int(11) NOT NULL," +
                    "`z` int(11) NOT NULL," +
                    "`World` varchar(32) NOT NULL," +
                    "PRIMARY KEY (`HomeName`));");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public Location getHome(UUID uuid,String name){
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement("SELECT * FROM `" + uuid.toString() + "` WHERE HomeName = '" + name + "';");
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("HomeName").equalsIgnoreCase(name.toLowerCase())) {
                    return new Location(Bukkit.getWorld(rs.getString("World")),rs.getInt("x"),rs.getInt("y"),rs.getInt("z"));
                }
            }
        }catch(SQLException e){
                e.printStackTrace();
            }
        return null;
    }

    public void addHome(UUID uuid, Location loc,String name){
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("REPLACE INTO `" + uuid.toString() + "` (`HomeName`,`x`,`y`,`z`,`World`) VALUES (?,?,?,?,?);");
            ps.setString(1,name);
            ps.setInt(2, ((int) loc.getX()));
            ps.setInt(3, ((int) loc.getY()));
            ps.setInt(4, ((int) loc.getZ()));
            ps.setString(5,loc.getWorld().getName());
            ps.executeUpdate();
            return;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if (ps != null)
                    ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public boolean isPlayerInDb(UUID uuid) {
        try {
            DatabaseMetaData dbm = connection.getMetaData();
            ResultSet tables = dbm.getTables(null, null, uuid.toString(), null);
            if (tables.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public void openConnection() throws SQLException,
            ClassNotFoundException {
        if (connection != null && !connection.isClosed()) {
            return;
        }
        Class.forName("org.mariadb.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://"
                        + Utlis.GetHostName()+ ":" + Utlis.GetPort() + "/" + Utlis.GetDatabase(),
                Utlis.GetUsername(), Utlis.GetPassword());
    }
}
