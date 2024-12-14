package Unit3_Assignment6;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class ArrayMethodsWorking {

	/*
	 * implement the following methods so that the program runs:
	 * 
	 * generateArray() <- must not generate 0
	 * convertArray()
	 * RangeOfArray()
	 * indexOfSmallest()
	 * sortArray()
	 * mergeArray()
	 * 
	 * read the code below to help you decide how to write the methods
	 * 
	 * after you've done implementing the methods, make sure to
	 * uncomment the proper section of the code below so that
	 * the program actually calls the methods
	 * 
	 */

	/*
	 * DO NOT change any of the variables or methods below
	 * It will break the program
	 */

	public double [] generateArray (double max, double min, int size) {
		double [] list = new double [size];

		for (int i = 0 ; i < size; i++)
			list [i] = (int) (Math.random () * (max-min)+1) + min;
		return list;
	}

	public String displayArray (double [] list, int numPerRow) {

		String result = "";
		for (int i = 0 ; i < list.length; i++) {
			if (i % numPerRow == 0)
				result += "\n";
			result += String.format("%8.1f", list[i]);
		}
		return result;
	}

	public double rangeOfArray (double [] list) {
		double largest = list[0];
		double smallest = list[0];
		for (int i = 1; i < list.length; i++) {
			largest = Math.max(largest,  list[i]);
		    smallest = Math.min(smallest,  list[i]);
		}
		return largest-smallest;
	}

	public double [] listHalfNumbers (double [] list) {
		double halfRange = rangeOfArray(list) / 2;
		int count = 0;
		for (int i = 0 ; i < list.length; i++)
			if (list[i] > halfRange)
				count ++;
		double [] newArray = new double [count];
		count = 0;
		for (int i = 0 ; i < list.length; i++) {
			if (list[i] > halfRange) {
				newArray[count] = list[i];
				count ++;
			}
		}
		return newArray;
	}

	public void sortArray (double [] list) {

		for (int i =list.length-1; i >= 0; i--)
			for (int j = 0; j < i; j++)
				if (list[j] > list [j+1]) {
					double temp = list[j];
					list [j] = list[j+1];
					list [j+1] = temp;
				}

	}

	public double [] mergeArray (double [] list1, double [] list2) {

		double [] newList = new double [list1.length + list2.length];
		int index1 = 0, index2 = 0;

		for (int i = 0; i < newList.length; i ++) {
			if (index1 < list1.length && index2 < list2.length ) {
				if (list1 [index1] <= list2 [index2]) {
					newList [i] = list1 [index1++];
				}
				else
					newList [i] = list2 [index2++];
			}
			else {
				if (index1 < list1.length)
					newList [i] = list1 [index1++];
				else
					newList [i] = list2 [index2++];

			}

		}
		return newList; 

	}

	double[] arr1, arr2;
	JFrame frame;
	JPanel mainPanel, arrPanel, btnPanel, labelPanel;
	JTextArea arr1Text, arr2Text, mainText;
	JButton btnGenerate, btnDisplay, btnRange, btnListHalf, btnSort, btnMerge;
	JTextField minInputField, maxInputField, sizeInputField, rowInputField;

	public ArrayMethodsWorking() {
		initJComponents(); //you can ignore this

		// you can start reading from here

		btnGenerate = new JButton("generate array");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//if(arr1 == null) return;

				if(!isNumeric(maxInputField.getText())) return;
				if(!isNumeric(minInputField.getText())) return;
				if(!isInteger(sizeInputField.getText())) return;

				//uncomment the following line(s) 
				//once you are done implementing the method

				double max = Double.parseDouble(maxInputField.getText());
				double min = Double.parseDouble(minInputField.getText());
				int size = Integer.parseInt(sizeInputField.getText());

				arr1 = generateArray(max, min, size);
				arr2 = generateArray(max, min, size);
			
				
				//uncomment the following line(s) 
				//once you are done implementing the method
				arr1Text.setText("arr1:\n" + displayArray(arr1, 10));
				arr2Text.setText("arr2:\n" + displayArray(arr2, 10));

			}
		});

		btnDisplay = new JButton("Display Array");
		btnDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(arr1 == null) return;
				if(!isInteger(rowInputField.getText())) return;

				//uncomment the following line(s) 
				//once you are done implementing the method

				int size = Integer.parseInt(rowInputField.getText());

				String arrayToString = displayArray(arr1, size);

				mainText.setText(arrayToString);

			}
		});

		btnRange = new JButton("Range of array");
		btnRange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(arr1 == null) return;

				//uncomment the following line(s) 
				//once you are done implementing the method

				double range = rangeOfArray(arr1);

				mainText.setText(String.valueOf(range));

			}
		});

		btnListHalf = new JButton("List Half Numbers");
		btnListHalf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(arr1 == null) return;

				//uncomment the following line(s) 
				//once you are done implementing the method
				double [] listHalf = listHalfNumbers(arr1);
				int numPerRow = Integer.parseInt(rowInputField.getText());
				mainText.setText(displayArray(listHalf, numPerRow));
				
			

			}
		});

		btnSort = new JButton("sort array");
		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(arr1 == null) return;

				//uncomment the following line(s) 
				//once you are done implementing the method

				sortArray(arr1);
				int size = Integer.parseInt(rowInputField.getText());

				mainText.setText(displayArray(arr1, size));

			}
		});

		btnMerge = new JButton("merge array");
		btnMerge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(arr1 == null) return;

				//uncomment the following line(s) 
				//once you are done implementing the method

				sortArray (arr1);
				sortArray (arr2);
				double[] mergedArray = mergeArray(arr1, arr2);
				int size = Integer.parseInt(rowInputField.getText());

				mainText.setText(displayArray(mergedArray, size));

			}
		});

		//you can stop reading at this point

		initMoreJComponents(); //you can ignore this(unless you're doing the bonus)
	}

	//very messy JComponent code below
	//can be used for reference(only if you can understand it)
	//can be modified for the bonus

	void initJComponents() {
		frame = new JFrame("Array Methods Demonstration");

		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		Font COURIER = new Font("Courier", Font.PLAIN, 11);
		arr1Text = new JTextArea("arr1: ");
		arr1Text.setPreferredSize(new Dimension(600, 200));
		arr1Text.setLineWrap(true);
		arr1Text.setFont(COURIER);
		arr1Text.setEditable(false);
		arr2Text = new JTextArea("arr2: ");
		arr2Text.setPreferredSize(new Dimension(600, 200));
		arr2Text.setLineWrap(true);
		arr2Text.setFont(COURIER);
		arr2Text.setEditable(false);
		JPanel arr1Panel = new JPanel();
		arr1Panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		arr1Panel.add(arr1Text);
		JPanel arr2Panel = new JPanel();
		arr2Panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		arr2Panel.add(arr2Text);
		mainPanel.add(arr1Panel);
		mainPanel.add(arr2Panel);

		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(0, 6));
		JLabel maxLabel = new JLabel("Max: ");
		maxLabel.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
		inputPanel.add(maxLabel);
		maxInputField = new JTextField("100");
		inputPanel.add(maxInputField);
		JLabel minLabel = new JLabel("Min: ");
		minLabel.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
		inputPanel.add(minLabel);
		minInputField = new JTextField("0");
		inputPanel.add(minInputField);
		JLabel sizeLabel = new JLabel("Size: ");
		sizeLabel.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
		inputPanel.add(sizeLabel);
		sizeInputField = new JTextField("10");
		inputPanel.add(sizeInputField);
		mainPanel.add(inputPanel);

		JPanel rowInputPanel = new JPanel();
		rowInputPanel.setAlignmentX(JPanel.RIGHT_ALIGNMENT);
		rowInputPanel.setLayout(new BoxLayout(rowInputPanel, BoxLayout.X_AXIS));
		rowInputField = new JTextField("1");
		rowInputPanel.add(new JLabel("Numbers per row: "));
		rowInputPanel.add(rowInputField);
		mainPanel.add(rowInputPanel);

		btnPanel = new JPanel();
		btnPanel.setLayout(new GridLayout(0, 3));
	}

	void initMoreJComponents() {
		Font COURIER = new Font("Courier", Font.PLAIN, 11);
		btnPanel.add(btnGenerate);
		btnPanel.add(btnDisplay);
		btnPanel.add(btnRange);
		btnPanel.add(btnListHalf);
		btnPanel.add(btnSort);
		btnPanel.add(btnMerge);

		labelPanel = new JPanel();
		labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.X_AXIS));
		labelPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		mainText = new JTextArea();
		mainText.setPreferredSize(new Dimension(600, 200));
		mainText.setMaximumSize(new Dimension(600, 200));
		mainText.setMinimumSize(new Dimension(600, 200));
		mainText.setLineWrap(true);
		mainText.setFont(COURIER);
		mainText.setEditable(false);

		labelPanel.setAlignmentY(Panel.TOP_ALIGNMENT);

		mainPanel.add(btnPanel);
		mainPanel.add(labelPanel);

		JPanel finalPanel = new JPanel();
		finalPanel.setLayout(new GridLayout(0,2));
		finalPanel.add(mainPanel);
		finalPanel.add(mainText);
		frame.add(finalPanel);

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

	//      String arrayToText(double[] arr) {
	//              StringBuilder sb = new StringBuilder();
	//              for(double d : arr) {
	//                      sb.append(d);
	//                      sb.append("  ");
	//              }
	//              return sb.toString();
	//      }

	//      String arrayToText(double[][] array) {
	//              StringBuilder sb = new StringBuilder();
	//              for(int i = 0; i < array.length; i++) {
	//                      for(int j = 0; j < array[0].length; j++) {
	//                              if(array[i][j] != 0) {
	//                                      sb.append(array[i][j]);
	//                                      sb.append("  ");
	//                              }
	//                      }
	//                      sb.append("\n");
	//              }
	//              return sb.toString();
	//      }

	public boolean isNumeric(String s) {
		try {  
			Double.parseDouble(s);  
			return true;
		} catch(NumberFormatException e){  
			return false;  
		} catch(NullPointerException e) {
			return false;
		}
	}

	public boolean isInteger(String s) {
		try { 
			Integer.parseInt(s); 
		} catch(NumberFormatException e) { 
			return false; 
		} catch(NullPointerException e) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		new ArrayMethodsWorking();
	}
}