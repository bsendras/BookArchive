package unlam.analisisdesoftware.BookArchiveClasses;

public class Book implements Comparable<Book> {

    private String ISBN;
    private String titulo;
    private String autor;
    private String editorial;
    private int edicion;
    private int anno_de_publicacion;
    private String coverImage = "";

	@Override
    public boolean equals(Object book) {
        return this==book || (book instanceof Book && ISBN.equals(((Book)book).ISBN));
    }

	@Override
	public int compareTo(Book book) {
		// TODO Auto-generated method stub
		return ISBN.compareTo(book.ISBN);
	}
	
    
    @Override
    public String toString() {
        return
            "ISBN               : " + ISBN + "\n" +
            "titulo             : " + titulo + "\n" +
            "autor              : " + autor + "\n" +
            "editorial          : " + editorial + "\n" +
            "edicion            : " + edicion + "\n" +
            "anno de publicacion: " + anno_de_publicacion + "\n";
    }

    public String getISBN() {
        return ISBN;
    }
    
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitulo() {
        return titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }
    
    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }
    
    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getEdicion() {
        return edicion;
    }
    
    public void setEdicion(int edicion) {
        this.edicion = edicion;
    }

    public int getAnno_de_publicacion() {
        return anno_de_publicacion;
    }
    
    public void setAnno_de_publicacion(int anno_de_publicacion) {
        this.anno_de_publicacion = anno_de_publicacion;
    }
    
    public String getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	} 
}
