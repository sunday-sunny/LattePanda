package com.test.main.menu.basic;

import com.test.main.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class StockDAO {

    private Connection conn;
    private Statement stat;
    private PreparedStatement pstat;
    private ResultSet rs;

    public StockDAO() {

        try {
            conn = DBUtil.open();
            stat = conn.createStatement();
        } catch (Exception e) {
            System.out.println("StockDAO.StockDAO");
            e.printStackTrace();
        }
    }

    /* AddProduct.java > 재료 리스트 반환 */
    public ArrayList<StockDTO> list() {

        try {
            String sql = "select * from tblStock";
            rs = stat.executeQuery(sql);

            ArrayList<StockDTO> list = new ArrayList<StockDTO>();
            while(rs.next()) {
                StockDTO dto = new StockDTO();
                dto.setSeqStock(rs.getString("seq_stock"));
                dto.setName(rs.getString("name"));
                dto.setTypes(rs.getString("types"));
                dto.setUnit(rs.getString("unit"));
                dto.setUnitCost(rs.getString("unit_cost"));
                dto.setOrderUnit(rs.getString("order_unit"));
                dto.setOrderCost(rs.getString("order_cost"));
                dto.setOrderUnitQuantity(rs.getString("order_unit_quantity"));

                list.add(dto);
            }

            conn.close();
            return list;

        }catch (Exception e){
            System.out.println("StockDAO.list");
            e.printStackTrace();
        }

        return null;
    }
}
