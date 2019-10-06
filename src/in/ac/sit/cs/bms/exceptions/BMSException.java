/*    */ package in.ac.sit.cs.bms.exceptions;
/*    */ 
/*    */ 
/*    */ public class BMSException
/*    */   extends Exception
/*    */ {
/*    */   private String details;
/*    */   
/*  9 */   public BMSException(String details) { this.details = details; }
/*    */ 
/*    */ 
/*    */   
/* 13 */   public String getDetails() { return this.details; }
/*    */ }


/* Location:              E:\education\Engineering\Java_training2019\GUI_JAR_files\BookManagementSystem\BMS_GUI\consoleBasedBookManagementSystem.jar!\in\ac\sit\cs\bms\exceptions\BMSException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */