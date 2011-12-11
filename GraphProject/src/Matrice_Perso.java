import java.util.ArrayList;

public class Matrice_Perso<E> {
	
	/**
	 * @uml.property  name="matrice"
	 * @uml.associationEnd  
	 */
	ArrayList<ArrayList<E>>	matrice;
	
	public Matrice_Perso() {
		matrice = new ArrayList<ArrayList<E>>();
	}
	
	
	public void ensureCapacity(int num) {
		matrice.ensureCapacity(num);
	}
 
	
	public void ensureCapacity(int row, int num) {
		ensureCapacity(row);
		while (row < getNumRows())
		{
			matrice.add(new ArrayList<E>());
		}
		matrice.get(row).ensureCapacity(num);
	}
 
	
	public void Add(E data, int row) {
		ensureCapacity(row);
		while(row >= getNumRows())
		{
			matrice.add(new ArrayList<E>());
		}
		matrice.get(row).add(data);
	}
 
	public E get(int row, int col) {
		return matrice.get(row).get(col);
	}
 
	public void set(int row, int col, E data) {
		matrice.get(row).set(col,data);
	}
 
	public void remove(int row, int col) {
		matrice.get(row).remove(col);
	}
 
	public boolean contains(E data)
	{
		for (int i = 0; i < matrice.size(); i++)
		{
			if (matrice.get(i).contains(data))
			{
				return true;
			}
		}
		return false;
	}
 
	public int getNumRows()
	{
		return matrice.size();
	}
 
	public int getNumCols(int row)
	{
		return matrice.get(row).size();
	}

	@Override
	public String toString() {
		
		String str = "\n";
		
		for (int i=0; i<this.getNumRows(); i++) {
			str += "";
			str += i + "\t";
			for (int j=0; j<this.getNumCols(i); j++) {
				str += this.get(i, j) + "\t";
			}
			
			str += "\n";
		
		}
		return str;
	}
	
}
