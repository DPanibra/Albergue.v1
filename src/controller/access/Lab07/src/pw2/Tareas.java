package pw2;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.IdGeneratorStrategy;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Tareas {
@PrimaryKey
@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) private Long id;
@Persistent private String nombre;
@Persistent private String tema;
@Persistent private String curso;
@Persistent private String alumno;
@Persistent private String profesor;
public Tareas(  String tema, String curso, String alumno, String profesor) {
	
	this.tema = tema;
	this.curso = curso;
	this.alumno = alumno;
	this.profesor = profesor;
}

public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getTema() {
	return tema;
}
public void setTema(String tema) {
	this.tema = tema;
}
public String getCurso() {
	return curso;
}
public void setCurso(String curso) {
	this.curso = curso;
}
public String getAlumno() {
	return alumno;
}
public void setAlumno(String alumno) {
	this.alumno = alumno;
}
public String getProfesor() {
	return profesor;
}
public void setProfesor(String profesor) {
	this.profesor = profesor;
}

}