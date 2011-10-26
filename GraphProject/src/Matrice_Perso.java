import java.util.ArrayList;
import java.util.HashSet;


public class Matrice_Perso<E> {
	
	ArrayList<ArrayList<E>>	matrice;
	
	public Matrice_Perso()
	{
		matrice = new ArrayList<ArrayList<E>>();
	}
	
	/**
	 * ensures a minimum capacity of num rows. Note that this does not guarantee
	 * that there are that many rows.
	 * 
	 * @param num
	 */
	public void ensureCapacity(int num)
	{
		matrice.ensureCapacity(num);
	}
 
	/**
	 * Ensures that the given row has at least the given capacity. Note that
	 * this method will also ensure that getNumRows() >= row
	 * 
	 * @param row
	 * @param num
	 */
	public void ensureCapacity(int row, int num)
	{
		ensureCapacity(row);
		while (row < getNumRows())
		{
			matrice.add(new ArrayList<E>());
		}
		matrice.get(row).ensureCapacity(num);
	}
 
	/**
	 * Adds an item at the end of the specified row. This will guarantee that at least row rows exist.
	 */
	public void Add(E data, int row)
	{
		ensureCapacity(row);
		while(row >= getNumRows())
		{
			matrice.add(new ArrayList<E>());
		}
		matrice.get(row).add(data);
	}
 
	public E get(int row, int col)
	{
		return matrice.get(row).get(col);
	}
 
	public void set(int row, int col, E data)
	{
		matrice.get(row).set(col,data);
	}
 
	public void remove(int row, int col)
	{
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
		return "Matrice_Perso [matrice=" + matrice + "]";
	}
	
}
