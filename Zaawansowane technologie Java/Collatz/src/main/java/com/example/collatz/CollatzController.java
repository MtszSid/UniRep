package com.example.collatz;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.LinkedList;
import java.util.Queue;

public class CollatzController {

    public Label output;
    public Button Calculate;
    public TextField input;


    public void OnCalculate(ActionEvent actionEvent) {
        try{
            StringBuilder sb = new StringBuilder();
            int val = Integer.parseInt(input.getText());

            if(val <= 0){
                output.setText("invalid input.");
            }
            else{
                Queue<Integer> queue = new LinkedList<Integer>();
                while(val > 1){
                    queue.add(val);
                    if(val % 2 == 0){
                        val /= 2;
                    }
                    else{
                        val = 3 * val + 1;
                    }
                }
                while(!queue.isEmpty()){
                    sb.append(queue.poll());
                    sb.append(", ");
                }

                sb.append(1);
                output.setText(sb.toString());
            }

        }
        catch(NumberFormatException e){
            output.setText("invalid input.");
        }
    }
}
