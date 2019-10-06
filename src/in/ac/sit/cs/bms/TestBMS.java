/*     */ package in.ac.sit.cs.bms;
/*     */ 
/*     */ import in.ac.sit.cs.bms.db.BookDAO;
/*     */ import in.ac.sit.cs.bms.db.IssueDAO;
/*     */ import in.ac.sit.cs.bms.exceptions.BMSException;
/*     */ import in.ac.sit.cs.entity.Author;
/*     */ import in.ac.sit.cs.entity.Book;
/*     */ import in.ac.sit.cs.entity.Student;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.InputMismatchException;
/*     */ import java.util.List;
/*     */ import java.util.Scanner;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TestBMS
/*     */ {
/*     */   public static void main(String[] args) {
/*  22 */     Scanner sc = new Scanner(System.in);
/*  23 */     String choice = null;
/*     */     
/*  25 */     String title = null;
/*  26 */     int ISBN = 0;
/*  27 */     String catagory = null;
/*  28 */     int noOfBooks = 0;
/*  29 */     String aName = null;
/*  30 */     String aPhNo = null;
/*  31 */     String zeroBooks = "0";
/*  32 */     BookDAO dao = new BookDAO();
/*  33 */     List<Book> bookList = null;
/*  34 */     int usingtitle = 1, usingcatagory = 2, usingauthor = 3, listall = 4;
/*  35 */     String studName = null, studUsn = null, issueDate = null;
/*  36 */     Date iDate = null;
/*  37 */     IssueDAO iDao = new IssueDAO();
/*  38 */     Date rDate = null;
/*  39 */     int selectISBN = 0;
/*     */     do {
/*     */       int available;
/*  42 */       System.out.println("\nPlease select your choice : ");
/*  43 */       System.out.println("1. Add a book");
/*  44 */       System.out.println("2. Search a book based on book title");
/*  45 */       System.out.println("3. Search books based on catagory");
/*  46 */       System.out.println("4. Search books based on author");
/*  47 */       System.out.println("5. List all books along with author information");
/*  48 */       System.out.println("6. Issue book to a student");
/*  49 */       System.out.println("7. List books issued to student based on USN number");
/*  50 */       System.out.println("8. List books which are to be returned for current date");
/*  51 */       System.out.println("9. Exit");
/*     */       
/*  53 */       choice = sc.nextLine();
/*  54 */       System.out.println("You chose option " + choice + "\n");
/*     */       
/*  56 */       switch (choice) {
/*     */         case "1":
/*  58 */           System.out.println("Give the details of the book");
/*  59 */           System.out.println("    1)Title");
/*     */           try {
/*  61 */             title = sc.nextLine();
/*  62 */             if (title.equals("") || title.equals(null)) {
/*  63 */               throw new BMSException("title field cannot be empty");
/*     */             }
/*  65 */           } catch (BMSException e) {
/*  66 */             while (title.equals("") || title.equals(null)) {
/*  67 */               System.out.println("Invalid title, null\nEnter the title again");
/*  68 */               title = sc.nextLine();
/*     */             } 
/*     */           } 
/*  71 */           System.out.println("    2)ISBN (Book number)");
/*     */           try {
/*  73 */             ISBN = sc.nextInt(); sc.nextLine();
/*  74 */             if (ISBN <= 0) {
/*  75 */               throw new BMSException("ISBN cannot be zero or negative");
/*     */             }
/*  77 */           } catch (BMSException e) {
/*  78 */             while (ISBN < 0 || ISBN == 0) {
/*  79 */               System.out.println("Invalid ISBN,\nEnter ISBN again ");
/*  80 */               ISBN = sc.nextInt(); sc.nextLine();
/*     */             } 
/*  82 */           } catch (InputMismatchException e) {
/*  83 */             System.out.println("Invalid input, ISBN should be a string value, please enter again");
/*  84 */             ISBN = sc.nextInt(); sc.nextLine();
/*     */           } 
/*  86 */           System.out.println("    3)Catagory");
/*     */           try {
/*  88 */             catagory = sc.nextLine();
/*  89 */             if (catagory.equals("") || catagory.equals(null)) {
/*  90 */               throw new BMSException("catagory field cannot be empty");
/*     */             }
/*  92 */           } catch (BMSException e) {
/*  93 */             while (catagory.equals("") || catagory.equals(null)) {
/*  94 */               System.out.println("Invalid catagory, null\nEnter the catagory again");
/*  95 */               catagory = sc.nextLine();
/*     */             } 
/*     */           } 
/*  98 */           System.out.println("    4)Number of books being added");
/*     */           try {
/* 100 */             noOfBooks = sc.nextInt(); sc.nextLine();
/* 101 */             if (noOfBooks <= 0) {
/* 102 */               throw new BMSException("Number of books cannot be zero or negative number");
/*     */             }
/* 104 */           } catch (BMSException e) {
/* 105 */             while (noOfBooks <= 0) {
/* 106 */               System.out.println("Invalid number, null\nEnter the Number of books again");
/* 107 */               noOfBooks = sc.nextInt(); sc.nextLine();
/*     */             } 
/* 109 */           } catch (InputMismatchException e) {
/* 110 */             System.out.println("Invalid entry, It should be an integer value, \nEnter number again ");
/* 111 */             noOfBooks = sc.nextInt(); sc.nextLine();
/*     */           } 
/* 113 */           System.out.println("Give the details of the Author");
/* 114 */           System.out.println("    1)Name");
/*     */           try {
/* 116 */             aName = sc.nextLine();
/* 117 */             if (aName.equals("") || aName.equals(null)) {
/* 118 */               throw new BMSException("Author name cannot be empty");
/*     */             }
/* 120 */           } catch (BMSException e) {
/* 121 */             while (aName.equals("") || aName.equals(null)) {
/* 122 */               System.out.println("Invalid name, null\nEnter the Author name again");
/* 123 */               aName = sc.nextLine();
/*     */             } 
/*     */           } 
/* 126 */           System.out.println("    2)Phone number or Mail ID");
/*     */           try {
/* 128 */             aPhNo = sc.nextLine();
/* 129 */             if (aPhNo.equals("") || aPhNo.equals(null)) {
/* 130 */               throw new BMSException("This field cannot be empty");
/*     */             }
/* 132 */           } catch (BMSException e) {
/* 133 */             while (aPhNo.equals("") || aPhNo.equals(null)) {
/* 134 */               System.out.println("Invalid entry, null\nEnter the Phone number or Mail ID again");
/* 135 */               aPhNo = sc.nextLine();
/*     */             } 
/*     */           } 
/*     */           
/*     */           try {
/* 140 */             dao.addBook(new Book(title, ISBN, catagory, noOfBooks, new Author(aName, aPhNo)));
/* 141 */           } catch (BMSException e2) {
/*     */             
/* 143 */             e2.printStackTrace();
/* 144 */             System.out.println("Book with ISBN " + ISBN + " already exists\nPLease check your data.");
/*     */           } 
/* 146 */           System.out.println("The book has been added");
/*     */           break;
/*     */         
/*     */         case "2":
/* 150 */           System.out.println("Enter book title");
/*     */           try {
/* 152 */             title = sc.nextLine();
/* 153 */             if (title.equals("") || title.equals(null)) {
/* 154 */               throw new BMSException("Book title field cannot be empty");
/*     */             }
/* 156 */           } catch (BMSException e) {
/* 157 */             while (title.equals("") || title.equals(null)) {
/* 158 */               System.out.println("Invalid Book title\nEnter the Book title again");
/* 159 */               title = sc.nextLine();
/*     */             } 
/* 161 */           } catch (Exception e) {
/* 162 */             e.printStackTrace();
/*     */           } 
/* 164 */           bookList = null;
/* 165 */           bookList = dao.searchUsingTitle(title, 1);
/* 166 */           for (Book i : bookList) {
/* 167 */             System.out.println(i.toString());
/*     */           }
/*     */           break;
/*     */ 
/*     */         
/*     */         case "3":
/* 173 */           System.out.println("Enter book Catagory");
/*     */           try {
/* 175 */             catagory = sc.nextLine();
/* 176 */             if (catagory.equals("") || catagory.equals(null)) {
/* 177 */               throw new BMSException("Book catagory field cannot be empty");
/*     */             }
/* 179 */           } catch (BMSException e) {
/* 180 */             while (catagory.equals("") || catagory.equals(null)) {
/* 181 */               System.out.println("Invalid Book catagory\nEnter the Book title again");
/* 182 */               catagory = sc.nextLine();
/*     */             } 
/* 184 */           } catch (Exception e) {
/* 185 */             e.printStackTrace();
/*     */           } 
/* 187 */           bookList = null;
/* 188 */           bookList = dao.searchUsingTitle(catagory, 2);
/* 189 */           for (Book i : bookList) {
/* 190 */             System.out.println(i.toString());
/*     */           }
/*     */           break;
/*     */ 
/*     */         
/*     */         case "4":
/* 196 */           System.out.println("Enter Author name");
/*     */           try {
/* 198 */             aName = sc.nextLine();
/* 199 */             if (aName.equals("") || aName.equals(null)) {
/* 200 */               throw new BMSException("Author name field cannot be empty");
/*     */             }
/* 202 */           } catch (BMSException e) {
/* 203 */             while (aName.equals("") || aName.equals(null)) {
/* 204 */               System.out.println("Invalid Author name\nEnter the Book title again");
/* 205 */               aName = sc.nextLine();
/*     */             } 
/* 207 */           } catch (Exception e) {
/* 208 */             e.printStackTrace();
/*     */           } 
/* 210 */           bookList = null;
/* 211 */           bookList = dao.searchUsingTitle(aName, 3);
/* 212 */           for (Book i : bookList) {
/* 213 */             System.out.println(i.toString());
/*     */           }
/*     */           break;
/*     */         
/*     */         case "5":
/* 218 */           bookList = null;
/* 219 */           bookList = dao.searchUsingTitle("0", 4);
/* 220 */           for (Book i : bookList) {
/* 221 */             System.out.println(i.toString());
/*     */           }
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case "6":
/* 263 */           bookList = null;
/* 264 */           bookList = iDao.displayAllRecordedBooks();
/* 265 */           for (Book i : bookList) {
/* 266 */             System.out.println(i.toString());
/*     */           }
/* 268 */           System.out.println("\nPlease enter the ISBN of the book you need");
/*     */           try {
/* 270 */             selectISBN = sc.nextInt(); sc.nextLine();
/* 271 */             if (selectISBN <= 0) {
/* 272 */               throw new BMSException("ISBN cannot be zero or negative");
/*     */             }
/* 274 */           } catch (BMSException e) {
/* 275 */             while (selectISBN < 0 || selectISBN == 0) {
/* 276 */               System.out.println("Invalid ISBN,\nEnter ISBN again ");
/* 277 */               ISBN = sc.nextInt(); sc.nextLine();
/*     */             } 
/* 279 */           } catch (InputMismatchException e) {
/* 280 */             System.out.println("Invalid input, ISBN should be a string value, please enter again");
/* 281 */             ISBN = sc.nextInt(); sc.nextLine();
/*     */           } 
/*     */           
/* 284 */           available = iDao.bookAvailability(ISBN);
/* 285 */           if (available == 0) {
/* 286 */             System.out.println("This book is unavailable");
/*     */           } else {
/* 288 */             System.out.println("Enter the Student name");
/*     */             try {
/* 290 */               studName = sc.nextLine();
/* 291 */               if (studName.equals("") || studName.equals(null)) {
/* 292 */                 throw new BMSException("Name cannot be empty");
/*     */               }
/* 294 */             } catch (BMSException e) {
/* 295 */               while (studName.equals("") || studName.equals(null)) {
/* 296 */                 System.out.println("Enter valid name");
/* 297 */                 studName = sc.nextLine();
/*     */               } 
/*     */             } 
/* 300 */             System.out.println("Enter the Student USN");
/*     */             try {
/* 302 */               studUsn = sc.nextLine();
/* 303 */               if (studUsn.equals("") || studUsn.equals(null))
/*     */               {
/* 305 */                 throw new BMSException("USN cannot be empty");
/*     */               }
/* 307 */             } catch (BMSException e) {
/* 308 */               while (studUsn.equals("") || studUsn.equals(null)) {
/* 309 */                 System.out.println("Enter Valid USN");
/* 310 */                 studUsn = sc.nextLine();
/*     */               } 
/*     */             } 
/* 313 */             System.out.println("Enter issue date (dd/mm/yyyy)");
/*     */             try {
/* 315 */               issueDate = sc.nextLine();
/* 316 */               if (issueDate.equals("") || issueDate.equals(null))
/*     */               {
/* 318 */                 throw new BMSException("Date cannot be empty");
/*     */               }
/* 320 */             } catch (BMSException e) {
/*     */               
/* 322 */               while (issueDate.equals("") || issueDate.equals(null)) {
/*     */                 
/* 324 */                 System.out.println("Enter valid date (dd/mm/yyyy)");
/* 325 */                 issueDate = sc.nextLine();
/*     */               } 
/*     */             } 
/*     */             try {
/* 329 */               iDate = (new SimpleDateFormat("dd/MM/yyyy")).parse(issueDate);
/* 330 */             } catch (ParseException e) {
/* 331 */               System.out.println("Incorrect format, Enter as dd/mm/yyyy");
/* 332 */               issueDate = sc.nextLine();
/*     */               try {
/* 334 */                 iDate = (new SimpleDateFormat("dd/MM/yyyy")).parse(issueDate);
/* 335 */               } catch (ParseException e1) {
/* 336 */                 e1.printStackTrace();
/*     */               } 
/*     */             } 
/* 339 */             ArrayList aList = iDao.issueBook(new Student(studName, studUsn), iDate, selectISBN);
/* 340 */             System.out.println("Issue ID : " + aList.get(0) + "\nand the return date : " + aList.get(1));
/*     */           } 
/*     */           
/* 343 */           System.out.println("Book issued");
/*     */           break;
/*     */         
/*     */         case "7":
/* 347 */           System.out.println("Enter the USN");
/*     */           try {
/* 349 */             studUsn = sc.nextLine();
/* 350 */             if (studUsn.equals("") || studUsn.equals(null))
/*     */             {
/* 352 */               throw new BMSException("USN cannot be empty");
/*     */             }
/* 354 */           } catch (BMSException e) {
/* 355 */             while (studUsn.equals("") || studUsn.equals(null)) {
/* 356 */               System.out.println("Enter Valid USN");
/* 357 */               studUsn = sc.nextLine();
/*     */             } 
/*     */           } 
/* 360 */           iDao.listStudentBooks(studUsn);
/*     */           break;
/*     */         
/*     */         case "8":
/* 364 */           System.out.println("Enter the current date");
/*     */           try {
/* 366 */             issueDate = sc.nextLine();
/* 367 */             if (issueDate.equals("") || issueDate.equals(null))
/*     */             {
/* 369 */               throw new BMSException("Date cannot be empty");
/*     */             }
/* 371 */           } catch (BMSException e) {
/*     */             
/* 373 */             while (issueDate.equals("") || issueDate.equals(null)) {
/*     */               
/* 375 */               System.out.println("Enter valid date (dd/mm/yyyy)");
/* 376 */               issueDate = sc.nextLine();
/*     */             } 
/*     */           } 
/*     */           try {
/* 380 */             rDate = (new SimpleDateFormat("dd/MM/yyyy")).parse(issueDate);
/* 381 */           } catch (ParseException e) {
/* 382 */             System.out.println("Incorrect format, Enter as dd/mm/yyyy");
/* 383 */             issueDate = sc.nextLine();
/*     */             try {
/* 385 */               rDate = (new SimpleDateFormat("dd/MM/yyyy")).parse(issueDate);
/* 386 */             } catch (ParseException e1) {
/* 387 */               e1.printStackTrace();
/*     */             } 
/*     */           } 
/* 390 */           iDao.booksToBeReturned(rDate);
/*     */           break;
/*     */         
/*     */         case "9":
/* 394 */           System.out.println("Exiting program");
/*     */           break;
/*     */         
/*     */         default:
/* 398 */           System.out.println("Invalid choice!");
/*     */           break;
/*     */       } 
/*     */     
/* 402 */     } while (!choice.equals("9"));
/*     */     
/* 404 */     sc.close();
/*     */   }
/*     */ }


/* Location:              E:\education\Engineering\Java_training2019\GUI_JAR_files\BookManagementSystem\BMS_GUI\consoleBasedBookManagementSystem.jar!\in\ac\sit\cs\bms\TestBMS.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */