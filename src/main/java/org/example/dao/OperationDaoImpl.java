package org.example.dao;

import org.example.entities.Operation;
import org.example.entities.TypeOperation;
import org.example.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OperationDaoImpl implements IOperationDao {

    @Override
    public void save(Operation operation) {

        String sql = "INSERT INTO operation(montant, type, compteId, date_operation) VALUES (?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setDouble(1, operation.getMontant());
            ps.setString(2, operation.getType().name());
            ps.setLong(3, operation.getCompteId());
            ps.setDate(4, Date.valueOf(operation.getDateOperation()));

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Operation findById(Long id) {

        String sql = "SELECT * FROM operation WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapOperation(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Operation> findAll() {

        List<Operation> operations = new ArrayList<>();

        String sql = "SELECT * FROM operation";

        try (Connection con = DatabaseConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                operations.add(mapOperation(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return operations;
    }

    @Override
    public void update(Operation operation) {

        String sql = "UPDATE operation SET montant = ?, type = ?, compteId = ?, date_operation = ? WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, operation.getMontant());
            ps.setString(2, operation.getType().name());
            ps.setLong(3, operation.getCompteId());
            ps.setDate(4, Date.valueOf(operation.getDateOperation()));
            ps.setLong(5, operation.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {

        String sql = "DELETE FROM operation WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Operation mapOperation(ResultSet rs) throws SQLException {

        Operation op = new Operation();

        op.setId(rs.getLong("id"));
        op.setMontant(rs.getDouble("montant"));
        op.setType(TypeOperation.valueOf(rs.getString("type")));
        op.setCompteId(rs.getLong("compteId"));
        op.setDateOperation(rs.getDate("dateOperation").toLocalDate());

        return op;
    }
}