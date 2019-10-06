/*    */ package in.ac.sit.cs.bms;
/*    */ 
/*    */ import java.awt.CardLayout;
/*    */ import java.awt.Color;
/*    */ import javax.swing.JButton;
/*    */ import javax.swing.JFrame;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.JScrollPane;
/*    */ import javax.swing.JTable;
/*    */ import javax.swing.SwingUtilities;
/*    */ 
/*    */ public class BMS_gui
/*    */   extends JFrame
/*    */ {
/*    */   JPanel mainPanel;
/*    */   JPanel panel1;
/*    */   JPanel panel2;
/*    */   JPanel panel3;
/*    */   JPanel panel4;
/*    */   JPanel panel5;
/*    */   JPanel panel6;
/*    */   JPanel panel7;
/*    */   
/*    */   public BMS_gui() {
/* 25 */     this.mainPanel = new JPanel();
/* 26 */     this.panel1 = new JPanel();
/* 27 */     this.panel2 = new JPanel();
/* 28 */     this.panel3 = new JPanel();
/* 29 */     this.panel4 = new JPanel();
/* 30 */     this.panel5 = new JPanel();
/* 31 */     this.panel6 = new JPanel();
/* 32 */     this.panel7 = new JPanel();
/* 33 */     this.panel8 = new JPanel();
/* 34 */     this.panel9 = new JPanel();
/*    */ 
/*    */     
/* 37 */     this.btnSelect = new JButton("Select");
/*    */     
/* 39 */     this.colNames = new Object[] { "Please select your choice : " };
/* 40 */     this.data = new Object[][] { { "1. Add a book" }, { "2. Search a book based on book title" }, { "3. Search books based on catagory" }, { "4. Search books based on author" }, { "5. List all books along with author information" }, { "6. Issue book to a student" }, { "7. List books issued to student based on USN number" }, { "8. List books which are to be returned for current date" }, { "9. Exit" } };
/*    */     
/* 42 */     this.cl = new CardLayout();
/*    */ 
/*    */     
/* 45 */     setTitle("Book Management System");
/* 46 */     setSize(600, 600);
/* 47 */     setLocationRelativeTo(null);
/* 48 */     setDefaultCloseOperation(3);
/*    */     
/* 50 */     this.table = new JTable(this.data, this.colNames);
/* 51 */     this.scrolPane = new JScrollPane(this.table);
/* 52 */     this.mainPanel.setLayout(this.cl);
/*    */ 
/*    */     
/* 55 */     this.panel1.add(this.scrolPane);
/* 56 */     this.panel1.add(this.btnSelect);
/* 57 */     this.panel1.setBackground(Color.red);
/*    */     
/* 59 */     this.mainPanel.add(this.panel1, "1");
/* 60 */     this.mainPanel.add(this.panel2, "2");
/* 61 */     this.mainPanel.add(this.panel3, "3");
/* 62 */     this.mainPanel.add(this.panel4, "4");
/* 63 */     this.mainPanel.add(this.panel5, "5");
/* 64 */     this.mainPanel.add(this.panel6, "6");
/* 65 */     this.mainPanel.add(this.panel7, "7");
/* 66 */     this.mainPanel.add(this.panel8, "8");
/* 67 */     this.mainPanel.add(this.panel9, "9");
/*    */     
/* 69 */     this.cl.show(this.mainPanel, "1");
/*    */     
/* 71 */     add(this.mainPanel);
/*    */   }
/*    */   JPanel panel8; JPanel panel9; JScrollPane scrolPane; JTable table; JButton btnSelect; Object[] colNames;
/*    */   Object[][] data;
/*    */   CardLayout cl;
/*    */   
/*    */   public static void main(String[] args) {
/* 78 */     SwingUtilities.invokeLater(new Runnable()
/*    */         {
/* 80 */           public void run() { (new BMS_gui()).setVisible(true); }
/*    */         });
/*    */   }
/*    */ }


/* Location:              E:\education\Engineering\Java_training2019\GUI_JAR_files\BookManagementSystem\BMS_GUI\consoleBasedBookManagementSystem.jar!\in\ac\sit\cs\bms\BMS_gui.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */