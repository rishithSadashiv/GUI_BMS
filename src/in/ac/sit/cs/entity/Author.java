/*    */ package in.ac.sit.cs.entity;
/*    */ 
/*    */ public class Author
/*    */ {
/*    */   private String authorName;
/*    */   private String phoneNumber;
/*    */   
/*    */   public Author(String authorName, String phoneNumber) {
/*  9 */     this.authorName = authorName;
/* 10 */     this.phoneNumber = phoneNumber;
/*    */   }
/*    */   
/* 13 */   public String getAuthorName() { return this.authorName; }
/*    */ 
/*    */   
/* 16 */   public void setAuthorName(String authorName) { this.authorName = authorName; }
/*    */ 
/*    */   
/* 19 */   public String getPhoneNumber() { return this.phoneNumber; }
/*    */ 
/*    */   
/* 22 */   public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
/*    */ 
/*    */ 
/*    */   
/* 26 */   public String toString() { return "Author [authorName=" + this.authorName + ", Contact detail =" + this.phoneNumber + "]"; }
/*    */ }


/* Location:              E:\education\Engineering\Java_training2019\GUI_JAR_files\BookManagementSystem\BMS_GUI\consoleBasedBookManagementSystem.jar!\in\ac\sit\cs\entity\Author.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */