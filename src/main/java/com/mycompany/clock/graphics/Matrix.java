/*
 * |-------------------------------------------------
 * | Copyright © 2008 Colin But. All rights reserved. 
 * |-------------------------------------------------
 */
package com.mycompany.clock.graphics;

/**
 * @author colin
 *
 */
public class Matrix {
	
	private int rows;
	private int columns;
	public float[][] matrix;
	
	/**
	 * Constructor
	 * 
	 * @param rows
	 * @param columns
	 * @param value
	 */
	public Matrix(int rows, int columns, float value){
		this.rows = rows;
		this.columns = columns;
		matrix = new float[rows][columns];
		
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				matrix[i][j] = value;
			}//end j
		}//end i
	}
	
	/**
	 * Constructor
	 * 
	 * @param data
	 */
	public Matrix(float[][] data){
		rows = data.length;
		columns = data[0].length;
		for(int k = 0; k < rows; k++){
			if(data[k].length != columns){
				throw new IllegalArgumentException("All rows must have same length");
			}
		}
		matrix = data;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getRows(){
		return rows;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getColumns(){
		return columns;
	}
	
	/**
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public float getElement(int i, int j){
		return (matrix[i][j]);
	}
	
	/**
	 * 
	 * @param i
	 * @param j
	 * @param value
	 */
	public void setElement(int i, int j, float value){
		matrix[i][j] = value;
	}
	
	/**
	 * 
	 * @param b
	 * @return
	 */
	public Matrix mult(Matrix b){
		float sum = 0;
		int i,j,k;
		if(rows != b.columns){
			throw new IllegalArgumentException("Error");
		}
		
		Matrix result = new Matrix(b.rows, columns, 0);
		
		for(i = 0; i < b.rows; i++){
			for(j = 0; j < columns; j++){
				sum = 0;
				for(k = 0; k < rows; k++){
					sum += b.getElement(i, k) * matrix[k][j];
				}
				result.matrix[i][j] = sum;
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @param b
	 */
	public void transform(Matrix b){
		float sum = 0;
		int i,j,k;
		if(rows != b.columns){
			throw new IllegalArgumentException("Error");
		}
		
		float[][] result = new float[b.rows][columns];
		
		for(i = 0; i < b.rows; i++){
			for(j = 0; j < columns; j++){
				sum = 0;
				for(k = 0; k < rows; k++){
					sum += b.getElement(i,k) * matrix[k][j];
				}
				result[i][j] = sum;
			}
		}
		
		matrix = result;
	}

}
