package packet;



import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.IDN;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Userpanel {



    DefaultTableModel model = new DefaultTableModel();
    Object[] Coloms = {"ID","FULL_NAME","CAR_MODEL","CAR_PLATE"};
    Object[] Rows =  new Object[4];


    Userpanel() throws Exception {


       SQliteAccess sql = new SQliteAccess();
       sql.readDataBase();


        JFrame frame = new JFrame();
        frame.setSize(450, 450);
        frame.setVisible(true);


        JTable table = new JTable();
        model.setColumnIdentifiers(Coloms);
        table.setBounds(30, 40, 400, 400);
        JScrollPane sp = new JScrollPane(table);
        frame.add(sp);


        try {
            while (sql.resultSet.next())
            {
                Rows[0] = sql.resultSet.getString("ID");
                Rows[1] = sql.resultSet.getString("Full_Name");
                Rows[2] = sql.resultSet.getString("Car_Model");
                Rows[3] = sql.resultSet.getString("Car_Plate");
                model.addRow(Rows);
            }

        }catch (Exception e){

        }
        table.setModel(model);







    }
}
