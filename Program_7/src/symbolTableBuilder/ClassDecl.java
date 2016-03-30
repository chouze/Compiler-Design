package symbolTableBuilder;

public class ClassDecl 
{
	ClassDeclSpec classDeclSpec;
	ClassDeclDeff classDeclDeff;
	
	public ClassDecl(ClassDeclSpec classDeclSpec, ClassDeclDeff classDeclDeff) {
		super();
		this.classDeclSpec = classDeclSpec;
		this.classDeclDeff = classDeclDeff;
	}
}
