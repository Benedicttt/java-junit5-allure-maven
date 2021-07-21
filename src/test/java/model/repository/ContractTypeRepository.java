package model.repository;

import helpers.db.Postgresql;
import model.dto.ContractType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContractTypeRepository {
    public Postgresql db;

    public ContractTypeRepository() {
        db = Postgresql.INSTANCE;
    }

    public List<ContractType> findAll() {
        String SQL_QUERY = "select * from contract_type";
        List<ContractType> contractTypes = null;

        try (
            PreparedStatement pst = db.getConnection().prepareStatement(SQL_QUERY);
            ResultSet rs = pst.executeQuery()) {
            contractTypes = new ArrayList<>();
            ContractType contractType;

            while (rs.next()) {
                contractType = new ContractType(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getBigDecimal("vat_rate"),
                        rs.getString("type")
                );
                contractTypes.add(contractType);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return contractTypes;
    }
}
