/*     */ package in.ac.sit.cs.bms.db;
/*     */ 
/*     */ import in.ac.sit.cs.bms.exceptions.BMSException;
/*     */ import in.ac.sit.cs.entity.Author;
/*     */ import in.ac.sit.cs.entity.Book;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Vector;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.table.DefaultTableModel;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BookDAO
/*     */ {
/*     */   public boolean addBook(Book book) throws BMSException {
/*  21 */     String sql0 = "select bookISBN,bookTitle from book where bookISBN = ?";
/*  22 */     String sql1 = "insert into book values(?,?,?,?)";
/*  23 */     String sql2 = "insert into author values(?,?,?)";
/*  24 */     String sql3 = "update book set noOfBooks=noOfBooks+? where bookISBN=?";
/*     */     
/*  26 */     int rows1 = 0, rows2 = 0, rows0 = 0;
/*  27 */     int isbn = 0;
/*  28 */     String title = null;
/*  29 */     try (Connection conn = DBConnectionManager.getConnection()) {
/*     */       
/*  31 */       ps0 = conn.prepareStatement(sql0);
/*  32 */       ps0.setInt(1, book.getISBN());
/*  33 */       ResultSet rs = ps0.executeQuery();
/*  34 */       if (rs != null && rs.next()) {
/*     */         
/*  36 */         isbn = rs.getInt(1);
/*  37 */         title = rs.getString(2);
/*     */       } 
/*     */       
/*  40 */       if (book.getISBN() == isbn && book.getTitle().equals(title)) {
/*     */         
/*  42 */         PreparedStatement ps3 = conn.prepareStatement(sql3);
/*  43 */         ps3.setInt(1, book.getNumberOfBooks());
/*  44 */         ps3.setInt(2, book.getISBN());
/*  45 */         rows0 = ps3.executeUpdate();
/*  46 */         return (rows0 > 0);
/*     */       } 
/*  48 */       if (book.getISBN() == isbn && !book.getTitle().equals(title))
/*     */       {
/*  50 */         throw new BMSException("A book with the ISBN " + book.getISBN() + " already exists !\nPlease check your data");
/*     */       }
/*     */ 
/*     */       
/*  54 */       PreparedStatement ps1 = conn.prepareStatement(sql1);
/*  55 */       ps1.setInt(1, book.getISBN());
/*  56 */       ps1.setString(2, book.getTitle());
/*  57 */       ps1.setString(3, book.getCatagory());
/*  58 */       ps1.setInt(4, book.getNumberOfBooks());
/*  59 */       rows1 = ps1.executeUpdate();
/*     */       
/*  61 */       PreparedStatement ps2 = conn.prepareStatement(sql2);
/*  62 */       ps2.setString(1, book.getAuthor().getAuthorName());
/*  63 */       ps2.setString(2, book.getAuthor().getPhoneNumber());
/*  64 */       ps2.setInt(3, book.getISBN());
/*  65 */       rows2 = ps2.executeUpdate();
/*     */     
/*     */     }
/*  68 */     catch (SQLException e) {
/*  69 */       e.printStackTrace();
/*     */     } 
/*     */     
/*  72 */     return ((rows1 > 0 && rows2 > 0) || rows0 > 0);
/*     */   }
/*     */   
/*     */   public List<Book> searchUsingTitle(String title, int decision) {
/*  76 */     String sql = null;
/*  77 */     int zeroNumber = 0;
/*  78 */     if (decision == 1) {
/*  79 */       sql = "select b.bookTitle,b.bookISBN,b.bookCatagory,b.noOfBooks,a.authorName,a.authorMailId from book b, author a where b.bookISBN = a.bookISBN and b.bookTitle RLIKE(?) and b.noOfBooks!=0";
/*     */     }
/*  81 */     else if (decision == 2) {
/*  82 */       sql = "select b.bookTitle,b.bookISBN,b.bookCatagory,b.noOfBooks,a.authorName,a.authorMailId from book b, author a where b.bookISBN = a.bookISBN and b.bookCatagory RLIKE(?) and b.noOfBooks!=0";
/*     */     }
/*  84 */     else if (decision == 3) {
/*  85 */       sql = "select b.bookTitle,b.bookISBN,b.bookCatagory,b.noOfBooks,a.authorName,a.authorMailId from book b, author a where b.bookISBN = a.bookISBN and a.authorName RLIKE(?) and b.noOfBooks!=0";
/*     */     }
/*  87 */     else if (decision == 4) {
/*  88 */       sql = "select b.bookTitle,b.bookISBN,b.bookCatagory,b.noOfBooks,a.authorName,a.authorMailId from book b, author a where b.bookISBN = a.bookISBN and b.noOfBooks!=? ";
/*     */     } 
/*  90 */     List<Book> bookList = new ArrayList<Book>();
/*  91 */     Book temp = null;
/*  92 */     Author temp0 = null;
/*     */     
/*  94 */     try (Connection conn = DBConnectionManager.getConnection()) {
/*  95 */       ps = conn.prepareStatement(sql);
/*  96 */       if (decision != 4) {
/*  97 */         ps.setString(1, title);
/*     */       } else {
/*  99 */         ps.setInt(1, zeroNumber);
/*     */       } 
/* 101 */       ResultSet rs = ps.executeQuery();
/* 102 */       while (rs != null && rs.next()) {
/* 103 */         temp0 = new Author(rs.getString(5), rs.getString(6));
/* 104 */         temp = new Book(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getInt(4), temp0);
/* 105 */         bookList.add(temp);
/*     */       } 
/* 107 */     } catch (SQLException e) {
/* 108 */       e.printStackTrace();
/*     */     } 
/* 110 */     if (bookList.isEmpty()) {
/* 111 */       System.out.println("No books found");
/*     */     }
/* 113 */     return bookList;
/*     */   }
/*     */   
/*     */   public DefaultTableModel getTableData(String title, int decision) {
/* 117 */     String sql = null;
/* 118 */     int zeroNumber = 0;
/* 119 */     switch (decision) {
/*     */       
/*     */       case 1:
/* 122 */         sql = "select b.bookTitle,b.bookISBN,b.bookCatagory,b.noOfBooks,a.authorName,a.authorMailId from book b, author a where b.bookISBN = a.bookISBN and b.bookTitle RLIKE(?) and b.noOfBooks!=0";
/*     */         break;
/*     */       case 2:
/* 125 */         sql = "select b.bookTitle,b.bookISBN,b.bookCatagory,b.noOfBooks,a.authorName,a.authorMailId from book b, author a where b.bookISBN = a.bookISBN and b.bookCatagory RLIKE(?) and b.noOfBooks!=0";
/*     */         break;
/*     */       case 3:
/* 128 */         sql = "select b.bookTitle,b.bookISBN,b.bookCatagory,b.noOfBooks,a.authorName,a.authorMailId from book b, author a where b.bookISBN = a.bookISBN and a.authorName RLIKE(?) and b.noOfBooks!=0";
/*     */         break;
/*     */       case 4:
/* 131 */         sql = "select b.bookTitle,b.bookISBN,b.bookCatagory,b.noOfBooks,a.authorName,a.authorMailId from book b, author a where b.bookISBN = a.bookISBN and b.noOfBooks!=? ";
/*     */         break;
/*     */       default:
/* 134 */         System.out.println("default");
/*     */         break;
/*     */     } 
/* 137 */     Vector<String> colNames = new Vector<String>();
/* 138 */     colNames.add("Book Title");
/* 139 */     colNames.add("Book ISBN");
/* 140 */     colNames.add("Book Catagory");
/* 141 */     colNames.add("Number of books");
/* 142 */     colNames.add("Author Name");
/* 143 */     colNames.add("Author Contact");
/*     */     
/* 145 */     Vector<Vector<String>> data = new Vector<Vector<String>>();
/* 146 */     Vector<String> row = null;
/*     */     
/* 148 */     try (Connection conn = DBConnectionManager.getConnection()) {
/* 149 */       ps = conn.prepareStatement(sql);
/* 150 */       if (decision != 4) {
/* 151 */         ps.setString(1, title);
/*     */       } else {
/* 153 */         ps.setInt(1, zeroNumber);
/*     */       } 
/* 155 */       ResultSet rs = ps.executeQuery();
/*     */       
/* 157 */       while (rs != null && rs.next())
/*     */       {
/* 159 */         row = new Vector<String>();
/* 160 */         row.add(rs.getString(1));
/* 161 */         row.add(String.valueOf(rs.getInt(2)));
/* 162 */         row.add(rs.getString(3));
/* 163 */         row.add(String.valueOf(rs.getInt(4)));
/* 164 */         row.add(rs.getString(5));
/* 165 */         row.add(rs.getString(6));
/* 166 */         data.add(row);
/*     */       }
/*     */     
/* 169 */     } catch (SQLException e) {
/* 170 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 173 */     return new DefaultTableModel(data, colNames);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateComboBox(JComboBox combo) {
/* 178 */     combo.removeAllItems();
/* 179 */     String sql = "select * from book";
/*     */     
/* 181 */     try (Connection conn = DBConnectionManager.getConnection()) {
/* 182 */       ps = conn.prepareStatement(sql);
/* 183 */       ResultSet rs = ps.executeQuery();
/* 184 */       while (rs != null && rs.next()) {
/*     */         
/* 186 */         String val = rs.getString("bookCatagory");
/* 187 */         combo.addItem(val);
/*     */       } 
/* 189 */     } catch (SQLException e) {
/*     */       
/* 191 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\education\Engineering\Java_training2019\GUI_JAR_files\BookManagementSystem\BMS_GUI\consoleBasedBookManagementSystem.jar!\in\ac\sit\cs\bms\db\BookDAO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */