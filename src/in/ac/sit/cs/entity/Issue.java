/*    */ package in.ac.sit.cs.entity;
/*    */ 
/*    */ public class Issue
/*    */ {
/*    */   private String USN;
/*    */   private String issueDate;
/*    */   private String returnDate;
/*    */   private int ISBN;
/*    */   
/*    */   public Issue(String uSN, String issueDate, String returnDate, int iSBN) {
/* 11 */     this.USN = uSN;
/* 12 */     this.issueDate = issueDate;
/* 13 */     this.returnDate = returnDate;
/* 14 */     this.ISBN = iSBN;
/*    */   }
/*    */   
/* 17 */   public String getUSN() { return this.USN; }
/*    */ 
/*    */   
/* 20 */   public void setUSN(String uSN) { this.USN = uSN; }
/*    */ 
/*    */   
/* 23 */   public String getIssueDate() { return this.issueDate; }
/*    */ 
/*    */   
/* 26 */   public void setIssueDate(String issueDate) { this.issueDate = issueDate; }
/*    */ 
/*    */   
/* 29 */   public String getReturnDate() { return this.returnDate; }
/*    */ 
/*    */   
/* 32 */   public void setReturnDate(String returnDate) { this.returnDate = returnDate; }
/*    */ 
/*    */   
/* 35 */   public int getISBN() { return this.ISBN; }
/*    */ 
/*    */   
/* 38 */   public void setISBN(int iSBN) { this.ISBN = iSBN; }
/*    */ 
/*    */ 
/*    */   
/* 42 */   public String toString() { return "Issue [USN=" + this.USN + ", issueDate=" + this.issueDate + ", returnDate=" + this.returnDate + ", ISBN=" + this.ISBN + "]"; }
/*    */ }


/* Location:              E:\education\Engineering\Java_training2019\GUI_JAR_files\BookManagementSystem\BMS_GUI\consoleBasedBookManagementSystem.jar!\in\ac\sit\cs\entity\Issue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */