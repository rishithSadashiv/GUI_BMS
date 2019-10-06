/*     */ package in.ac.sit.cs.bms.db;
/*     */ 
/*     */ import in.ac.sit.cs.entity.Author;
/*     */ import in.ac.sit.cs.entity.Book;
/*     */ import in.ac.sit.cs.entity.Student;
/*     */ import java.sql.Connection;
/*     */ import java.sql.Date;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.text.DateFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Vector;
/*     */ import javax.swing.table.DefaultTableModel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IssueDAO
/*     */ {
/*     */   public boolean booksToBeReturned(Date date) {
/*  26 */     String sql = "select s.studentName,s.studentUSN,b.bookISBN,b.bookTitle,a.authorName from book b,issue i,student s,author a where b.bookISBN = i.bookISBN and s.studentUSN = i.studentUSN and a.bookISBN=b.bookISBN and i.returnDate=?";
/*  27 */     boolean flag = false;
/*     */     
/*  29 */     Date rDate = new Date(date.getTime());
/*     */ 
/*     */ 
/*     */     
/*  33 */     try (Connection conn = DBConnectionManager.getConnection()) {
/*     */       
/*  35 */       ps = conn.prepareStatement(sql);
/*  36 */       ps.setDate(1, rDate);
/*     */       
/*  38 */       ResultSet rs = ps.executeQuery();
/*  39 */       while (rs != null && rs.next())
/*     */       {
/*  41 */         System.out.println("Student name : " + rs.getString(1) + "\tUSN : " + rs.getString(2) + "\tBook ISBN : " + rs.getInt(3) + "\tTitle : " + rs.getString(4) + "\tAuthor name : " + rs.getString(5));
/*  42 */         flag = true;
/*     */       }
/*     */     
/*     */     }
/*  46 */     catch (SQLException e) {
/*     */       
/*  48 */       e.printStackTrace();
/*     */     } 
/*  50 */     if (!flag)
/*     */     {
/*  52 */       System.out.println("There are no books to be returned on this day");
/*     */     }
/*     */     
/*  55 */     return flag;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean listStudentBooks(String USN) {
/*  61 */     String sql = "select b.bookTitle,s.studentName,i.returnDate from book b, issue i, student s where b.bookISBN = i.bookISBN and s.studentUSN = i.studentUSN and s.studentUSN RLIKE(?)";
/*  62 */     int rows = 0;
/*     */     
/*  64 */     try (Connection conn = DBConnectionManager.getConnection()) {
/*  65 */       ps = conn.prepareStatement(sql);
/*  66 */       ps.setString(1, USN);
/*  67 */       ResultSet rs = ps.executeQuery();
/*  68 */       while (rs != null && rs.next())
/*     */       {
/*  70 */         System.out.println("Book Title :" + rs.getString(1) + "\tStudent Name : " + rs.getString(2) + "\tReturn Date : " + rs.getDate(3));
/*     */       }
/*  72 */     } catch (Exception e) {
/*  73 */       e.printStackTrace();
/*     */     } 
/*     */     
/*  76 */     return (rows > 0);
/*     */   }
/*     */   
/*     */   public ArrayList issueBook(Student student, Date issueDate, int ISBN) {
/*  80 */     String sql0 = "select studentUSN from Student where studentUSN = ?";
/*  81 */     String sql1 = "insert into student values(?,?)";
/*  82 */     String sql2 = "insert into Issue values(default,?,?,?,?)";
/*  83 */     String sql3 = "update Book set noOfBooks=noOfBooks-? where bookISBN=?";
/*     */     
/*  85 */     int rows1 = 0, rows2 = 0, rows3 = 0;
/*  86 */     int issueID = 0;
/*  87 */     ArrayList aList = new ArrayList();
/*  88 */     int oneBook = 1;
/*  89 */     String USN = null;
/*     */     
/*  91 */     Calendar c = Calendar.getInstance();
/*  92 */     c.setTime(issueDate);
/*  93 */     c.add(5, 7);
/*  94 */     Date returnDate = c.getTime();
/*  95 */     Date rDate = new Date(returnDate.getTime());
/*  96 */     Date iDate = new Date(issueDate.getTime());
/*  97 */     DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
/*     */     
/*  99 */     try (Connection conn = DBConnectionManager.getConnection()) {
/*     */       
/* 101 */       PreparedStatement ps3 = conn.prepareStatement(sql3, 1);
/* 102 */       ps3.setInt(1, 1);
/* 103 */       ps3.setInt(2, ISBN);
/* 104 */       rows3 = ps3.executeUpdate();
/*     */       
/* 106 */       PreparedStatement ps0 = conn.prepareStatement(sql0);
/* 107 */       ps0.setString(1, student.getUSN());
/* 108 */       ResultSet rs = ps0.executeQuery();
/* 109 */       if (rs != null && rs.next())
/*     */       {
/* 111 */         USN = rs.getString(1);
/*     */       }
/* 113 */       if (USN != null && USN.equals(student.getUSN())) {
/*     */         
/* 115 */         PreparedStatement ps2 = conn.prepareStatement(sql2);
/* 116 */         ps2.setString(1, student.getUSN());
/* 117 */         ps2.setDate(2, iDate);
/* 118 */         ps2.setDate(3, rDate);
/* 119 */         ps2.setInt(4, ISBN);
/* 120 */         rows2 = ps2.executeUpdate();
/*     */         
/* 122 */         ResultSet rs1 = ps2.getGeneratedKeys();
/* 123 */         rs1.next();
/* 124 */         issueID = rs1.getInt(1);
/* 125 */         aList.add(rs1.getString(1));
/* 126 */         aList.add(df.format(returnDate));
/* 127 */         return aList;
/*     */       } 
/*     */ 
/*     */       
/* 131 */       PreparedStatement ps1 = conn.prepareStatement(sql1);
/* 132 */       ps1.setString(1, student.getUSN());
/* 133 */       ps1.setString(2, student.getName());
/* 134 */       rows1 = ps1.executeUpdate();
/*     */       
/* 136 */       PreparedStatement ps2 = conn.prepareStatement(sql2);
/* 137 */       ps2.setString(1, student.getUSN());
/* 138 */       ps2.setDate(2, iDate);
/* 139 */       ps2.setDate(3, rDate);
/* 140 */       ps2.setInt(4, ISBN);
/* 141 */       rows2 = ps2.executeUpdate();
/* 142 */       ResultSet rs1 = ps2.getGeneratedKeys();
/* 143 */       rs1.next();
/* 144 */       issueID = rs1.getInt(1);
/* 145 */       aList.add(rs1.getString(1));
/* 146 */       aList.add(df.format(returnDate));
/* 147 */       return aList;
/*     */     }
/* 149 */     catch (SQLException e) {
/*     */       
/* 151 */       e.printStackTrace();
/*     */       
/* 153 */       return aList;
/*     */     } 
/*     */   }
/*     */   public int bookAvailability(int ISBN) {
/* 157 */     String sql = "select noOfBooks from book where bookISBN=?";
/* 158 */     int number = 0;
/*     */     
/* 160 */     try (Connection conn = DBConnectionManager.getConnection()) {
/* 161 */       ps = conn.prepareStatement(sql);
/* 162 */       ps.setInt(1, ISBN);
/* 163 */       ResultSet rs = ps.executeQuery();
/* 164 */       if (rs != null && rs.next())
/*     */       {
/* 166 */         number = rs.getInt(1);
/*     */       }
/*     */     }
/* 169 */     catch (SQLException e) {
/* 170 */       e.printStackTrace();
/*     */     } 
/* 172 */     if (number > 0)
/*     */     {
/* 174 */       return 1;
/*     */     }
/*     */     
/* 177 */     return number;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Book> displayAllRecordedBooks() {
/* 185 */     List<Book> bookList = new ArrayList<Book>();
/* 186 */     String sql = "select b.bookTitle,b.bookISBN,b.bookCatagory,b.noOfBooks,a.authorName,a.authorMailId from book b, author a where b.bookISBN = a.bookISBN ";
/* 187 */     try (Connection conn = DBConnectionManager.getConnection()) {
/* 188 */       ps = conn.prepareStatement(sql);
/* 189 */       ResultSet rs = ps.executeQuery();
/* 190 */       while (rs != null && rs.next()) {
/* 191 */         Author temp0 = new Author(rs.getString(5), rs.getString(6));
/* 192 */         Book temp = new Book(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getInt(4), temp0);
/* 193 */         bookList.add(temp);
/*     */       } 
/* 195 */     } catch (SQLException e) {
/* 196 */       e.printStackTrace();
/*     */     } 
/* 198 */     if (bookList.isEmpty()) {
/* 199 */       System.out.println("No books found");
/*     */     }
/* 201 */     return bookList;
/*     */   }
/*     */ 
/*     */   
/*     */   public DefaultTableModel guiGetStudentIssues(String USN) {
/* 206 */     String sql = "select b.bookTitle,s.studentName,i.returnDate from book b, issue i, student s where b.bookISBN = i.bookISBN and s.studentUSN = i.studentUSN and s.studentUSN RLIKE(?)";
/* 207 */     Vector<String> colNames = new Vector<String>();
/* 208 */     colNames.add("Book Title");
/* 209 */     colNames.add("Student Name");
/* 210 */     colNames.add("Return date");
/*     */     
/* 212 */     Vector<Vector<String>> data = new Vector<Vector<String>>();
/* 213 */     Vector<String> row = null;
/*     */     
/* 215 */     try (Connection conn = DBConnectionManager.getConnection()) {
/* 216 */       ps = conn.prepareStatement(sql);
/* 217 */       ps.setString(1, USN);
/* 218 */       ResultSet rs = ps.executeQuery();
/*     */       
/* 220 */       while (rs != null && rs.next())
/*     */       {
/* 222 */         row = new Vector<String>();
/* 223 */         row.add(rs.getString(1));
/* 224 */         row.add(rs.getString(2));
/* 225 */         row.add(rs.getString(3));
/* 226 */         data.add(row);
/*     */       }
/*     */     
/* 229 */     } catch (SQLException e) {
/* 230 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 233 */     return new DefaultTableModel(data, colNames);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public DefaultTableModel guiBooksToBeReturned(Date date) {
/* 239 */     String sql = "select s.studentName,s.studentUSN,b.bookISBN,b.bookTitle,a.authorName from book b,issue i,student s,author a where b.bookISBN = i.bookISBN and s.studentUSN = i.studentUSN and a.bookISBN=b.bookISBN and i.returnDate=?";
/*     */     
/* 241 */     Date rDate = new Date(date.getTime());
/*     */     
/* 243 */     Vector<String> colNames = new Vector<String>();
/* 244 */     colNames.add("Student Name");
/* 245 */     colNames.add("Student USN");
/* 246 */     colNames.add("Book ISBN");
/* 247 */     colNames.add("Book Title");
/* 248 */     colNames.add("Author Name");
/*     */     
/* 250 */     Vector<Vector<String>> data = new Vector<Vector<String>>();
/* 251 */     Vector<String> row = null;
/*     */     
/* 253 */     try (Connection conn = DBConnectionManager.getConnection()) {
/* 254 */       ps = conn.prepareStatement(sql);
/* 255 */       ps.setDate(1, rDate);
/* 256 */       ResultSet rs = ps.executeQuery();
/*     */       
/* 258 */       while (rs != null && rs.next()) {
/*     */         
/* 260 */         row = new Vector<String>();
/* 261 */         row.add(rs.getString(1));
/* 262 */         row.add(rs.getString(2));
/* 263 */         row.add(String.valueOf(rs.getInt(3)));
/* 264 */         row.add(rs.getString(4));
/* 265 */         row.add(rs.getString(5));
/* 266 */         data.add(row);
/*     */       } 
/* 268 */     } catch (SQLException e) {
/* 269 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 272 */     return new DefaultTableModel(data, colNames);
/*     */   }
/*     */ 
/*     */   
/*     */   public DefaultTableModel listStudents(String USN) {
/* 277 */     String sql = "select studentName,studentUSN from student where studentUSN RLIKE(?)";
/* 278 */     Vector<String> colNames = new Vector<String>();
/* 279 */     colNames.add("Student Name");
/* 280 */     colNames.add("Student USN");
/*     */     
/* 282 */     Vector<Vector<String>> data = new Vector<Vector<String>>();
/* 283 */     Vector<String> row = null;
/*     */     
/* 285 */     try (Connection conn = DBConnectionManager.getConnection()) {
/* 286 */       ps = conn.prepareStatement(sql);
/* 287 */       ps.setString(1, USN);
/* 288 */       ResultSet rs = ps.executeQuery();
/*     */       
/* 290 */       while (rs != null && rs.next())
/*     */       {
/* 292 */         row = new Vector<String>();
/* 293 */         row.add(rs.getString(1));
/* 294 */         row.add(rs.getString(2));
/* 295 */         data.add(row);
/*     */       }
/*     */     
/* 298 */     } catch (SQLException e) {
/* 299 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 302 */     return new DefaultTableModel(data, colNames);
/*     */   }
/*     */ }


/* Location:              E:\education\Engineering\Java_training2019\GUI_JAR_files\BookManagementSystem\BMS_GUI\consoleBasedBookManagementSystem.jar!\in\ac\sit\cs\bms\db\IssueDAO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */