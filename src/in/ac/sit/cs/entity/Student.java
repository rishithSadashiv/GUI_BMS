/*    */ package in.ac.sit.cs.entity;
/*    */ 
/*    */ 
/*    */ public class Student
/*    */ {
/*    */   private String name;
/*    */   private String USN;
/*    */   
/*    */   public Student(String name, String uSN) {
/* 10 */     this.name = name;
/* 11 */     this.USN = uSN;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 16 */   public String getName() { return this.name; }
/*    */ 
/*    */ 
/*    */   
/* 20 */   public void setName(String name) { this.name = name; }
/*    */ 
/*    */ 
/*    */   
/* 24 */   public String getUSN() { return this.USN; }
/*    */ 
/*    */ 
/*    */   
/* 28 */   public void setUSN(String uSN) { this.USN = uSN; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 33 */   public String toString() { return "Student [name=" + this.name + ", USN=" + this.USN + "]"; }
/*    */ }


/* Location:              E:\education\Engineering\Java_training2019\GUI_JAR_files\BookManagementSystem\BMS_GUI\consoleBasedBookManagementSystem.jar!\in\ac\sit\cs\entity\Student.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */