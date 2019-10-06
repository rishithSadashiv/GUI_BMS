/*    */ package in.ac.sit.cs.bms.db;
/*    */ 
/*    */ import java.sql.Connection;
/*    */ import java.sql.DriverManager;
/*    */ import java.sql.SQLException;
/*    */ 
/*    */ 
/*    */ public class DBConnectionManager
/*    */ {
/*    */   static  {
/*    */     try {
/* 12 */       Class.forName("com.mysql.jdbc.Driver");
/* 13 */     } catch (ClassNotFoundException e) {
/* 14 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static Connection getConnection() {
/* 20 */     conn = null;
/*    */     try {
/* 22 */       conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bms", "root", "Rishith7!");
/* 23 */     } catch (SQLException e) {
/* 24 */       e.printStackTrace();
/*    */     } 
/* 26 */     return conn;
/*    */   }
/*    */ }


/* Location:              E:\education\Engineering\Java_training2019\GUI_JAR_files\BookManagementSystem\BMS_GUI\consoleBasedBookManagementSystem.jar!\in\ac\sit\cs\bms\db\DBConnectionManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */