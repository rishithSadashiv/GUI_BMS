/*    */ package in.ac.sit.cs.bms;
/*    */ 
/*    */ import in.ac.sit.cs.bms.db.DBConnectionManager;
/*    */ import java.sql.Connection;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TestDBConn
/*    */ {
/*    */   public static void main(String[] args) {
/* 11 */     Connection conn = DBConnectionManager.getConnection();
/*    */     
/* 13 */     if (conn != null) {
/*    */       
/* 15 */       System.out.println("Connection Success ");
/*    */     }
/*    */     else {
/*    */       
/* 19 */       System.out.println("Connection fail");
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\education\Engineering\Java_training2019\GUI_JAR_files\BookManagementSystem\BMS_GUI\consoleBasedBookManagementSystem.jar!\in\ac\sit\cs\bms\TestDBConn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */