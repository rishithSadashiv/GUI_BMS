/*    */ package in.ac.sit.cs.entity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Book
/*    */ {
/*    */   private String title;
/*    */   private int ISBN;
/*    */   private String catagory;
/*    */   private int numberOfBooks;
/*    */   private Author author;
/*    */   
/*    */   public Book(String title, int iSBN, String catagory, int numberOfBooks, Author author) {
/* 16 */     this.title = title;
/* 17 */     this.ISBN = iSBN;
/* 18 */     this.catagory = catagory;
/* 19 */     this.numberOfBooks = numberOfBooks;
/* 20 */     this.author = author;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 25 */   public int getNumberOfBooks() { return this.numberOfBooks; }
/*    */ 
/*    */ 
/*    */   
/* 29 */   public void setNumberOfBooks(int numberOfBooks) { this.numberOfBooks = numberOfBooks; }
/*    */ 
/*    */ 
/*    */   
/* 33 */   public String getTitle() { return this.title; }
/*    */ 
/*    */ 
/*    */   
/* 37 */   public void setTitle(String title) { this.title = title; }
/*    */ 
/*    */ 
/*    */   
/* 41 */   public int getISBN() { return this.ISBN; }
/*    */ 
/*    */ 
/*    */   
/* 45 */   public void setISBN(int iSBN) { this.ISBN = iSBN; }
/*    */ 
/*    */ 
/*    */   
/* 49 */   public String getCatagory() { return this.catagory; }
/*    */ 
/*    */ 
/*    */   
/* 53 */   public void setCatagory(String catagory) { this.catagory = catagory; }
/*    */ 
/*    */ 
/*    */   
/* 57 */   public Author getAuthor() { return this.author; }
/*    */ 
/*    */ 
/*    */   
/* 61 */   public void setAuthor(Author author) { this.author = author; }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 66 */     return "->Book[title=" + this.title + ", ISBN=" + this.ISBN + ", catagory=" + this.catagory + ", numberOfBooks=" + this.numberOfBooks + ", " + this.author
/* 67 */       .toString() + "]";
/*    */   }
/*    */ }


/* Location:              E:\education\Engineering\Java_training2019\GUI_JAR_files\BookManagementSystem\BMS_GUI\consoleBasedBookManagementSystem.jar!\in\ac\sit\cs\entity\Book.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */