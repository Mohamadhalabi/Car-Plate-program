package packet;



import net.proteanit.sql.DbUtils;

import javax.swing.*;

public class Userpanel {




    Userpanel() throws Exception {
        SQliteAccess sq = new SQliteAccess();
        sq.readDataBase();
        JFrame frame = new JFrame();
        frame.setSize(400,400);
        frame.setLayout(null);
        frame.setVisible(true);
        JTable table = new JTable();
        table.setModel(DbUtils.resultSetToTableModel(sq.resultSet));
        frame.add(table);

    }


}
