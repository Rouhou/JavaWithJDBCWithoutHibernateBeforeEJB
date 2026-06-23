package org.example.dao;

import org.example.entities.Compte;
import org.example.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompteDaoImpl implements ICompteDao {

    @Override
    public Long save(Compte compte) {

        String sql = "INSERT INTO compte(numeroCompte, solde, dateCreation, clientId) VALUES (?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, compte.getNumeroCompte());
            ps.setDouble(2, compte.getSolde());
            ps.setDate(3, Date.valueOf(compte.getDateCreation()));
            ps.setLong(4, compte.getClientId());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                long id = rs.getLong(1);
                compte.setId(id);
                return (Long)id;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Compte findById(Long id) {

        String sql = "SELECT * FROM compte WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapCompte(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Compte> findAll() {

        List<Compte> comptes = new ArrayList<>();

        String sql = "SELECT * FROM compte";

        try (Connection con = DatabaseConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                comptes.add(mapCompte(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return comptes;
    }

    @Override
    public void update(Compte compte) {

        String sql = "UPDATE compte SET numeroCompte = ?, solde = ?, dateCreation = ?, clientId = ? WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, compte.getNumeroCompte());
            ps.setDouble(2, compte.getSolde());
            ps.setDate(3, Date.valueOf(compte.getDateCreation()));
            ps.setLong(4, compte.getClientId());
            ps.setLong(5, compte.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {

        String sql = "DELETE FROM compte WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Compte mapCompte(ResultSet rs) throws SQLException {

        Compte c = new Compte();

        c.setId(rs.getLong("id"));
        c.setNumeroCompte(rs.getString("numeroCompte"));
        c.setSolde(rs.getDouble("solde"));
        c.setClientId(rs.getLong("clientId"));
        c.setDateCreation(rs.getDate("dateCreation").toLocalDate());

        return c;
    }
}