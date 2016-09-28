package k00370783.example.tamuk.edu.tictactoeadvanced;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends Activity {


    private boolean oturn = false;
    private char gamecreen[][] = new char[3][3];
    private int count = 0;
    private int swap = 0;
    private Button button0 = null;
    private Button button1 = null;
    private Button button2 = null;
    private Button button3 = null;
    private Button button4 = null;
    private Button button5 = null;
    private Button button6 = null;
    private Button button7 = null;
    private Button button8 = null;


    private ArrayList<Button> btlList = new ArrayList<Button>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button0 = (Button) findViewById(R.id.button8);
        button1 = (Button) findViewById(R.id.button0);
        button2 = (Button) findViewById(R.id.button1);
        button3 = (Button) findViewById(R.id.button2);
        button4 = (Button) findViewById(R.id.button3);
        button5 = (Button) findViewById(R.id.button4);
        button6 = (Button) findViewById(R.id.button5);
        button7 = (Button) findViewById(R.id.button6);
        button8 = (Button) findViewById(R.id.button7);

        btlList.add(button0);
        btlList.add(button1);
        btlList.add(button2);
        btlList.add(button3);
        btlList.add(button4);
        btlList.add(button5);
        btlList.add(button6);
        btlList.add(button7);
        btlList.add(button8);

        setupOnClickListeners();
        resetButtons();
    }


    public void newGame(View view) {
        oturn = false;
        gamecreen = new char[3][3];
        count = 0;
        swap = 0;
        resetButtons();
        TextView t = (TextView) findViewById(R.id.turn);
        t.setText("");
    }

    private void resetButtons() {
        TableLayout T = (TableLayout) findViewById(R.id.tableLayout);
        for (int y = 0; y < T.getChildCount(); y++) {
            if (T.getChildAt(y) instanceof TableRow) {
                TableRow R = (TableRow) T.getChildAt(y);
                for (int x = 0; x < R.getChildCount(); x++) {
                    if (R.getChildAt(x) instanceof Button) {
                        Button B = (Button) R.getChildAt(x);
                        B.setText("");
                        B.setEnabled(true);
                    }
                }
            }
        }
        TextView t = (TextView) findViewById(R.id.titleText);
        t.setText(R.string.title);
    }


    private boolean checkWin() {


        char winner = '\0';
        if (checkWinner(gamecreen, 3, 'X')) {
            winner = 'X';
        } else if (checkWinner(gamecreen, 3, 'O')) {
            winner = 'O';
        }

        if (winner == '\0') {
            return false;
        } else {

            TextView T = (TextView) findViewById(R.id.turn);
            T.setText(winner + " wins");
            Toast.makeText(MainActivity.this, winner + " wins", Toast.LENGTH_SHORT).show();
            //  Toast.makeText(MainActivity.this, count + " repeat", Toast.LENGTH_SHORT).show();

            return true;
        }


    }


    private boolean checkWinner(char[][] gamecreen, int size, char player) {

        for (int x = 0; x < size; x++) {
            int total = 0;
            for (int y = 0; y < size; y++) {
                if (gamecreen[x][y] == player) {
                    total++;
                }
            }
            if (total >= size) {
                return true;
            }
        }

        for (int y = 0; y < size; y++) {
            int total = 0;
            for (int x = 0; x < size; x++) {
                if (gamecreen[x][y] == player) {
                    total++;
                }
            }
            if (total >= size) {
                return true;
            }
        }


        int total = 0;
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (x == y && gamecreen[x][y] == player) {
                    total++;
                }
            }
        }
        if (total >= size) {
            return true;
        }


        total = 0;
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (x + y == size - 1 && gamecreen[x][y] == player) {
                    total++;
                }
            }
        }
        if (total >= size) {
            return true;
        }

        return false;
    }


    private void disableButtons() {
        TableLayout T = (TableLayout) findViewById(R.id.tableLayout);
        for (int y = 0; y < T.getChildCount(); y++) {
            if (T.getChildAt(y) instanceof TableRow) {
                TableRow R = (TableRow) T.getChildAt(y);
                for (int x = 0; x < R.getChildCount(); x++) {
                    if (R.getChildAt(x) instanceof Button) {
                        Button B = (Button) R.getChildAt(x);
                        B.setEnabled(false);
                    }
                }
            }
        }
    }

    private void enableB() {
        for (Button btn : btlList) {
            Button btns = (Button) findViewById(btn.getId());
            btns.setEnabled(true);
            //Toast.makeText(getApplicationContext()," to be blocked", Toast.LENGTH_SHORT).show();
        }
    }

    private void setupOnClickListeners() {
        TableLayout T = (TableLayout) findViewById(R.id.tableLayout);
        for (int y = 0; y < T.getChildCount(); y++) {
            if (T.getChildAt(y) instanceof TableRow) {
                TableRow R = (TableRow) T.getChildAt(y);
                for (int x = 0; x < R.getChildCount(); x++) {
                    View V = R.getChildAt(x); // In our case this will be each button on the grid
                    V.setOnClickListener(new PlayOnClick(x, y));
                }
            }
        }
    }


    private class PlayOnClick implements View.OnClickListener {

        private int x = 0;
        private int y = 0;

        public PlayOnClick(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void onClick(View view) {

            if (view instanceof Button && count < 6) {
                Button B = (Button) view;
                TextView T = (TextView) findViewById(R.id.turn);

                gamecreen[x][y] = oturn ? 'O' : 'X';
                B.setText(oturn ? "O" : "X");
                T.setText(oturn ? "X's Turn" : "0's Turn");
                B.setEnabled(true);
                //flag= oturn ? "O" : "X";
                oturn = !oturn;
                count++;

                if (checkWin()) {
                    disableButtons();
                }
            }
            if (swap == 1) {
                swap = 0;




                Button B = (Button) findViewById(view.getId());
                TextView T = (TextView) findViewById(R.id.turn);


                //     Toast.makeText(getApplicationContext(), oturn+  "inside swap", Toast.LENGTH_SHORT).show();
                gamecreen[x][y] = oturn ? 'O' : 'X';

                //    Toast.makeText(getApplicationContext(), oturn ? "O" : "X", Toast.LENGTH_SHORT).show();

                B.setText(oturn ? "O" : "X");
                //B.setBackgroundColor(Color.BLUE);
                //  Toast.makeText(getApplicationContext(), "Button Tex : "+B.getText().toString(), Toast.LENGTH_SHORT).show();
                T.setText(oturn ? "X's Turn" : "0's Turn");
                B.setEnabled(true);
                oturn = !oturn;
                count++;


                enableB();
                disableSpecific(oturn ? "X" : "O");

                if (checkWin()) {
                    disableButtons();
                }

            }
            else {

                if (count > 6) {
                    Button B = (Button) view;
                    B.setText(" ");
                    gamecreen[x][y] = ' ';
                    swap = 1;


                } else if (count == 6) {

                    disableSpecific(oturn ? "X" : "O");
                    count++;
                }
            }


       /* if(count==6)
        {
                  disableButtons();
        }*/

        }

        public void disableSpecific(String s) {


            for (Button btn : btlList) {


                String text = btn.getText().toString();
                if (text.equals(s)) {
                    Button btns = (Button) findViewById(btn.getId());
                    btns.setEnabled(false);

                }

            }
        }
    }
}


