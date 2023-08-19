package com.care.library.search;

public class BookDTO {
	 private String bookname;
	    private String authors;
	    private String publisher;
	    private String bookImageURL;

		public String getBookname() {
			return bookname;
		}


		public void setBookname(String bookname) {
			this.bookname = bookname;
		}


		public String getAuthors() {
			return authors;
		}


		public void setAuthors(String authors) {
			this.authors = authors;
		}


		public String getPublisher() {
			return publisher;
		}


		public void setPublisher(String publisher) {
			this.publisher = publisher;
		}


		public String getBookImageURL() {
			return bookImageURL;
		}


		public void setBookImageURL(String bookImageURL) {
			this.bookImageURL = bookImageURL;
		}
}
