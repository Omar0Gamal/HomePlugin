package me.homeplguin.Sql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import me.homeplguin.Main;
import me.homeplguin.Utlis;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.*;
import java.util.UUID;

public class DataBaseHikrioCp {

    private HikariDataSource hikari;
    private HikariConfig config;

    public void connectToDatabase() {

        String address = Utlis.GetHostName();
        String name = Utlis.GetDatabase();
        String username = Utlis.GetUsername();
        String password = Utlis.GetPassword();
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.addDataSourceProperty("useServerPrepStmts", "true");
        hikari = new HikariDataSource(config);

        hikari.setMaximumPoolSize(10);
        hikari.setDataSourceClassName("org.mariadb.jdbc.MariaDbDataSource");
        hikari.addDataSourceProperty("serverName", address);
        hikari.addDataSourceProperty("port", "3306");
        hikari.addDataSourceProperty("databaseName", name);
        hikari.addDataSourceProperty("user", username);
        hikari.addDataSourceProperty("password", password);
    }

    public void CloseConnection(){
        hikari.close();
    }

    public Location getHome(UUID uuid, String name) {
        PreparedStatement p = null;
        ResultSet rs = null;
        Connection c = null;
        String update = "SELECT * FROM `" + uuid.toString() + "` WHERE HomeName = '" + name + "';";
        try {
            c = hikari.getConnection();
            p = c.prepareStatement(update);
            rs = p.executeQuery();
            while (rs.next()) {
                if (rs.getString("HomeName").equalsIgnoreCase(name.toLowerCase())) {
                    return new Location(Bukkit.getWorld(rs.getString("World")), rs.getDouble("x"), rs.getDouble("y"), rs.getDouble("z"), rs.getFloat("yaw"), rs.getFloat("pitch"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (p != null) {
                try {
                    p.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

    public boolean isPlayerInDb(UUID uuid) {
        Connection c = null;
        DatabaseMetaData dbm = null;
        try {
            c = hikari.getConnection();
            dbm = c.getMetaData();
            ResultSet tables = dbm.getTables(null, null, uuid.toString(), null);
            if (tables.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return false;
    }

    public final void AddPlayer(UUID uuid){
        new BukkitRunnable() {
            @Override
            public void run() {

                Connection c = null;
                Statement s = null;
                try {
                    c = hikari.getConnection();
                    s = c.createStatement();
                    s.executeUpdate("CREATE TABLE IF NOT EXISTS `" + uuid + "`(" +
                            "`HomeName` varchar(32) NOT NULL," +
                            "`x` DECIMAL(10,2)  NOT NULL," +
                            "`y` DECIMAL(10,2)  NOT NULL," +
                            "`z` DECIMAL(10,2)  NOT NULL," +
                            "`yaw` FLOAT  NOT NULL," +
                            "`pitch`FLOAT  NOT NULL," +
                            "`World` varchar(32) NOT NULL," +
                            "PRIMARY KEY (`HomeName`));");
                }  catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    if (c != null) {
                        try {
                            c.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }if (s != null) {
                        try {
                            s.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.runTaskAsynchronously(Main.getInstance());
    }

    public final void AddHome(UUID uuid, Location loc, String name) {

        new BukkitRunnable() {
            @Override
            public void run() {

                Connection c = null;
                PreparedStatement p = null;
                String update = "REPLACE INTO `" + uuid.toString() + "` (`HomeName`,`x`,`y`,`z`,`yaw`,`pitch`,`World`) VALUES (?,?,?,?,?,?,?);";

                try {
                    c = hikari.getConnection();
                    p = c.prepareStatement(update);
                    p.setString(1, name);
                    p.setDouble(2, loc.getX());
                    p.setDouble(3, loc.getY());
                    p.setDouble(4, loc.getZ());
                    p.setFloat(5, loc.getYaw());
                    p.setFloat(6, loc.getPitch());
                    p.setString(7, loc.getWorld().getName());
                    p.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    if (c != null) {
                        try {
                            c.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.runTaskAsynchronously(Main.getInstance());
    }
}


