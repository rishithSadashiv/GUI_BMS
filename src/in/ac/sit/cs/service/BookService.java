/*    */ package in.ac.sit.cs.service;
/*    */ 
/*    */ import in.ac.sit.cs.bms.db.BookDAO;
/*    */ import in.ac.sit.cs.bms.db.IssueDAO;
/*    */ import in.ac.sit.cs.bms.exceptions.BMSException;
/*    */ import in.ac.sit.cs.entity.Book;
/*    */ import in.ac.sit.cs.entity.Student;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Date;
/*    */ import javax.swing.JComboBox;
/*    */ import javax.swing.table.DefaultTableModel;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BookService
/*    */ {
/*    */   public static boolean addBook(Book book) throws BMSException {
/* 26 */     BookDAO dao = new BookDAO();
/* 27 */     return dao.addBook(book);
/*    */   }
/*    */ 
/*    */   
/*    */   public static DefaultTableModel getTableData(String title, int decision) {
/* 32 */     BookDAO dao = new BookDAO();
/* 33 */     return dao.getTableData(title, decision);
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean bookAvailability(int ISBN) {
/* 38 */     IssueDAO dao = new IssueDAO();
/* 39 */     boolean nope = false;
/* 40 */     if (dao.bookAvailability(ISBN) > 0)
/*    */     {
/* 42 */       return true;
/*    */     }
/*    */ 
/*    */     
/* 46 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static ArrayList issueBook(Student student, Date iDate, int selectISBN) {
/* 52 */     IssueDAO dao = new IssueDAO();
/* 53 */     return dao.issueBook(student, iDate, selectISBN);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static DefaultTableModel guiGetStudentIssues(String USN) {
/* 59 */     IssueDAO dao = new IssueDAO();
/* 60 */     return dao.guiGetStudentIssues(USN);
/*    */   }
/*    */ 
/*    */   
/*    */   public static DefaultTableModel guiBooksToBeReturned(Date date) {
/* 65 */     IssueDAO dao = new IssueDAO();
/* 66 */     return dao.guiBooksToBeReturned(date);
/*    */   }
/*    */ 
/*    */   
/*    */   public static DefaultTableModel listStudents(String USN) {
/* 71 */     IssueDAO dao = new IssueDAO();
/* 72 */     return dao.listStudents(USN);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void updateComboBox(JComboBox combo) {
/* 77 */     BookDAO dao = new BookDAO();
/* 78 */     dao.updateComboBox(combo);
/*    */   }
/*    */ }


/* Location:              E:\education\Engineering\Java_training2019\GUI_JAR_files\BookManagementSystem\BMS_GUI\consoleBasedBookManagementSystem.jar!\in\ac\sit\cs\service\BookService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */